package com.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Patient;
import com.exception.notsuccess.PatientNotSuccessException;

/**
 * Interface repository {@code IPatientRepo} spécifique de {@link Patient} qui
 * hérite de l'interface générique {@code IDaoRepo}. Cette interface est
 * responsable de la communication avec la table patient dans la base de
 * données.
 * 
 * @author Sophie Lahmar
 * @see IDaoRepo
 *
 */
@Repository
public interface IPatientRepo extends IDaoRepo<Patient> {

	/**
	 * Méthode permettant de rechercher un patient par son nom de famille et son
	 * prénom.
	 * 
	 * @param nom    Nom du patient recherché.
	 * @param prenom Prénom du patient recherché.
	 * @return Le patient recherché.
	 */
//	public Patient findByNomAndPrenom(String nom, String prenom);

	/**
	 * Méthode permettant de rechercher un patient par son identifiant.
	 * 
	 * @param identifiant Identifiant du patient recherché.
	 * @return Un patient correpondant à l'identifiant entré.
	 * @throws PatientNotSuccessException
	 */
	public Patient findByIdentifiant(String identifiant) throws PatientNotSuccessException;

	/**
	 * Méthode permettant de rechercher un patient par son identifiant et son mot de
	 * passe.
	 * 
	 * @param identifiant Identifiant du patient recherché.
	 * @param mdp         Mot de passe du patient recherché.
	 * @return Un patient correpondant à l'identifiant et au mot de passe entrés.
	 * @throws PatientNotSuccessException
	 */
	public Patient findByIdentifiantAndMotDePasse(String identifiant, String mdp) throws PatientNotSuccessException;

	/**
	 * Méthode permettant de rechercher un patient par son tout ou partie de son
	 * adresse sans tenir compte de la case
	 * 
	 * @param adresse L'adresse du patient recherché.
	 * @return Une liste de Patient correspondant à l'adresse recherché.
	 * @throws PatientNotSuccessException
	 */
	public List<Patient> findByAdresseContainingIgnoreCase(String adresse)throws PatientNotSuccessException;

	/**
	 * Méthode permettant de recherche un patient par tout ou partie de son nom sans
	 * tenir compte de la case.
	 * 
	 * @param nom Nom du patient recherché.
	 * @return Une liste de Patient correspondant au nom recherché.
	 * @throws PatientNotSuccessException
	 */
	public List<Patient> findByNomContainingIgnoreCase(String nom)throws PatientNotSuccessException;

}
