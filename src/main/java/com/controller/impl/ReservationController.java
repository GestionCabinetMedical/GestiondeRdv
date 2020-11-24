package com.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ResponseDto;
import com.entity.Reservation;
import com.exception.notfound.ReservationNotFoundException;
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
	 */
	@GetMapping(value = "/consulterplanning")
	public ResponseDto<List<Reservation>> findReservationsDispo() throws ReservationNotFoundException {
		log.info("Controller spécifique de Reservation : méthode findReservationsDispo appelée.");
		List<Reservation> listeRes = service.findReservationsDispo();
		return makeListResponse(listeRes);
	}

}
