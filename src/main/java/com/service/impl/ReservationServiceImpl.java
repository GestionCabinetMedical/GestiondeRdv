package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Reservation;
import com.repo.IReservationRepo;
import com.service.IReservationService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe service {@code ReservationServiceImpl} spécifique de Reservation qui
 * hérite de la classe générique {@code DaoServiceImpl} et implémente
 * l'interface spécifique {@code IReservationService}.
 * 
 * @author Sophie Lahmar
 * @see DaoServiceImpl
 * @see IReservationService
 *
 */
@Service
@Slf4j
public class ReservationServiceImpl extends DaoServiceImpl<Reservation> implements IReservationService {

	@Autowired
	IReservationRepo repo;

	@Override
	public List<Reservation> findByStatus(boolean status) {
		log.info("Service spécifique de Reservation : méthode find By Status appelée.");
		if (status == false) {
			log.info("Appel repo OK.");
			return repo.findAll();
		}
		log.warn("Les réservations sont indisponibles.");
		return null;
	}

}
