/**
 * 
 */
package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.Consultation;
import com.entity.FichesMedicales;
import com.exception.notsuccess.ConsultationNotSuccessException;
import com.exception.notsuccess.FichesMedicalesNotSuccessException;

/**
 * Interface repository {@code IFichesMedicalesRepository} spécifique de
 * {@link FichesMedicales} étend de l'interface générique {@code IDaoRepo}.
 * Cette interface est responsable de la communication avec la table
 * fiches_medicales dans la base de données.
 * 
 * @author Pauline Humbert
 * @see IDaoRepo
 *
 */
@Repository
public interface IFichesMedicalesRepository extends IDaoRepo<FichesMedicales> {
	
	/**
	 * Méthode permettant de rechercher une FichesMedicales par une consultation associée.
	 * 
	 * @param c Consultation de la fiche médicale recherchée.
	 * @return Une FichesMedicales correpondante.
	 * @throws FichesMedicalesNotSuccessException
	 */
	public FichesMedicales findByConsultation (Consultation c) throws FichesMedicalesNotSuccessException;

}
