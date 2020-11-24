package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.Patient;

/**
 * Interface repository {@code IPatientRepo} spécifique de {@link Patient} qui
 * hérite de l'interface générique {@code IDaoRepo}.
 * 
 * @author Sophie Lahmar
 * @see IDaoRepo
 *
 */
@Repository
public interface IPatientRepo extends IDaoRepo<Patient> {

	/**
	 * Méthode permettant de rechercher un patient par son identifiant et son mot de
	 * passe.
	 * 
	 * @param identifiant Identifiant du patient recherché.
	 * @param mdp         Mot de passe du patient recherché.
	 * @return Le patient recherché.
	 */
	public Patient findByIdentifiantAndMotDePasse(String identifiant, String mdp);

	/**
	 * Méthode permettant de rechercher un patient par son nom de famille et son
	 * prénom.
	 * 
	 * @param nom    Nom du patient recherché.
	 * @param prenom Prénom du patient recherché.
	 * @return Le patient recherché.
	 */
	public Patient findByNomAndPrenom(String nom, String prenom);

	/**
	 * Méthode permettant de vérifier l'existence d'un patient dans la base de
	 * données avec cet identifiant.
	 * 
	 * @param identifiant Identifiant du patient.
	 * @return True si l'identifiant du patient existe déjà, false sinon.
	 */
//	public boolean existsByIdentifiant(String identifiant);

	/**
	 * Méthode permettant de vérifier l'existence d'un patient dans la base de
	 * données avec ce nom et ce prénom.
	 * 
	 * @param name Nom de famille et prénom du patient.
	 * @return True si le nom de famille et le prénom du patient existent déjà,
	 *         false sinon.
	 */
//	public boolean existsByNomAndPrenom(String name);

}
