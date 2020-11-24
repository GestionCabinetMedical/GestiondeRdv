package com.controller.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ResponseDto;
import com.entity.Consultation;
import com.entity.Medecin;
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
@RequestMapping(value = "/medecin")
@Slf4j
public class MedecinControllerImpl extends DaoControllerImpl<Medecin> {
	
	//TODO RESPOSEDTO A FAIRE
	
	// ATTRIBUTS

	@Autowired
	private MedecinServiceImpl medecinService;

	// METHODES

	/**
	 * Méthode permettant à un medecin de se connecter à son espace personnel dans
	 * l'application.
	 * 
	 * @param login Identifiant associé au compte personnel du medecin.
	 * @param mdp   Mot de passe pour entrer dans l'espace du medecin.
	 */
	@RequestMapping
	public void connexion(String login, String mdp) {
		// TODO : implémenter la méthode
		log.info("Controller spécifique de Medecin : méthode connexion appelée.");
	}
	
	/**
	 * Methode permettant au medecin de confirmer un rdv => ajout d'une consultation dans sa liste (=son planning)
	 * @param idReservation Long associé à la Réservation faite par un patient à réserver.
	 * @param idMedecin Long associé à un medecin qui peut confirmer ou non le rdv.
	 * @param idPatient Long associé à un patient qui a fait la Réservation.
	 * @return List<Consultation> d'un medecin avec la nouvelle consultation prévue une fois la Réservation confirmée
	 * @throws ParseException 
	 */
	@GetMapping(value="/confirmerRdv")
	public ResponseDto<List<Consultation>> confirmerRdv(@RequestParam Long idReservation, @RequestParam Long idMedecin, @RequestParam Long idPatient) throws ParseException {
		log.info("Controller medecin : méthode confirmerRdv appelée");
		List<Consultation> listeConsultations = medecinService.confirmerRdv(idReservation, idMedecin, idPatient);
		return makeListConsultationResponse(listeConsultations);
	}
	
	/**
	 * Methode permettant au medecin de consulter ses rdv prévus / consultations => son planning
	 * @param idMedecin Long associé à un medecin qui souhaite consulter le planning.
	 * @return Map<Consultation, Date> qui liste en Key une consultation et en valeur la date de celle-ci
	 *
	 */ 
	@GetMapping(value="/consulterPlanning")
	public ResponseDto<Map<Consultation, Date>> consulterPlanning(@RequestParam Long idMedecin) {
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