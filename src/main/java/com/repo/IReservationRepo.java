package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.Reservation;

/**
 * Interface repository {@code IReservationRepo} spécifique de
 * {@link Reservation} qui hérite de l'interface générique {@code IDaoRepo}.
 * 
 * @author Sophie Lahmar
 * @see IDaoRepo
 *
 */
@Repository
public interface IReservationRepo extends IDaoRepo<Reservation> {

}
