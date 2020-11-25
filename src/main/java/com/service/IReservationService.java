package com.service;

import java.util.Date;
import java.util.List;

import com.entity.Reservation;
import com.enums.HeureRdv;
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
	
	/**
	 * Méthode permettant de connaître la liste des rdv dispo par medecin et par date.
	 * @param date La date concernée.
	 * @param idMedecin L'id du medecin concerné.
	 * @return Une liste d'Heure Rdv correspondant aux heures disponibles de rendez-vous.
	 * @throws ReservationNotFoundException
	 */
	public List<HeureRdv> findResaParDateParMedecin(Date date, Long idMedecin) throws ReservationNotFoundException ;
}
