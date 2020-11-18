package com.service;

import com.entity.Patient;
import com.entity.Reservation;

/**
 * Interface service {@code IPatientService} spécifique de Patient héritant de
 * l'interface générique {@code IDaoService}.
 * 
 * @author Sophie Lahmar
 *
 */
public interface IPatientService extends IDaoService<Patient> {
	/**
	 * @author Sophie Lahmar
	 * 
	 */
	public void connexion();

	/**
	 * @author Sophie Lahmar
	 * 
	 */
	public void consulterPlanning();

	/**
	 * @author Sophie Lahmar
	 * 
	 * @return
	 */
	public Reservation reserverRdv();

	/**
	 * @author Sophie Lahmar
	 * 
	 * @return
	 */
	public Reservation modifierRdv();

	/**
	 * @author Sophie Lahmar
	 * 
	 */
	public void consulterFicheMedicale();

	/**
	 * @author Sophie Lahmar
	 * 
	 * @return
	 */
	public Patient modifierProfil();

	/**
	 * @author Sophie Lahmar
	 * 
	 */
	public void remplirQuestionnaireSatisfaction();

}
