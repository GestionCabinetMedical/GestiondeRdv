package com.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
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
	
	@Query (value = "SELECT * FROM reservation R INNER JOIN consultation C on R.id_reservation = C.fk_reservation  WHERE (R.status = true AND C.fk_medecin = :idMedecin AND R.date_reservation =:dateResa)",nativeQuery = true)
	public List<Reservation> findAllResaParDateEtMedecin(Date dateResa, Long idMedecin);

}
