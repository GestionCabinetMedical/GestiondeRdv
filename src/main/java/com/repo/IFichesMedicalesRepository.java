/**
 * 
 */
package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.FichesMedicales;

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

}
