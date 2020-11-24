package com.repo;

import java.util.Map;

import org.springframework.data.jpa.repository.Query;
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
	
	
	/**
	 * Requête sur la table medecin du nombre total de medecin dans BD
	 * @return int du nombre total de medecin dans la BD 
	 */
	@Query("SELECT COUNT(*) FROM medecin") 
    int totalDesMedecins();
	
	/**
	 * Requête sur la table medecin dans la BD du nombre total de medecin pour chaque specialite
	 * @return Map<String, Integer> key: specialité et value: nombre total de medecin pour cette specialite dans la BD
	 */
	@Query("SELECT specialite, COUNT(*)  FROM medecin GROUP BY specialite")
    Map<String, Integer> totalMedecinsParSpecialite();

}