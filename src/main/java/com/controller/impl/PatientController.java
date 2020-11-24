package com.controller.impl;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ResponseDto;
import com.entity.FichesMedicales;
import com.entity.Patient;
import com.exception.notfound.FichesMedicalesNotFoundException;
import com.service.impl.PatientServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe controller {@code PatientController} spécifique de {@link Patient} qui
 * hérite de la classe générique {@code DaoControllerImpl}.
 * 
 * @author Sophie Lahmar
 * @see DaoControllerImpl
 *
 */
@RestController
@RequestMapping(value = "/patient")
//@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
@Slf4j
public class PatientController extends DaoControllerImpl<Patient> {

	// ATTRIBUTS

	@Autowired
	PatientServiceImpl service;

	// METHODES

	/**
	 * Méthode permettant de rechercher un patient par son identifiant et son mot de
	 * passe.
	 * 
	 * @param identifiant Identifiant du patient recherché.
	 * @param mdp         Mot de passe du patient recherché.
	 * @return Le patient correspondant à l'identifiant et au mot de passe entrés.
	 */
	@GetMapping(value = "/identifiant-mdp")
	public ResponseDto<Patient> findByIdentifiantAndMotDePasse(@RequestParam(name = "identifiant") String identifiant,
			@RequestParam(name = "motDePasse") String mdp) {
		log.info("Controller spécifique de Patient : méthode 'find By Identifiant And MotDePasse' appelée.");
		Patient patient = service.findByIdentifiantAndMotDePasse(identifiant, mdp);
		return makeDtoResponse(patient);
	}

	/**
	 * Méthode permettant la recherche d'un patient par son nom et son prénom.
	 * 
	 * @param nom    Nom du patient recherché.
	 * @param prenom Prénom du patient recherché.
	 * @return Le patient correspondant au nom et prénom entrés.
	 */
	@GetMapping(value = "/nom-prenom")
	public ResponseDto<Patient> findByNomAndPrenom(String nom, String prenom) {
		return null;
	}

	/**
	 * Méthode permettant au patient de consulter sa liste de fiches médicales.
	 * 
	 * @param id Id du patient.
	 * @return Une liste de fiches médicales d'un patient.
	 * @throws FichesMedcialesNotFoundException
	 */
	@GetMapping(value = "/consulterFichesMedicales/{id}")
	public ResponseDto<List<FichesMedicales>> consulterFicheMedicale(@PathVariable Long id)
			throws FichesMedicalesNotFoundException {
		log.info("Controller spécifique de Patient : méthode consulter Fiche Medicale appelée.");
		List<FichesMedicales> listeFiches = service.consulterFicheMedicale(id);
		return makeListFichesMedicalesResponse(listeFiches);
	}

	public ResponseDto<List<FichesMedicales>> makeListFichesMedicalesResponse(List<FichesMedicales> liste) {
		ResponseDto<List<FichesMedicales>> resp = new ResponseDto<>();
		if (liste.isEmpty() == false) {
			log.info("makeListFichesMedicalesResponse : ResponseDto<List<FichesMedicales>> Ok");
			resp.setError(false);
			resp.setBody(liste);
			resp.setStatus(HttpStatus.SC_OK);
		} else {
			log.info("makeListFichesMedicalesResponse : ResponseDto<List<FichesMedicales>> Erreur");
			resp.setError(true);
			resp.setBody(null);
			resp.setStatus(HttpStatus.SC_BAD_REQUEST);
		}
		return resp;
	}

}
