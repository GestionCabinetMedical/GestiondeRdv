package com.controller;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.dto.ResponseDto;
import com.entity.Reservation;

/**
 * Interface controller {@code IReservationController} spécifique de
 * {@link Reservation} qui hérite de l'interface générique
 * {@code IDaoController}.
 * 
 * @author Sophie Lahmar
 * @see IDaoController
 *
 */
@NoRepositoryBean
public interface IReservationController extends IDaoController<Reservation> {

	/**
	 * Méthode permettant de rechercher une liste de réservations par son statut
	 * (validée ou non par un médecin).
	 * 
	 * @param status Etat de la réservation (false si disponible, true sinon).
	 * @return Une liste de réservations disponibles.
	 */
	public ResponseDto<List<Reservation>> findByStatus(boolean status);

}
