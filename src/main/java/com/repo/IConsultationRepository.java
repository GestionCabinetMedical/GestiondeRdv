package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.Consultation;

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

}