/**
 * 
 */
package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.FichesMedicales;

/**
 * @author Pauline Humbert
 * Interface de FichesMedicales Repository responsable de la communication avec la table fichesMédicales dans la base de données.
 * Elle étend de IDaoRepository.
 *
 */
@Repository
public interface IFichesMedicalesRepository extends IDaoRepo<FichesMedicales>{

}
