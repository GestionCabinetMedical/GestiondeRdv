package com.service;

import java.util.List;

import com.entity.FichesMedicales;
import com.entity.Patient;
import com.exception.notfound.FichesMedicalesNotFoundException;

/**
 * Interface service {@code IPatientService} spécifique de {@link Patient} qui
 * étend de l'interface générique {@code IDaoService}.
 * 
 * @author Sophie Lahmar
 * @see IDaoService
 *
 */
public interface IPatientService extends IDaoService<Patient> {

	/**
	 * Méthode permettant de rechercher un patient par son identifiant et son mot de
	 * passe.
	 * 
	 * @param identifiant Identifiant du patient recherché.
	 * @param mdp         Mot de passe du patient recherché.
	 * @return Le patient correspondant à l'identifiant et au mot de passe entrés.
	 */
	public Patient findByIdentifiantAndMotDePasse(String identifiant, String mdp);

	/**
	 * Méthode permettant la recherche d'un patient par son nom et son prénom.
	 * 
	 * @param nom    Nom du patient recherché.
	 * @param prenom Prénom du patient recherché.
	 * @return Le patient correspondant au nom et prénom entrés.
	 */
	public Patient findByNomAndPrenom(String nom, String prenom);

	/**
	 * Méthode permettant au patient de consulter sa liste de fiches médicales.
	 * 
	 * @param id Id du patient.
	 * @return Une liste de fiches médicales d'un patient.
	 * @throws FichesMedcialesNotFoundException
	 */
	public List<FichesMedicales> consulterFicheMedicale(Long id) throws FichesMedicalesNotFoundException;

}
