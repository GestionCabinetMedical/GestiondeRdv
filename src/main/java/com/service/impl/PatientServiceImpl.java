package com.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.FichesMedicales;
import com.entity.Patient;
import com.entity.Reservation;
import com.exception.ReservationNotFoundException;
import com.repo.IFichesMedicalesRepository;
import com.repo.IPatientRepo;
import com.repo.IReservationRepo;
import com.service.IPatientService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Classe service {@code PatientServiceImpl} spécifique de Patient qui hérite de
 * la classe générique {@code DaoServiceImpl} et implémente l'interface
 * spécifique {@code IPatientService}.
 * 
 * @author Sophie Lahmar
 * @see DaoServiceImpl
 * @see IPatientService
 *
 */
@Service
@Slf4j
public class PatientServiceImpl extends DaoServiceImpl<Patient> implements IPatientService {

	@Autowired
	private IPatientRepo patientRepo;

	@Autowired
	IReservationRepo resRepo;

	@Autowired
	IFichesMedicalesRepository ficheRepo;

	@Override
	public void connexion(String login, String mdp) {
		// TODO : implémenter la méthode
		log.info("Service spécifique de Patient : méthode connection appelée.");
	}

	@Override
	public Patient modifierProfil(Patient patient) {
		log.info("Service spécifique de Patient : méthode modifier Profil appelée.");
		// TODO : implémenter la méthode
		return null;
	}

	@Override
	public List<Reservation> consulterPlanning() {
		log.info("Service spécifique de Patient : méthode consulter Planning appelée.");
		List<Reservation> rdvDispo = resRepo.findByStatus(false);
		log.info("Appel repo OK.");
		return rdvDispo;
	}

	@Override
	public Reservation reserverRdv(Reservation reservation) {
		log.info("Service spécifique de Patient : méthode reserver Rdv appelée.");
		if (reservation != null && reservation.isStatus() == false) {
			log.info("Appel repo OK.");
			return resRepo.save(reservation);
		}
		log.warn("Erreur méthode reserver Rdv: reservation null et non disponible.");
		return null;
	}

	@Override
	public void modifierRdv(Reservation newReservation) throws ReservationNotFoundException {
		log.info("Service spécifique de Patient : méthode modifier Rdv appelée.");
		Optional<Reservation> optionalRes = resRepo.findById(newReservation.getIdReservation());
		if (!optionalRes.isPresent()) {
			throw new ReservationNotFoundException("Réservation introuvable.");
		} else {
			Reservation reservationToUpdate = optionalRes.get();
			reservationToUpdate.setDateRervation(newReservation.getDateRervation());
			reservationToUpdate.setUrgent(newReservation.isUrgent());
			resRepo.save(reservationToUpdate);
		}
	}

	@Override
	public List<FichesMedicales> consulterFicheMedicale(Long idPatient) {
		log.info("Service spécifique de Patient : méthode consulter Fiche Medicale appelée.");
		List<FichesMedicales> listeFiches = ficheRepo.findAll().stream()
				.filter(f -> f.getConsultation().getIdPatient() == idPatient).collect(Collectors.toList());
		log.info("Appel repo OK.");
		return listeFiches;
	}

	@Override
	public void remplirQuestionnaireSatisfaction() {
		log.info("Service spécifique de Patient : méthode remplir Questionnaire Satisfaction appelée.");
		// TODO : implémenter la méthode
	}

}
