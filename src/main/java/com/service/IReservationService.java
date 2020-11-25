package com.service;

import java.util.List;

import com.entity.Reservation;
import com.enums.HeureRdv;
import com.exception.notsuccess.ReservationNotSuccessException;

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
	 * Méthode permettant de connaître la liste des rdv dispo par medecin et par date.
	 * @param date La date concernée.
	 * @param idMedecin L'id du medecin concerné.
	 * @return Une liste d'Heure Rdv correspondant aux heures disponibles de rendez-vous.
	 * @throws ReservationNotSuccessException 
	 */

	public List<HeureRdv> findResaParDateParMedecin(String date, Long idMedecin) throws ReservationNotSuccessException ;

}
