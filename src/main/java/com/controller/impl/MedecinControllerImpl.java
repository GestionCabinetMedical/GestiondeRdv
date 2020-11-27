package com.controller.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ConnectedUserDto;
import com.dto.ConnexionDto;
import com.dto.ResponseDto;
import com.entity.Consultation;
import com.entity.Medecin;
import com.entity.Reservation;
import com.enums.Role;
import com.exception.notfound.MedecinNotFoundException;
import com.exception.notsuccess.ConnectedUserDtoNotSuccessException;
import com.exception.notsuccess.ConnectedUserNotSuccessException;
import com.exception.notsuccess.ResponseDtoNotSuccessException;
import com.exception.notsuccess.MedecinNotSuccessException;
import com.exception.notsuccess.ReservationNotSuccessException;
import com.exception.notsuccess.TokenNotSuccessException;
import com.security.ITokenManagement;
import com.service.impl.MedecinServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe controller {@code MedecinControllerImpl} spécifique de {@link Medecin}
 * qui étend de la classe générique {@code DaoControllerImpl}.
 *
 * @author Jonathan Rachwal
 * @see DaoControllerImpl
 *
 */
@RestController
@RequestMapping(value = "/gestion-rdv/medecin")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
@Slf4j
public class MedecinControllerImpl extends DaoControllerImpl<Medecin> {

	// ATTRIBUTS

	@Autowired
	MedecinServiceImpl medecinService;

	@Autowired
	ITokenManagement tokenManage;

	// METHODES

	/**
	 * Méthode permettant de vérifier l'existence d'un médecin par son identifiant.
	 *
	 * @param identifiant Identifiant du médecin recherché.
	 * @return Un médecin s'il existe déjà, null sinon.
	 * @throws MedecinNotFoundException
	 * @throws MedecinNotSuccessException
	 * @throws ResponseDtoNotSuccessException
	 */
	@GetMapping(value = "/identifiant")
	public ResponseDto<Medecin> existsByIdentifiant(@RequestParam(name = "identifiant") String identifiant)
			throws MedecinNotFoundException, MedecinNotSuccessException, ResponseDtoNotSuccessException {
		log.info("Controller spécifique de Medecin: méthode 'existsByIdentifiant' appelée.");
		Medecin medecin = medecinService.existsByIdentifiant(identifiant);
		return makeDtoResponse(medecin);
	}

	/**
	 * Méthode permettant de vérifier l'existence d'un médecin par son identifiant
	 * et son mot de passe (= connexion).
	 *
	 * @param username Identifiant du médecin recherché.
	 * @param mdp         Mot de passe du médecin recherché.
	 * @return Un médecin s'il existe déjà, null sinon.
	 * @throws MedecinNotFoundException
	 * @throws MedecinNotSuccessException
	 * @throws TokenNotSuccessException
	 * @throws ConnectedUserNotSuccessException
	 * @throws ConnectedUserDtoNotSuccessException
	 */
	@PostMapping(path = "/connexion")
	public ConnexionDto existsByIdentifiantAndMotDePasse(@RequestParam String username, @RequestParam String mdp)
			throws MedecinNotFoundException, MedecinNotSuccessException, ConnectedUserNotSuccessException,
			TokenNotSuccessException, ConnectedUserDtoNotSuccessException {
		log.info("Controller spécifique de Medecin: méthode 'existsByIdentifiantAndMotDePasse' appelée.");

		ConnexionDto connexionDto = new ConnexionDto();
		try {
			Medecin medecin = medecinService.existsByIdentifiantAndMotDePasse(username, mdp);
			if (medecin != null) {
				log.info("Medecin existant dans la BDD.");
				ConnectedUserDto medecinDto = makeConnectedUserDtoResponse(medecin);
				String token = tokenManage.makeMedecinSession(medecinDto);

				connexionDto.setUser(medecinDto);
				connexionDto.setToken(token);
				connexionDto.setError(false);
				connexionDto.setStatus(HttpStatus.SC_OK);
				return connexionDto;

			} else {
				log.info("Aucune entité Medecin ne correspond à ces entrées.");
				connexionDto.setUser(null);
				connexionDto.setToken(null);
				connexionDto.setError(true);
				connexionDto.setStatus(HttpStatus.SC_BAD_REQUEST);
				return connexionDto;
			}

		} catch (NullPointerException e) {
			log.info("Null Pointer Exception: " + e.getMessage());
			connexionDto.setUser(null);
			connexionDto.setToken(null);
			return connexionDto;
		}
	}

	/**
	 * Methode permettant au medecin de confirmer un rdv => modification d'une resa
	 * en status true
	 *
	 * @param Reservation Entite Reservation a confirmer.
	 * @return Booléen true si la modification a été effectué, false sinon.
	 * @throws ReservationNotSuccessException
	 */
	@PostMapping(value = "/confirmerRdv")
	public ResponseDto<Boolean> confirmerRdv(@RequestBody Reservation r)
			throws ReservationNotSuccessException, ResponseDtoNotSuccessException {
		log.info("Controller medecin : méthode confirmerRdv appelée");
		return makeBooleanResponse(medecinService.confirmerRdv(r));
	}

	/**
	 * Methode permettant au medecin de consulter ses demande de rendez-vous
	 *
	 * @param identifiant Identifiant du medecin.
	 * @return List<Consultation> qui comprend toutes les consultations en status
	 *         false du medecin.
	 * @throws MedecinNotFoundException
	 * @throws MedecinNotSuccessException
	 * @throws ResponseDtoNotSuccessException
	 */
	@GetMapping(value = "/consulterResa")
	public ResponseDto<List<Consultation>> consulterResa(@RequestParam String identifiant)
			throws MedecinNotFoundException, ResponseDtoNotSuccessException, MedecinNotSuccessException {
		log.info("Controller medecin : méthode consulterResa appelée");
		List<Consultation> planning = medecinService.consulterResa(identifiant);
		return makeListConsultationResponse(planning);
	}

	/**
	 * Methode permettant au medecin de consulter ses rdv prévus / consultations (=
	 * son planning).
	 *
	 * @param identifiant Identifiant du medecin.
	 * @return List<Consultation> qui comprend toutes les consultations en status
	 *         true du medecin.
	 * @throws MedecinNotFoundException
	 * @throws MedecinNotSuccessException
	 * @throws ResponseDtoNotSuccessException
	 */
	@GetMapping(value = "/consulterPlanning")
	public ResponseDto<List<Consultation>> consulterPlanning(@RequestParam String identifiant)
			throws MedecinNotFoundException, ResponseDtoNotSuccessException, MedecinNotSuccessException {
		log.info("Controller medecin : méthode consulter Planning appelée");
		return makeListConsultationResponse(medecinService.consulterPlanning(identifiant));
	}

	@GetMapping(value = "/consulterPlanningByDate")
	public ResponseDto<Map<String,List<Consultation>>> consulterPlanningByDate(@RequestParam String identifiant,String date)
			throws MedecinNotFoundException, ResponseDtoNotSuccessException, MedecinNotSuccessException , ParseException {
		log.info("Controller medecin : méthode consulter Planning appelée");
		return makeMapDateAndConsultationResponse(medecinService.consulterPlanningByDate(identifiant, date));
	}

	@GetMapping(path = "nom")
	public ResponseDto<List<Medecin>> findByNom(@RequestParam String nom)
			throws MedecinNotFoundException, ResponseDtoNotSuccessException {
		log.info("Controller medecin : méthode find by nom appelée");
		return makeListResponse(medecinService.findByNom(nom));
	}

	@GetMapping(path = "specialite")
	public ResponseDto<List<Medecin>> findBySpecialite(@RequestParam String specialite)
			throws MedecinNotFoundException, ResponseDtoNotSuccessException {
		log.info("Controller medecin : méthode find by nom appelée");
		return makeListResponse(medecinService.findBySpecialite(specialite));
	}

	/**
	 * Méthode permettant de créer une réponse de type ConnectedUserDto, et
	 * d'injecter les paramètres de connection d'un Medecin (identifiant et mdp)
	 * dans un medecinDto.
	 *
	 * @param medecin Instance de la classe Medecin.
	 * @return Un objet ConnectedUserDto.
	 * @throws ConnectedUserDtoNotSuccessException
	 */
	private ConnectedUserDto makeConnectedUserDtoResponse(Medecin medecin) throws ConnectedUserDtoNotSuccessException {
		try {
			ConnectedUserDto response = new ConnectedUserDto();
			if (medecin != null) {
				log.info("makeConnectedUserDtoResponse : medecin OK.");
				response.setRole(Role.MEDECIN);
				response.setIdentifiant(medecin.getIdentifiant());
				response.setMdp(medecin.getMotDePasse());
				response.setError(false);
				response.setMsg("Success !");
			} else {
				log.info("Erreur 'makeConnectedUserDtoResponse' : medecin null.");
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
	 * ResponseDto<List<Consultation>>, et d'envoyer en responseDto une liste de
	 * consultation pour un médecin afin de voir son planning
	 *
	 * @param liste List<Consultation> attribut d'un Medecin.
	 * @return ResponseDto<List<Consultation>>.
	 * @throws ResponseDtoNotSuccessException
	 */
	public ResponseDto<List<Consultation>> makeListConsultationResponse(List<Consultation> liste)
			throws ResponseDtoNotSuccessException {
		try {
			ResponseDto<List<Consultation>> resp = new ResponseDto<>();
			if (liste.isEmpty() == false) {
				log.info("makeListConsultationResponse : ResponseDto<List<Consultation>> Ok");
				resp.setError(false);
				resp.setBody(liste);
				resp.setStatus(HttpStatus.SC_OK);
			} else {
				log.info("makeListConsultationResponse : ResponseDto<List<Consultation>> Erreur");
				resp.setError(true);
				resp.setBody(null);
				resp.setStatus(HttpStatus.SC_BAD_REQUEST);
			}
			if (resp.getStatus() != 200 || resp.getStatus() != 400) {
				return resp;
			} else {
				log.warn("Erreur méthode 'makeListConsultationResponse': set du ResponseDto non fonctionnel.");
				throw new ResponseDtoNotSuccessException("Modification ResponseDto échouée");
			}
		} catch (ResponseDtoNotSuccessException dnse) {
			dnse.printStackTrace();
			dnse.getMessage();

		}
		return null;
	}

	/**
	 * Méthode permettant de créer une réponse de type ResponseDto<Map<Consultation,
	 * Date>>, et d'envoyer en responseDto un planning des consultations par date
	 * pour un médecin
	 *
	 * @param map Map<Consultation, Date> générer par consultation planning.
	 * @return Une ResponseDto<Map<Consultation, Date>>.
	 * @throws ResponseDtoNotSuccessException
	 */
	public ResponseDto<Map<Consultation, Date>> makeMapConsultationDateResponse(Map<Consultation, Date> map)
			throws ResponseDtoNotSuccessException {
		try {
			ResponseDto<Map<Consultation, Date>> resp = new ResponseDto<>();
			if (map.isEmpty() == false) {
				log.info("makeListConsultationResponse : ResponseDto<Map<Consultation, Date>> Ok");
				resp.setError(false);
				resp.setBody(map);
				resp.setStatus(HttpStatus.SC_OK);
			} else {
				log.info("makeListConsultationResponse : ResponseDto<Map<Consultation, Date>> Erreur");
				resp.setError(true);
				resp.setBody(null);
				resp.setStatus(HttpStatus.SC_BAD_REQUEST);
			}
			if (resp.getStatus() != 200 || resp.getStatus() != 400) {
				return resp;
			} else {
				log.warn("Erreur méthode 'makeMapConsultationDateResponse': set du ResponseDto non fonctionnel.");
				throw new ResponseDtoNotSuccessException("Modification ResponseDto échouée");
			}
		} catch (ResponseDtoNotSuccessException dnse) {
			dnse.printStackTrace();
			dnse.getMessage();

		}
		return null;
	}

	public ResponseDto<Map<String, List<Consultation>>> makeMapDateAndConsultationResponse(Map<String,List<Consultation>> map)
			throws ResponseDtoNotSuccessException {
		try {
			ResponseDto<Map<String, List<Consultation>>> resp = new ResponseDto<>();
			if (map.isEmpty() == false) {
				log.info("makeListConsultationResponse : ResponseDto<Map<Consultation, Date>> Ok");
				resp.setError(false);
				resp.setBody(map);
				resp.setStatus(HttpStatus.SC_OK);
			} else {
				log.info("makeListConsultationResponse : ResponseDto<Map<Consultation, Date>> Erreur");
				resp.setError(true);
				resp.setBody(null);
				resp.setStatus(HttpStatus.SC_BAD_REQUEST);
			}
			if (resp.getStatus() != 200 || resp.getStatus() != 400) {
				return resp;
			} else {
				log.warn("Erreur méthode 'makeMapConsultationDateResponse': set du ResponseDto non fonctionnel.");
				throw new ResponseDtoNotSuccessException("Modification ResponseDto échouée");
			}
		} catch (ResponseDtoNotSuccessException dnse) {
			dnse.printStackTrace();
			dnse.getMessage();

		}
		return null;
	}

}
