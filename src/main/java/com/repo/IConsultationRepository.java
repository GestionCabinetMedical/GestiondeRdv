package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.Consultation;

/**
 * @author Jonathan Rachwal
 * Interface de Consultation Repository qui permet la communication avec la table Consultation de la BDD
 * Interface etendue de IDaoRepository
 *
 */
@Repository
public interface IConsultationRepository extends IDaoRepo<Consultation>{

}