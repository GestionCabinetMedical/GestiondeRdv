package com.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.IPatientController;
import com.dto.ResponseDto;
import com.entity.FichesMedicales;
import com.entity.Patient;
import com.entity.Reservation;
import com.service.IPatientService;
import com.service.IReservationService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe controller {@code PatientController} spécifique de Patient qui hérite
 * de la classe générique {@code DaoControllerImpl} et implémente l'interface
 * spécifique {@code IPatientController}.
 * 
 * @author Sophie Lahmar
 * @see DaoControllerImpl
 * @see IPatientController
 *
 */
@RestController
@RequestMapping(value = "/patient-rest")
@Slf4j
public class PatientController extends DaoControllerImpl<Patient> implements IPatientController {

	@Autowired
	private IPatientService service;

	@Autowired
	private IReservationService reservationService;

	@Override
	@RequestMapping
	public void connexion(String login, String mdp) {
		// TODO : implémenter la méthode
		log.info("Controller spécifique de Patient : méthode connexion appelée.");

	}

	@Override
	@PutMapping
	public ResponseDto<Patient> modifierProfil(Patient patientUpdated) {
		// TODO : implémenter la méthode
		log.info("Controller spécifique de Patient : méthode modifier Profil appelée.");

		return null;
	}

	@Override
	@GetMapping
	public ResponseDto<List<Reservation>> consulterPlanning() {
		// TODO : implémenter la méthode
		log.info("Controller spécifique de Patient : méthode consulter Planning appelée.");
		List<Reservation> reservations = service.findAllReservations();
		return null;
	}

	@Override
	@PostMapping
	public ResponseDto<Reservation> reserverRdv(Reservation reservation) {
		// TODO : implémenter la méthode
		log.info("Controller spécifique de Patient : méthode reserver Rdv appelée.");

		return null;
	}

	@Override
	@PutMapping
	public ResponseDto<Reservation> modifierRdv(Reservation reservation) {
		// TODO : implémenter la méthode
		log.info("Controller spécifique de Patient : méthode modifier Rdv appelée.");

		return null;
	}

	@Override
	@GetMapping
	public ResponseDto<FichesMedicales> consulterFicheMedicale(Long id) {
		// TODO : implémenter la méthode
		log.info("Controller spécifique de Patient : méthode consulter Fiche Medicale appelée.");

		return null;
	}

	@Override
	public void remplirQuestionnaireSatisfaction() {
		// TODO : implémenter la méthode
		log.info("Controller spécifique de Patient : méthode remplir Questionnaire Satisfaction appelée.");

	}

}
