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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ConnectedUserDto;
import com.dto.ConnexionDto;
import com.dto.ResponseDto;
import com.entity.Consultation;
import com.entity.Medecin;
import com.enums.Role;
import com.exception.notfound.MedecinNotFoundException;
import com.exception.notfound.ReservationNotFoundException;
import com.exception.notsuccess.MedecinNotSuccessException;
import com.exception.notsuccess.ReservationNotSuccessException;
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

	// TODO: RESPONSEDTO A FAIRE

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
	 */
	@GetMapping(value = "/identifiant")
	public ResponseDto<Medecin> existsByIdentifiant(@RequestParam(name = "identifiant") String identifiant)
			throws MedecinNotFoundException, MedecinNotSuccessException {
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
	 */
	@PostMapping(path = "/connexion")
	public ConnexionDto existsByIdentifiantAndMotDePasse(@RequestParam String username, @RequestParam String mdp)
			throws MedecinNotFoundException, MedecinNotSuccessException {
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
	 * Méthode permettant de créer une réponse de type ConnectedUserDto, et
	 * d'injecter les paramètres de connection d'un Medecin (identifiant et mdp)
	 * dans un medecinDto.
	 *
	 * @param medecin Instance de la classe Medecin.
	 * @return Un objet ConnectedUserDto.
	 */
	private ConnectedUserDto makeConnectedUserDtoResponse(Medecin medecin) {
		ConnectedUserDto resp = new ConnectedUserDto();
		if (medecin != null) {
			log.info("makeConnectedUserDtoResponse : medecin OK.");
			resp.setRole(Role.MEDECIN);
			resp.setIdentifiant(medecin.getIdentifiant());
			resp.setMdp(medecin.getMotDePasse());
			resp.setError(false);
			resp.setStatus(HttpStatus.SC_OK);
		} else {
			log.info("Erreur 'makeConnectedUserDtoResponse' : medecin null.");
			resp.setRole(Role.NONE);
			resp.setError(true);
			resp.setStatus(HttpStatus.SC_BAD_REQUEST);
		}
		return resp;
	}

	/**
	 * Methode permettant au medecin de confirmer un rdv => ajout d'une consultation
	 * dans sa liste (=son planning)
	 *
	 * @param idReservation Long associé à la Réservation faite par un patient à
	 *                      réserver.
	 * @param idMedecin     Long associé à un medecin qui peut confirmer ou non le
	 *                      rdv.
	 * @param idPatient     Long associé à un patient qui a fait la Réservation.
	 * @return List<Consultation> d'un medecin avec la nouvelle consultation prévue
	 *         une fois la Réservation confirmée
	 * @throws ParseException
	 * @throws ReservationNotSuccessException
	 * @throws MedecinNotSuccessException
	 * @throws ReservationNotFoundException
	 */
	@GetMapping(value = "/confirmerRdv")
	public ResponseDto<List<Consultation>> confirmerRdv(@RequestParam Long idReservation, @RequestParam Long idMedecin)
			throws ParseException, ReservationNotFoundException, MedecinNotSuccessException,
			ReservationNotSuccessException {
		log.info("Controller medecin : méthode confirmerRdv appelée");
		List<Consultation> listeConsultations = medecinService.confirmerRdv(idReservation, idMedecin);
		return makeListConsultationResponse(listeConsultations);
	}

	/**
	 * Methode permettant au medecin de consulter ses rdv prévus / consultations =>
	 * son planning
	 *
	 * @param idMedecin Long associé à un medecin qui souhaite consulter le
	 *                  planning.
	 * @return Map<Consultation, Date> qui liste en Key une consultation et en
	 *         valeur la date de celle-ci
	 * @throws MedecinNotFoundException
	 */
	@GetMapping(value = "/consulterPlanning")
	public ResponseDto<Map<Consultation, Date>> consulterPlanning(@RequestParam Long idMedecin)
			throws MedecinNotFoundException {
		log.info("Controller medecin : méthode consulterPlanning appelée");
		Map<Consultation, Date> planning = medecinService.consulterPlanning(idMedecin);
		return makeMapConsultationDateResponse(planning);
	}

	public ResponseDto<List<Consultation>> makeListConsultationResponse(List<Consultation> liste) {
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
		return resp;
	}

	public ResponseDto<Map<Consultation, Date>> makeMapConsultationDateResponse(Map<Consultation, Date> map) {
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
		return resp;
	}

}
