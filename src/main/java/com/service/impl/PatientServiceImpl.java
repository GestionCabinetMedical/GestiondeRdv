package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Consultation;
import com.entity.FichesMedicales;
import com.entity.Patient;
import com.entity.Reservation;
import com.exception.notfound.FichesMedicalesNotFoundException;
import com.exception.notfound.PatientNotFoundException;
import com.repo.IConsultationRepository;
import com.repo.IFichesMedicalesRepository;
import com.repo.IPatientRepo;
import com.repo.IReservationRepo;
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

	@Autowired
	private IReservationRepo resaRepo;

	@Autowired
	private IConsultationRepository consultRepo;

	// METHODES

	@Override
	public Patient existsByIdentifiant(String identifiant) throws PatientNotFoundException {
		try {
			log.info("Service spécifique de Patient: méthode 'existsByIdentifiant' appelée.");
			if (identifiant != null) {
				log.info("Appel repo OK.");
				Patient patient = patientRepo.findByIdentifiant(identifiant);
				return patient;
			} else {
				log.warn("Erreur méthode 'existsByIdentifiant': identifiant null.");
				throw new PatientNotFoundException("Aucun Patient n'existe avec cet identifiant.");
			}
		} catch (PatientNotFoundException pnfe) {
			pnfe.printStackTrace();
			pnfe.getMessage();
		}
		return null;
	}

	@Override
	public Patient existsByIdentifiantAndMotDePasse(String identifiant, String mdp) throws PatientNotFoundException {
		try {
			log.info("Service spécifique de Admin: méthode 'existsByIdentifiantAndMotDePasse' appelée.");
			if (identifiant != null) {
				if (mdp != null) {
					log.info("Appel repo OK.");
					Patient patient = patientRepo.findByIdentifiantAndMotDePasse(identifiant, mdp);
					return patient;
				} else {
					log.warn("Erreur méthode 'existsByIdentifiantAndMotDePasse': mdp null.");
					throw new PatientNotFoundException("Patient non trouvé : mpd = null.");
				}
			} else {
				log.warn("Erreur méthode 'existsByIdentifiantAndMotDePasse': identifiant null et mdp null.");
				throw new PatientNotFoundException("Aucun Patient n'existe avec cet identifiant et ce mdp.");
			}
		} catch (PatientNotFoundException pnfe) {
			pnfe.printStackTrace();
			pnfe.getMessage();
		}
		return null;
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
				//trouver les fiches correspondant à un patient

				//trouver toutes les consultations d'un patient
				List<Reservation> listeResa = resaRepo.findAllByFkPatient(idPatient);
				//trouver toutes les consultations lié à cette consult
				List<Consultation> listeConsult = new ArrayList<>();
				for (Reservation r : listeResa) {
					listeConsult.add(consultRepo.findByReservation(r));
				}
				//trouver toutes les fiches médicales lié à cette consult
				for (Consultation c : listeConsult) {
					listeFiches.add(ficheRepo.findByConsultation(c));
				}
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
