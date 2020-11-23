package com.controller.impl;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ResponseDto;
import com.entity.FichesMedicales;
import com.entity.Patient;
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
@Slf4j
public class PatientController extends DaoControllerImpl<Patient> {

	// ATTRIBUTS

	@Autowired
	private PatientServiceImpl service;

	// METHODES

	/**
	 * Méthode permettant à un patient de se connecter à son espace personnel dans
	 * l'application.
	 * 
	 * @param login Identifiant associé au compte personnel du patient.
	 * @param mdp   Mot de passe pour entrer dans l'espace du patient.
	 */
	@RequestMapping
	public void connexion(String login, String mdp) {
		// TODO : implémenter la méthode
		log.info("Controller spécifique de Patient : méthode connexion appelée.");
	}

	/**
	 * Méthode permettant au patient de consulter sa liste de fiches médicales.
	 * 
	 * @param id Id du patient.
	 * @return Une liste de fiches médicales d'un patient.
	 */
	@GetMapping(value = "/consulterFichesMedicales/{id}")
	public ResponseDto<List<FichesMedicales>> consulterFicheMedicale(@PathVariable Long id) {
		log.info("Controller spécifique de Patient : méthode consulter Fiche Medicale appelée.");
		List<FichesMedicales> listeFiches = service.consulterFicheMedicale(id);
		return makeListFichesMedicalesResponse(listeFiches);
	}

	public ResponseDto<List<FichesMedicales>> makeListFichesMedicalesResponse(List<FichesMedicales> liste) {
		ResponseDto<List<FichesMedicales>> resp = new ResponseDto<>();
		if (liste.isEmpty() == false) {
			resp.setError(false);
			resp.setBody(liste);
			resp.setStatus(HttpStatus.SC_OK);
		} else {
			resp.setError(true);
			resp.setBody(null);
			resp.setStatus(HttpStatus.SC_BAD_REQUEST);
		}
		return resp;
	}

}
