package com.controller.impl;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.exception.notsuccess.ConnectedUserDtoNotSuccessException;
import com.exception.notsuccess.ConnectedUserNotSuccessException;
import com.exception.notsuccess.ConsultationNotSuccessException;
import com.exception.notsuccess.ResponseDtoNotSuccessException;
import com.exception.notsuccess.FichesMedicalesNotSuccessException;
import com.exception.notsuccess.PatientNotSuccessException;
import com.exception.notsuccess.ReservationNotSuccessException;
import com.exception.notsuccess.TokenNotSuccessException;
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
@RequestMapping(value = "/gestion-rdv/patient")
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
	 * @throws ResponseDtoNotSuccessException
	 */
	@GetMapping(value = "/identifiant")
	public ResponseDto<Patient> existsByIdentifiant(@RequestParam(name = "identifiant") String identifiant)
			throws PatientNotFoundException, PatientNotSuccessException, ResponseDtoNotSuccessException {
		log.info("Controller spécifique de Patient: méthode 'existsByIdentifiant' appelée.");
		Patient patient = patientService.existsByIdentifiant(identifiant);
		return makeDtoResponse(patient);
	}

	/**
	 * Méthode permettant de vérifier l'existence d'un patient par son identifiant
	 * et son mot de passe (= connexion).
	 *
	 * @param username Identifiant du patient recherché.
	 * @param mdp      Mot de passe du patient recherché.
	 * @return Un patient s'il existe déjà, null sinon.
	 * @throws PatientNotFoundException
	 * @throws PatientNotSuccessException
	 * @throws TokenNotSuccessException
	 * @throws ConnectedUserNotSuccessException
	 * @throws ConnectedUserDtoNotSuccessException
	 */
	@GetMapping(path = "/connexion")
	public ConnexionDto existsByIdentifiantAndMotDePasse(@RequestParam String username, @RequestParam String mdp)
			throws PatientNotFoundException, PatientNotSuccessException, ConnectedUserNotSuccessException,
			TokenNotSuccessException, ConnectedUserDtoNotSuccessException {
		log.info("Controller spécifique de Patient: méthode 'existsByIdentifiantAndMotDePasse' appelée.");

		ConnexionDto connexionDto = new ConnexionDto();
		try {
			Patient patient = patientService.existsByIdentifiantAndMotDePasse(username, mdp);
			if (patient != null) {
				log.info("Patient existant dans la BDD.");
				ConnectedUserDto patientDto = makeConnectedUserDtoResponse(patient);
				String token = tokenManage.makePatientSession(patientDto);

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
	 * Méthode permettant au patient de consulter sa liste de fiches médicales.
	 *
	 * @param id Id du patient.
	 * @return Une liste de fiches médicales d'un patient.
	 * @throws FichesMedicalesNotSuccessException
	 * @throws ConsultationNotSuccessException
	 * @throws ReservationNotSuccessException
	 * @throws ResponseDtoNotSuccessException
	 * @throws FichesMedcialesNotFoundException
	 */
	@GetMapping(value = "/consulterFichesMedicales/{id}")
	public ResponseDto<List<FichesMedicales>> consulterFicheMedicale(@PathVariable Long id)
			throws FichesMedicalesNotFoundException, ReservationNotSuccessException, ConsultationNotSuccessException,
			FichesMedicalesNotSuccessException, ResponseDtoNotSuccessException {
		log.info("Controller spécifique de Patient : méthode consulter Fiche Medicale appelée.");
		List<FichesMedicales> listeFiches = patientService.consulterFicheMedicale(id);
		return makeListFichesMedicalesResponse(listeFiches);
	}

	/**
	 * Méthode permettant de rechercher un patient par son tout ou partie de son
	 * adresse sans tenir compte de la case
	 * 
	 * @param adresse L'adresse du patient recherché.
	 * @return Une liste de Patient correspondant à l'adresse recherché.
	 * @throws ResponseDtoNotSuccessException 
	 * @throws PatientNotFoundException
	 * @throws PatientNotSuccessException
	 */
	@GetMapping (path = "/adresse")
	public ResponseDto<List<Patient>> findPatientByAdresse(@RequestParam String adresse) throws PatientNotFoundException, ResponseDtoNotSuccessException, PatientNotSuccessException {
		log.info("Constroller specifique de Patient : méthode find Patient By Adresse appelée");
		return makeListResponse(patientService.findByAdresseContainingIgnoreCase(adresse));
	}
	
	/**
	 * Méthode permettant de recherche un patient par tout ou partie de son nom sans
	 * tenir compte de la case.
	 * 
	 * @param nom Nom du patient recherché.
	 * @return Une liste de Patient correspondant au nom recherché.
	 * @throws ResponseDtoNotSuccessException 
	 * @throws PatientNotFoundException
	 * @throws PatientNotSuccessException
	 */
	@GetMapping (path = "/nom")
	public ResponseDto<List<Patient>> findPatientByNom(@RequestParam String nom) throws PatientNotFoundException, ResponseDtoNotSuccessException, PatientNotSuccessException {
		log.info("Constroller specifique de Patient : méthode find Patient By Nom appelée");
		return makeListResponse(patientService.findByNomContainingIgnoreCase(nom));
	}
	
	
	/**
	 * Méthode permettant de créer une réponse de type ConnectedUserDto, et
	 * d'injecter les paramètres de connection d'un Patient (identifiant et mdp)
	 * dans un patientDto.
	 *
	 * @param patient Instance de la classe Patient.
	 * @return Un objet ConnectedUserDto.
	 * @throws ConnectedUserDtoNotSuccessException
	 */
	private ConnectedUserDto makeConnectedUserDtoResponse(Patient patient) throws ConnectedUserDtoNotSuccessException {
		try {
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
			if (response.getMsg() != null) {
				return response;
			} else {
				log.warn("Erreur méthode 'makeConnectedUserDtoResponse': set du ConnectedUserDto non fonctionnel.");
				throw new ConnectedUserDtoNotSuccessException("Modification ConnectedUserDto échouée");
			}
		} catch (ConnectedUserDtoNotSuccessException dnse) {
			dnse.printStackTrace();
			dnse.getMessage();

		}
		return null;
	}

	/**
	 * Méthode permettant de créer une réponse de type
	 * ResponseDto<List<FichesMedicales>>, et d'envoyer en responseDto une liste de
	 * fiches médicales concernant des consultations
	 *
	 * @param liste List<FichesMedicales> attribut de plusieurs consultations.
	 * @return ResponseDto<List<FichesMedicales>>.
	 * @throws ResponseDtoNotSuccessException
	 */
	public ResponseDto<List<FichesMedicales>> makeListFichesMedicalesResponse(List<FichesMedicales> liste)
			throws ResponseDtoNotSuccessException {
		try {
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
			if (resp.getStatus() != 200 || resp.getStatus() != 400) {
				return resp;
			} else {
				log.warn("Erreur méthode 'makeListFichesMedicalesResponse': set du ResponseDto non fonctionnel.");
				throw new ResponseDtoNotSuccessException("Modification ResponseDto échouée");
			}
		} catch (ResponseDtoNotSuccessException dnse) {
			dnse.printStackTrace();
			dnse.getMessage();

		}
		return null;
	}

}
