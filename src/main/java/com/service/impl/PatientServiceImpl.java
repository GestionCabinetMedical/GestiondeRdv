package com.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.entity.FichesMedicales;
import com.entity.Patient;
import com.repo.IFichesMedicalesRepository;
import com.repo.IPatientRepo;
import com.service.IPatientService;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe service {@code PatientServiceImpl} spécifique de {@link Patient} qui
 * étend de la classe générique {@code DaoServiceImpl} et implémente l'interface
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

	// ATTRIBUTS

	@Autowired
	private IPatientRepo patientRepo;

	@Autowired
	private IFichesMedicalesRepository ficheRepo;

	// METHODES

	@Override
	public void connexion(String login, String mdp) {
		// TODO : implémenter la méthode
		log.info("Service spécifique de Patient : méthode connection appelée.");
	}

	@Override
	public List<FichesMedicales> consulterFicheMedicale(Long idPatient) {
		log.info("Service spécifique de Patient : méthode consulter Fiche Medicale appelée.");
		if (idPatient != null) {
			log.info("Appel repo OK.");
			List<FichesMedicales> listeFiches = ficheRepo.findAll().stream()
					.filter(f -> f.getConsultation().getIdPatient() == idPatient).collect(Collectors.toList());
			return listeFiches;
		}
		log.warn("Erreur méthode consulter Fiche Medicale: idPatient null");
		return null;
	}

}
