package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.FichesMedicales;
import com.entity.Patient;
import com.entity.Reservation;
import com.repo.IPatientRepo;
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

	@Override
	public void connexion(String login, String mdp) {
		// TODO : implémenter la méthode
		log.info("Service spécifique de Patient : méthode connection appelée.");
	}

	@Override
	public Patient modifierProfil(Patient patientUpdated) {
		log.info("Service spécifique de Patient : méthode modifier Profil appelée.");
		// TODO : implémenter la méthode
		return null;
	}

	@Override
	public List<Reservation> consulterPlanning() {
		log.info("Service spécifique de Patient : méthode consulter Planning appelée.");
		// TODO : implémenter la méthode
		return null;
	}

	@Override
	public Reservation reserverRdv(Reservation reservation) {
		log.info("Service spécifique de Patient : méthode reserver Rdv appelée.");
		// TODO : implémenter la méthode
		return null;
	}

	@Override
	public Reservation modifierRdv(Reservation reservation) {
		log.info("Service spécifique de Patient : méthode modifier Rdv appelée.");
		// TODO : implémenter la méthode
		return null;
	}

	@Override
	public FichesMedicales consulterFicheMedicale(Long id) {
		log.info("Service spécifique de Patient : méthode consulter Fiche Medicale appelée.");
		// TODO : implémenter la méthode
		return null;
	}

	@Override
	public void remplirQuestionnaireSatisfaction() {
		log.info("Service spécifique de Patient : méthode remplir Questionnaire Satisfaction appelée.");
		// TODO : implémenter la méthode
	}

}
