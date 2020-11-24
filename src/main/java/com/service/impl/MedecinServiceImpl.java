package com.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Consultation;
import com.entity.Medecin;
import com.entity.Reservation;
import com.exception.notfound.MedecinNotFoundException;
import com.repo.IConsultationRepository;
import com.repo.IMedecinRepository;
import com.repo.IReservationRepo;
import com.service.IMedecinService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe service {@code MedecinServiceImpl} spécifique de {@link Medecin} qui
 * étend de la classe générique {@code DaoServiceImpl} et implémente l'interface
 * spécifique {@code IMedecinService}.
 * 
 * @author Jonathan Rachwal
 * @see DaoServiceImpl
 * @see IMedecinService
 *
 */
@Service
@Slf4j
public class MedecinServiceImpl extends DaoServiceImpl<Medecin> implements IMedecinService {

	// ATTRIBUTS
	@Autowired
	private IMedecinRepository medecinRepository;

	@Autowired
	private IReservationRepo reservationRepository;

	@Autowired
	private IConsultationRepository consultationRepository;

	// METHODES

	@Override
	public Medecin existsByIdentifiant(String identifiant) throws MedecinNotFoundException {
		try {
			log.info("Service spécifique de Medecin: méthode 'existsByIdentifiant' appelée.");
			if (identifiant != null) {
				log.info("Appel repo OK.");
				Medecin medecin = medecinRepository.findByIdentifiant(identifiant);
				return medecin;
			} else {
				log.warn("Erreur méthode 'existsByIdentifiant': identifiant null.");
				throw new MedecinNotFoundException("Aucun médecin n'existe avec cet identifiant.");
			}
		} catch (MedecinNotFoundException mnfe) {
			mnfe.printStackTrace();
			mnfe.getMessage();
		}
		return null;
	}

	@Override
	public Medecin existsByIdentifiantAndMotDePasse(String identifiant, String mdp) throws MedecinNotFoundException {
		try {
			log.info("Service spécifique de Medecin: méthode 'existsByIdentifiantAndMotDePasse' appelée.");
			if (identifiant != null) {
				if (mdp != null) {
					log.info("Appel repo OK.");
					Medecin medecin = medecinRepository.findByIdentifiantAndMotDePasse(identifiant, mdp);
					return medecin;
				} else {
					log.warn("Erreur méthode 'existsByIdentifiantAndMotDePasse': mdp null.");
					throw new MedecinNotFoundException("Médecin non trouvé : mpd = null.");
				}
			} else {
				log.warn("Erreur méthode 'existsByIdentifiantAndMotDePasse': identifiant null et mdp null.");
				throw new MedecinNotFoundException("Aucun médecin n'existe avec cet identifiant et ce mdp.");
			}
		} catch (MedecinNotFoundException mnfe) {
			mnfe.printStackTrace();
			mnfe.getMessage();
		}
		return null;
	}

	@Override
	public int totalDesMedecins() {
		return medecinRepository.totalDesMedecins();
	}

	@Override
	public Map<String, Integer> totalMedecinsParSpecialite() {
		return medecinRepository.totalMedecinsParSpecialite();
	}

	@Override
	public List<Consultation> confirmerRdv(Long idReservation, Long idMedecin, Long idPatient) throws ParseException {
		log.info("Service spécifique du Medecin : méthode confirmerRdv appelée.");
		if (idReservation != null) {
			Reservation reservationToConfirm = reservationRepository.findById(idReservation).get();
			log.info("Appel repo reservation OK.");
			if (reservationToConfirm.isStatus() == false) {
				Date dateToConfirm = reservationToConfirm.getDateRervation();
				Medecin medecinConcerned = medecinRepository.findById(idMedecin).get();
				List<Consultation> listeConsultation = medecinConcerned.getConsultations();
				log.info("Appel repo medecin OK.");
				List<Consultation> listeConsultationAlreadyToThisDate = listeConsultation.stream()
						.filter(c -> c.getReservation().getDateRervation() == dateToConfirm)
						.collect(Collectors.toList());
				if (listeConsultationAlreadyToThisDate.isEmpty()) {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
					// verifie si l'heure est entre 8h-12h et 14h-18h
					try {
						// TODO : update ces horaires devrait être spécifique pour chaque medecin pour
						// varier ses temps de travail
						Date TimeStartMorning = simpleDateFormat.parse("08:00");
						Date TimeStopMorning = simpleDateFormat.parse("12:00");
						Date TimeStartAfternoon = simpleDateFormat.parse("14:00");
						Date TimeStopAfternoon = simpleDateFormat.parse("18:00");

						String simpleDateFormatToConfirm = simpleDateFormat.format(dateToConfirm);
						Date timeToConfirm = simpleDateFormat.parse(simpleDateFormatToConfirm);
						if ((timeToConfirm.after(TimeStartMorning) && timeToConfirm.before(TimeStopMorning))
								|| (timeToConfirm.after(TimeStartAfternoon)
										&& timeToConfirm.before(TimeStopAfternoon))) {
							Consultation consultationConfirmed = new Consultation(null, idPatient,
									reservationToConfirm);
							consultationRepository.save(consultationConfirmed);

							listeConsultation.add(consultationConfirmed);
							medecinConcerned.setConsultations(listeConsultation);
							medecinRepository.save(medecinConcerned);

							reservationToConfirm.setStatus(true);
							reservationRepository.save(reservationToConfirm);
							return medecinConcerned.getConsultations();
						}
					} catch (ParseException e) {
						// faire les exceptions
					}

				} else {
					log.warn("medecin déjà en rdv à ce créneau en choisir un autre");
				}
			} else {
				log.warn("votre réservation est déjà prise en compte");
			}
		}
		return null;
	}

	@Override
	public Map<Consultation, Date> consulterPlanning(Long idMedecin) {
		log.info("Service spécifique du Medecin : méthode consulterPlanning appelée.");
		if (idMedecin != null) {
			Medecin medecinConcerned = medecinRepository.findById(idMedecin).get();
			log.info("Appel repo medecin OK.");
			List<Consultation> listeConsultations = medecinConcerned.getConsultations();
			Map<Consultation, Date> planningConsultations = new HashMap<>();
			for (int i = 0; i < listeConsultations.size(); i++) {
				Consultation consultationPlanned = listeConsultations.get(i);
				planningConsultations.put(consultationPlanned, consultationPlanned.getReservation().getDateRervation());
			}
			return planningConsultations;
		}
		return null;
	}

}
