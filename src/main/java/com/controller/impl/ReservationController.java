package com.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.IReservationController;
import com.dto.ResponseDto;
import com.entity.Reservation;
import com.service.impl.ReservationServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe controller {@code ReservationController} spécifique de Reservation qui
 * hérite de la classe générique {@code DaoControllerImpl} et implémente
 * l'interface spécifique {@code IReservationController}.
 * 
 * @author Sophie Lahmar
 * @see DaoControllerImpl
 * @see IReservationController
 *
 */
@RestController
@RequestMapping(value = "/reservation")
@Slf4j
public class ReservationController extends DaoControllerImpl<Reservation> implements IReservationController {

	@Autowired
	private ReservationServiceImpl service;

	@Override
	@GetMapping(value = "/consulterplanning/{status}")
	public ResponseDto<List<Reservation>> findByStatus(@PathVariable boolean status) {
		log.info("Controller spécifique de Reservation : méthode find By Status appelée.");
		List<Reservation> listeRes = service.findByStatus(status);
		return makeListResponse(listeRes);
	}

}
