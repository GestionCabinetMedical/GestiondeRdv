package com.controller.impl;

import java.util.Date;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ResponseDto;
import com.entity.Reservation;
import com.enums.HeureRdv;
import com.exception.notfound.ReservationNotFoundException;
import com.exception.notsuccess.ResponseDtoNotSuccessException;
import com.service.impl.ReservationServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe controller {@code ReservationController} spécifique de
 * {@link Reservation} qui hérite de la classe générique
 * {@code DaoControllerImpl}.
 * 
 * @author Sophie Lahmar
 * @see DaoControllerImpl
 * @see IReservationController
 *
 */
@RestController
@RequestMapping(value = "/reservation")
@Slf4j
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class ReservationController extends DaoControllerImpl<Reservation> {

	// ATTRIBUTS

	@Autowired
	private ReservationServiceImpl service;

	// METHODES

	/**
	 * Méthode permettant de rechercher une liste de réservations par son statut
	 * (validée ou non par un médecin).
	 * 
	 * @return Une liste de réservations disponibles.
	 * @throws ReservationNotFoundException
	 * @throws ResponseDtoNotSuccessException 
	 */
	@GetMapping(value = "/consulterplanning")
	public ResponseDto<List<Reservation>> findReservationsDispo() throws ReservationNotFoundException, ResponseDtoNotSuccessException {
		log.info("Controller spécifique de Reservation : méthode findReservationsDispo appelée.");
		List<Reservation> listeRes = service.findReservationsDispo();
		return makeListResponse(listeRes);
	}
	
	/**
	 * Méthode permettant de connaître les rdv disponible par jour et par medecin.
	 * @param idMedecin L'id du medecin concerné.
	 * @param date La date concernée.
	 * @return Un ResponseDto contenant un boolean eror, un body de liste Heure Rdv et un status Http response.
	 * @throws ReservationNotFoundException
	 */
	@GetMapping (path="getAllResaParDateEtMedecin/")
	public ResponseDto<List<HeureRdv>> findResaDispoParMedecin(@RequestParam Long idMedecin, @RequestParam  Date date ) 
			throws ReservationNotFoundException {
		log.info("Controller spécifique de Reservation : méthode find all resa dispo par medecin appelée.");
		return makeListHeureRdvResponse(service.findResaParDateParMedecin(date, idMedecin));
	}

	
	/**
	 * Méthode permettant de renvoyer un Response Dto contenant une liste d'Heure Rdv.
	 * @param liste Liste d'Heure Rdv à envoyer.
	 * @return Un ResponseDto contenant un boolean eror, un body de liste Heure Rdv et un status Http response.
	 */
	public ResponseDto<List<HeureRdv>> makeListHeureRdvResponse(List<HeureRdv> liste) {
		ResponseDto<List<HeureRdv>> resp = new ResponseDto<>();

		if (liste != null) {
			log.info("makeListResponse : ResponseDto<List<E>> Ok");
			resp.setError(false);
			resp.setBody(liste);
			resp.setStatus(HttpStatus.SC_OK);
		} else {
			log.info("makeListResponse : ResponseDto<List<E>> Erreur");
			resp.setError(true);
			resp.setBody(null);
			resp.setStatus(HttpStatus.SC_BAD_REQUEST);
		}
		return resp;
	}
	
}
