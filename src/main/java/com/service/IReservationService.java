package com.service;

import java.util.List;

import com.entity.Reservation;
import com.exception.notfound.ReservationNotFoundException;

/**
 * Interface service {@code IReservationService} spécifique de
 * {@link Reservation} qui étend de l'interface générique {@code IDaoService}.
 * 
 * @author Sophie Lahmar
 * @see IDaoService
 *
 */
public interface IReservationService extends IDaoService<Reservation> {

	/**
	 * Méthode permettant de rechercher une liste de réservations par son statut
	 * (validée ou non par un médecin).
	 * 
	 * @return Une liste de réservations disponibles.
	 * @throws ReservationNotFoundException 
	 */
	public List<Reservation> findReservationsDispo() throws ReservationNotFoundException;
}
