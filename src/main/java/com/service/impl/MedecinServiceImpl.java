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
import com.exception.notfound.ReservationNotFoundException;
import com.exception.notsuccess.MedecinNotSuccessException;
import com.exception.notsuccess.ReservationNotSuccessException;
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
	public Medecin existsByIdentifiant(String identifiant) throws MedecinNotSuccessException, MedecinNotFoundException {
		try {
			log.info("Service spécifique de Medecin: méthode 'existsByIdentifiant' appelée.");
			if (identifiant != null) {
				log.info("Appel repo OK.");
				try {
					Medecin medecin = medecinRepository.findByIdentifiant(identifiant);
					return medecin;
				} catch (MedecinNotSuccessException mnse) {
					log.warn("Erreur méthode 'existsByIdentifiant': findByIdentifiant dans repo => échoué trouver objet Medecin.");
					mnse.printStackTrace();
				}

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
	public Medecin existsByIdentifiantAndMotDePasse(String identifiant, String mdp) throws MedecinNotSuccessException, MedecinNotFoundException {
		try {
			log.info("Service spécifique de Medecin: méthode 'existsByIdentifiantAndMotDePasse' appelée.");
			if (identifiant != null) {
				if (mdp != null) {
					log.info("Appel repo OK.");
					try {
						Medecin medecin = medecinRepository.findByIdentifiantAndMotDePasse(identifiant, mdp);
						return medecin;
					} catch (MedecinNotSuccessException mnse) {
						log.warn("Erreur méthode 'existsByIdentifiantAndMotDePasse': findByIdentifiantAndMotDePasse dans repo => échoué trouver objet Medecin.");
						mnse.printStackTrace();
					}
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
	public int totalDesMedecins() throws MedecinNotSuccessException {
		return medecinRepository.totalDesMedecins();
	}

	@Override
	public Map<String, Integer> totalMedecinsParSpecialite() throws MedecinNotSuccessException {
		return medecinRepository.totalMedecinsParSpecialite();
	}

	@Override
	public List<Consultation> confirmerRdv(Long idReservation, Long idMedecin) 
			throws ParseException, ReservationNotFoundException, MedecinNotSuccessException, ReservationNotSuccessException {
		try {
			log.info("Service spécifique du Medecin : méthode confirmerRdv appelée.");
			
			if (idReservation != null) {
				// Récupération de la réservation à confirmer
				Reservation reservationToConfirm = reservationRepository.findById(idReservation).get();
				log.info("Appel repo reservation OK.");
				
				if (reservationToConfirm.isStatus() == false) {
					// Récupération de la date du rdv et du médecin visé
					Date dateToConfirm = reservationToConfirm.getDateRervation();
					Medecin medecinConcerned = medecinRepository.findById(idMedecin).get();
					log.info("Appel repo medecin OK.");
					
					// Récupération des dates de rdv du médecin et vérification si un rdv est déjà prévu à cette date
					List<Consultation> listeConsultation = medecinConcerned.getConsultations();
					List<Consultation> listeConsultationAlreadyToThisDate = listeConsultation.stream()
							.filter(c -> c.getReservation().getDateRervation() == dateToConfirm)
							.collect(Collectors.toList());

					if (listeConsultationAlreadyToThisDate.isEmpty()) {

						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
						// vérification si l'heure du redv est entre 8h-12h et 14h-18h
						try {
							// TODO : update : ces horaires devrait être spécifique pour chaque medecin pour
							// varier ses temps de travail
							// Limite des horaires de travail
							Date TimeStartMorning = simpleDateFormat.parse("08:00");
							Date TimeStopMorning = simpleDateFormat.parse("12:00");
							Date TimeStartAfternoon = simpleDateFormat.parse("14:00");
							Date TimeStopAfternoon = simpleDateFormat.parse("18:00");

							// Horaire du rdv à confirmer au format HH:mm
							String simpleDateFormatToConfirm = simpleDateFormat.format(dateToConfirm);
						    Date timeToConfirm = simpleDateFormat.parse(simpleDateFormatToConfirm);
						    
						    if ((timeToConfirm.after(TimeStartMorning) && timeToConfirm.before(TimeStopMorning))
						    		|| (timeToConfirm.after(TimeStartAfternoon) && timeToConfirm.before(TimeStopAfternoon))) {
						    	
						    	// Après vérification heures de travail création d'une nouvelle consultation associé à la réservation du rdv
						    	Consultation consultationConfirmed = new Consultation(reservationToConfirm);
						    	log.info("creation consultation OK.");
								consultationRepository.save(consultationConfirmed);
								log.info("save consultation OK.");

								// Rdv confirmé ajout de la consultation dans la liste du medecin pour que ce soit dans son planning
								listeConsultation.add(consultationConfirmed);
								medecinConcerned.setConsultations(listeConsultation);
								if (listeConsultation == medecinConcerned.getConsultations()) {
									medecinRepository.save(medecinConcerned);
									log.info("save medecin OK.");
									
									// Changement du status de la réservation pour qu'elle ne soit plus dispo et que le patient sache qu'elle a été confirmée
									reservationToConfirm.setStatus(true);
									if (reservationToConfirm.isStatus() == true) {
										reservationRepository.save(reservationToConfirm);
										log.info("save reservation OK.");
										return medecinConcerned.getConsultations();
									}
									else {
										log.warn("Erreur méthode 'confirmerRdv': setStatuss sur Reservation a échoué.");
										throw new ReservationNotSuccessException("Reservation n'a pas pu réaliser la MAJ du status: set échoué");
									}
								}
								else {
									log.warn("Erreur méthode 'confirmerRdv': setConsultations sur Medecin a échoué.");
									throw new MedecinNotSuccessException("Medecin n'a pas pu réaliser la MAJ de la liste Consultation: set échoué");
								}
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}

					} else {
						log.warn("medecin déjà en rdv à ce créneau en choisir un autre");
					}
				} else {
					log.warn("votre réservation est déjà prise en compte");
				}
			}
			else {
				log.warn("Erreur méthode 'confirmerRdv': idReservation null.");
				throw new ReservationNotFoundException("Reservation non trouvé: idReservation = null");
			}
		} catch (ReservationNotFoundException | MedecinNotSuccessException | ReservationNotSuccessException me) {
			me.printStackTrace();
			me.getMessage();
			
		}
		return null;
	}

	@Override
	public Map<Consultation, Date> consulterPlanning(Long idMedecin) throws MedecinNotFoundException {
		try {
			log.info("Service spécifique du Medecin : méthode consulterPlanning appelée.");
			if (idMedecin != null) {
				Medecin medecinConcerned = medecinRepository.findById(idMedecin).get();
				if (medecinConcerned != null) {
					log.info("Appel repo medecin OK.");
					List<Consultation> listeConsultations = medecinConcerned.getConsultations();
					Map<Consultation, Date> planningConsultations = new HashMap<>();
					for (int i = 0; i < listeConsultations.size(); i++) {
						Consultation consultationPlanned = listeConsultations.get(i);
						planningConsultations.put(consultationPlanned, consultationPlanned.getReservation().getDateRervation());
					}
					return planningConsultations;
				}
				else {
					log.warn("Erreur méthode 'consulterPlanning': repo trouve pas objet Medecin");
					throw new MedecinNotFoundException("Medecin non trouvé avec le repo");
				}
			}
			else {
				log.warn("Erreur méthode 'consulterPlanning': idMedecin null.");
				throw new MedecinNotFoundException("Mecedin non trouvé: idMedecin = null");
			}
		} catch (MedecinNotFoundException mnfe) {
			mnfe.printStackTrace();
			mnfe.getMessage();
		}
		return null;
	}

}
