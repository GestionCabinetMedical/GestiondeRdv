package com.service;

import java.util.List;

import com.entity.FichesMedicales;
import com.entity.Patient;
import com.exception.notfound.FichesMedicalesNotFoundException;
import com.exception.notfound.PatientNotFoundException;

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
	 * Méthode permettant de vérifier l'existence d'un patient par son identifiant.
	 * 
	 * @param identifiant Identifiant du patient recherché.
	 * @return Un patient s'il existe déjà, null sinon.
	 * @throws PatientNotFoundException 
	 */
	public Patient existsByIdentifiant(String identifiant) throws PatientNotFoundException;

	/**
	 * Méthode permettant de vérifier l'existence d'un patient par son identifiant
	 * et son mot de passe.
	 * 
	 * @param identifiant Identifiant du patient recherché.
	 * @param mdp         Mot de passe du patient recherché.
	 * @return Un patient s'il existe déjà, null sinon.
	 * @throws PatientNotFoundException
	 */
	public Patient existsByIdentifiantAndMotDePasse(String identifiant, String mdp) throws PatientNotFoundException;

	/**
	 * Méthode permettant au patient de consulter sa liste de fiches médicales.
	 * 
	 * @param id Id du patient.
	 * @return Une liste de fiches médicales d'un patient.
	 * @throws FichesMedcialesNotFoundException
	 */
	public List<FichesMedicales> consulterFicheMedicale(Long id) throws FichesMedicalesNotFoundException;

}
