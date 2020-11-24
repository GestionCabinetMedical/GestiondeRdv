package com.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.FichesMedicales;
import com.entity.Patient;
import com.exception.notfound.FichesMedicalesNotFoundException;
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
	public Patient findByIdentifiantAndMotDePasse(String identifiant, String mdp) {
		// TODO : ajouter les exceptions !

		if (identifiant != null && mdp != null) {
			log.info("Service spécifique de Patient: méthode 'find By Identifiant And MotDePasse' appelée.");
			Patient patient = patientRepo.findByIdentifiantAndMotDePasse(identifiant, mdp);
			return patient;
		} else {
			log.warn("Erreur méthode 'find By Identifiant And MotDePasse': identifiant null ou mdp null.");
			return null;
		}
	}

	@Override
	public Patient findByNomAndPrenom(String nom, String prenom) {
		// TODO : ajouter les exceptions !

		if (nom != null && prenom != null) {
			log.info("Service spécifique de Patient: méthode 'find By Nom And Prenom' appelée.");
			Patient patient = patientRepo.findByNomAndPrenom(nom, prenom);
			return patient;
		} else {
			log.warn("Erreur méthode 'find By Nom And Prenom': nom null ou penom null.");
			return null;
		}
	}

	@Override
	public List<FichesMedicales> consulterFicheMedicale(Long idPatient) throws FichesMedicalesNotFoundException {
		try {
			log.info("Service spécifique de Patient : méthode 'consulter Fiche Medicale' appelée.");
			if (idPatient != null) {
				List<FichesMedicales> listeFiches = ficheRepo.findAll();
				if (listeFiches == null) {
					log.warn("Erreur méthode 'consulter Fiche Medicale': appel repo 'findAll' Not OK");
					throw new FichesMedicalesNotFoundException("List<FichesMedicales> findAll = null");
				}
				log.info("Appel repo OK.");
				listeFiches = ficheRepo.findAll().stream().filter(f -> f.getConsultation().getIdPatient() == idPatient)
						.collect(Collectors.toList());
				return listeFiches;
			} else {
				log.warn("Erreur méthode 'consulter Fiche Medicale': idPatient null.");
				throw new FichesMedicalesNotFoundException("List<FichesMedicales> pas trouvé idPatient = null");
			}
		} catch (FichesMedicalesNotFoundException fmnfe) {
			fmnfe.printStackTrace();
			fmnfe.getMessage();
		}
		return null;
	}

}
