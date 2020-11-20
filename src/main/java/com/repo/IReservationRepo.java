package com.repo;

import java.util.List;

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

	public List<Reservation> findByStatus(boolean status);

}
