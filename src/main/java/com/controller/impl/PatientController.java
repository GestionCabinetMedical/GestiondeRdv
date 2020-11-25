package com.controller.impl;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ConnectedUserDto;
import com.dto.ConnexionDto;
import com.dto.ResponseDto;
import com.entity.FichesMedicales;
import com.entity.Patient;
import com.enums.Role;
import com.exception.notfound.FichesMedicalesNotFoundException;
import com.exception.notfound.PatientNotFoundException;
import com.exception.notsuccess.ConsultationNotSuccessException;
import com.exception.notsuccess.FichesMedicalesNotSuccessException;
import com.exception.notsuccess.PatientNotSuccessException;
import com.exception.notsuccess.ReservationNotSuccessException;
import com.security.ITokenManagement;
import com.service.IPatientService;

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
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
@Slf4j
public class PatientController extends DaoControllerImpl<Patient> {

	// ATTRIBUTS

	@Autowired
	IPatientService patientService;

	@Autowired
	ITokenManagement tokenManage;

	// METHODES

	/**
	 * Méthode permettant de vérifier l'existence d'un patient par son identifiant.
	 * 
	 * @param identifiant Identifiant du patient recherché.
	 * @return Un patient s'il existe déjà, null sinon.
	 * @throws PatientNotFoundException
	 * @throws PatientNotSuccessException 
	 */
	@GetMapping(value = "/identifiant")
	public ResponseDto<Patient> existsByIdentifiant(@RequestParam(name = "identifiant") String identifiant)
			throws PatientNotFoundException, PatientNotSuccessException {
		log.info("Controller spécifique de Patient: méthode 'existsByIdentifiant' appelée.");
		Patient patient = patientService.existsByIdentifiant(identifiant);
		return makeDtoResponse(patient);
	}

	/**
	 * Méthode permettant de vérifier l'existence d'un patient par son identifiant
	 * et son mot de passe.
	 * 
	 * @param identifiant Identifiant du patient recherché.
	 * @param mdp         Mot de passe du patient recherché.
	 * @return Un patient s'il existe déjà, null sinon.
	 * @throws PatientNotFoundException
	 * @throws PatientNotSuccessException 
	 */
	@PostMapping(path = "/identifiant-mdp")
	public ConnexionDto existsByIdentifiantAndMotDePasse(@RequestBody String[] tableau)
			throws PatientNotFoundException, PatientNotSuccessException {
		log.info("Controller spécifique de Patient: méthode 'existsByIdentifiantAndMotDePasse' appelée.");

		ConnexionDto connexionDto = new ConnexionDto();

		try {
			String username = tableau[0];
			String mdp = tableau[1];
			Patient patient = patientService.existsByIdentifiantAndMotDePasse(username, mdp);

			if (patient != null) {
				log.info("Patient existant dans la BDD.");
				ConnectedUserDto patientDto = makeConnectedUserDtoResponse(patient);
				String token = tokenManage.makeAdminSession(patientDto);

				connexionDto.setUser(patientDto);
				connexionDto.setToken(token);
				connexionDto.setError(false);
				connexionDto.setStatus(HttpStatus.SC_OK);
				return connexionDto;

			} else {
				log.info("Aucune entité Patient ne correspond à ces entrées.");
				connexionDto.setUser(null);
				connexionDto.setToken(null);
				connexionDto.setError(true);
				connexionDto.setStatus(HttpStatus.SC_BAD_REQUEST);
				return connexionDto;
			}

		} catch (NullPointerException e) {
			log.info("Null Pointer Exception : " + e.getMessage());
			connexionDto.setUser(null);
			connexionDto.setToken(null);
			return connexionDto;
		}
	}

	/**
	 * Méthode permettant de créer une réponse de type ConnectedUserDto, et
	 * d'injecter les paramètres de connection d'un Patient (identifiant et mdp)
	 * dans un patientDto.
	 * 
	 * @param patient Instance de la classe Patient.
	 * @return Un objet ConnectedUserDto.
	 */
	private ConnectedUserDto makeConnectedUserDtoResponse(Patient patient) {
		ConnectedUserDto response = new ConnectedUserDto();
		if (patient != null) {
			log.info("makeConnectedUserDtoResponse : patient OK.");
			response.setRole(Role.PATIENT);
			response.setIdentifiant(patient.getIdentifiant());
			response.setMdp(patient.getMotDePasse());
			response.setError(false);
			response.setMsg("Success !");
		} else {
			log.info("Erreur 'makeConnectedUserDtoResponse' : patient null.");
			response.setRole(Role.NONE);
			response.setError(true);
			response.setMsg("Error: Bad request.");
		}
		return response;
	}

	/**
	 * Méthode permettant au patient de consulter sa liste de fiches médicales.
	 * 
	 * @param id Id du patient.
	 * @return Une liste de fiches médicales d'un patient.
	 * @throws FichesMedicalesNotSuccessException 
	 * @throws ConsultationNotSuccessException 
	 * @throws ReservationNotSuccessException 
	 * @throws FichesMedcialesNotFoundException
	 */
	@GetMapping(value = "/consulterFichesMedicales/{id}")
	public ResponseDto<List<FichesMedicales>> consulterFicheMedicale(@PathVariable Long id)
			throws FichesMedicalesNotFoundException, ReservationNotSuccessException, ConsultationNotSuccessException, FichesMedicalesNotSuccessException {
		log.info("Controller spécifique de Patient : méthode consulter Fiche Medicale appelée.");
		List<FichesMedicales> listeFiches = patientService.consulterFicheMedicale(id);
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
