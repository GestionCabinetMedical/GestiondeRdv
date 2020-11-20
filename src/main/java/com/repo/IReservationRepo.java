package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.Reservation;

/**
 * Interface {@code IReservationRepo} spécifique à Patient héritant de
 * l'interface générique {@code IDaoRepo}.
 * 
 * @author Sophie Lahmar
 *
 */
@Repository
public interface IReservationRepo extends IDaoRepo<Reservation> {

}
