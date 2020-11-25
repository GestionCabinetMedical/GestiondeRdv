package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.Consultation;
import com.entity.Reservation;
import com.exception.notsuccess.ConsultationNotSuccessException;

/**
 * Interface repository {@code IConsultationRepository} spécifique de
 * {@link Consultation} qui hérite de l'interface générique {@code IDaoRepo}.
 * Cette interface permet la communication avec la table consultation dans la
 * base de données.
 * 
 * @author Jonathan Rachwal
 * @see IDaoRepo
 *
 */
@Repository
public interface IConsultationRepository extends IDaoRepo<Consultation> {
	
	/**
	 * Méthode permettant de rechercher une Consultation par une réservation associée.
	 * 
	 * @param r Reservation de la consultation recherchée.
	 * @return Une Consultation correpondante.
	 * @throws ConsultationNotSuccessException
	 */
	public Consultation findByReservation(Reservation r) throws ConsultationNotSuccessException;

}