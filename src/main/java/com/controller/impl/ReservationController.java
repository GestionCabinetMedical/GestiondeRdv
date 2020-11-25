package com.controller.impl;

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
import com.service.impl.ReservationServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe controller {@code ReservationController} spécifique de
 * {@link Reservation} qui hérite de la classe générique
 * {@code DaoControllerImpl}.
 * 
 * @author Sophie Lahmar, Maxime Rembert
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
	 * Méthode permettant de rechercher une liste d'enum d'HeureRdv par la date et l'id du Medecin
	 * (validée ou non par un médecin).
	 * 
	 * @return Une liste d'HeureRdv disponibles.
	 * @throws ReservationNotFoundException
	 */
	@GetMapping(value = "/consulterplanningRdv")
	public ResponseDto<List<HeureRdv>> findReservationsDispo(@RequestParam String date,@RequestParam Long idMedecin) throws ReservationNotFoundException {
		log.info("Controller spécifique de Reservation : méthode findReservationsDispo appelée.");
		List<HeureRdv> listeRes = service.findResaParDateParMedecin(date, idMedecin);
		return makeListHeureRdvResponse(listeRes);
	}
	
	
	/**
	 * @author Maxime Rembert
	 * 
	 * @param liste Liste d'instances de l'enum HeureRdv.
	 * @return ResponseDto contenant une liste de type HeureRdv.
	 */
	public ResponseDto<List<HeureRdv>> makeListHeureRdvResponse(List<HeureRdv> liste) {
		ResponseDto<List<HeureRdv>> resp = new ResponseDto<>();
		if (liste != null) {
			log.info("makeListResponse : ResponseDto<List<HeureRdv>> Ok");
			resp.setError(false);
			resp.setBody(liste);
			resp.setStatus(HttpStatus.SC_OK);
		} else {
			log.info("makeListResponse : ResponseDto<List<HeureRdv>> Erreur");
			resp.setError(true);
			resp.setBody(null);
			resp.setStatus(HttpStatus.SC_BAD_REQUEST);
		}
		return resp;
	}
	

}
