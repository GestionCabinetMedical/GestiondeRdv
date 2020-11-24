package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.Reservation;

/**
 * Interface repository {@code IReservationRepo} spécifique de
 * {@link Reservation} qui hérite de l'interface générique {@code IDaoRepo}.
 * Cette interface est responsable de la communication avec la table reservation
 * dans la base de données.
 * 
 * @author Sophie Lahmar
 * @see IDaoRepo
 *
 */
@Repository
public interface IReservationRepo extends IDaoRepo<Reservation> {

}
