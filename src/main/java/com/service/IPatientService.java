package com.service;

import java.util.List;

import com.entity.FichesMedicales;
import com.entity.Patient;

/**
 * Interface service {@code IPatientService} spécifique de {@link Patient}
 * qui étend de l'interface générique {@code IDaoService}.
 * 
 * @author Sophie Lahmar
 * @see IDaoService
 *
 */
public interface IPatientService extends IDaoService<Patient> {

	/**
	 * Méthode permettant à un patient de se connecter à son espace personnel dans
	 * l'application.
	 * 
	 * @param login Identifiant associé au compte personnel du patient.
	 * @param mdp   Mot de passe pour entrer dans l'espace du patient.
	 */
	public void connexion(String login, String mdp);

	/**
	 * Méthode permettant au patient de consulter sa liste de fiches médicales.
	 * 
	 * @param id Id du patient.
	 * @return Une liste de fiches médicales d'un patient.
	 */
	public List<FichesMedicales> consulterFicheMedicale(Long id);

}
