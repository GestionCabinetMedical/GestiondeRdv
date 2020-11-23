package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.Medecin;

/**
 * Interface repository {@code IMedecinRepository} spécifique de {@link Medecin}
 * étend de l'interface générique {@code IDaoRepo}. Cette interface permet la
 * communication avec la table medecin dans la base de données.
 * 
 * @author Jonathan Rachwal
 * @see IDaoRepo
 *
 */
@Repository
public interface IMedecinRepository extends IDaoRepo<Medecin> {

}