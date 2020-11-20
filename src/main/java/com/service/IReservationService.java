package com.service;

import java.util.List;

import com.entity.Reservation;

/**
 * Interface {@code IReservationService} spécifique de Reservation héritant de
 * l'interface générique {@code IDaoService}.
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
	 * @param status Etat de la réservation (false si disponible, true sinon).
	 * @return Une liste de réservations disponibles.
	 */
	public List<Reservation> findByStatus(boolean status);
}
