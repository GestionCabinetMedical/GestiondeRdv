package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.Medecin;

/**
 * @author Jonathan Rachwal
 * Interface de Medecin Repository qui permet la communication avec la table Medecin de la BDD
 * Interface etendue de IDaoRepository
 *
 */
@Repository
public interface IMedecinRepository extends IDaoRepo<Medecin>{

}