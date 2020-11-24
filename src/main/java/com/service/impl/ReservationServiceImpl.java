package com.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Reservation;
import com.exception.notfound.ReservationNotFoundException;
import com.repo.IReservationRepo;
import com.service.IReservationService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe service {@code ReservationServiceImpl} spécifique de
 * {@link Reservation} qui étend de la classe générique {@code DaoServiceImpl}
 * et implémente l'interface spécifique {@code IReservationService}.
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
	public List<Reservation> findReservationsDispo() throws ReservationNotFoundException {
		try {
			log.info("Service spécifique de Reservation : méthode find By Status appelée.");
			List<Reservation> listeReservations = repo.findAll();
			if (listeReservations != null) {
				log.info("Appel repo OK.");
				listeReservations = repo.findAll().stream().filter(r -> r.isStatus() == false)
						.collect(Collectors.toList());
				if (listeReservations != null) {
					return listeReservations;
				} else {
					log.warn("Tout est complet il n'y a pas de réservations disponibles");
				}
			} else {
				log.warn("Les réservations n'ont pas été trouvées.");
				throw new ReservationNotFoundException("List<Reservation> pas trouvée findAll=null ");
			}

		} catch (ReservationNotFoundException rnfe) {
			rnfe.printStackTrace();
			rnfe.getMessage();
		}
		return null;
	}

}
