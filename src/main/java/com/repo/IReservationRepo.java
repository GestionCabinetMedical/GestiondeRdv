package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Reservation;

/**
 * Interface repository {@code IReservationRepo} spécifique de
 * {@link Reservation} qui hérite de l'interface générique {@code IDaoRepo}.
 * Cette interface est responsable de la communication avec la table reservation
 * dans la base de données.
 * 
 * @author Sophie Lahmar, Maxime Rembert
 * @see IDaoRepo
 *
 */
@Repository
public interface IReservationRepo extends IDaoRepo<Reservation> {

	
	/**
	 * Méthode retournant la liste de toutes les réservations d'un patient.
	 * @param idPatient Id du patient concerné.
	 * @return Une liste de reservations.
	 */
	@Query (value = "SELECT * FROM reservation WHERE fk_patient = :idPatient", nativeQuery = true)
	public List<Reservation> findAllByFkPatient(Long idPatient);
}
