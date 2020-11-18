package com.service.impl;

import com.entity.Patient;
import com.entity.Reservation;
import com.service.IPatientService;

/**
 * 
 * Classe service {@code PatientServiceImpl} spécifique de Patient qui hérite de
 * la classe générique {@code DaoServiceImpl} et implémente l'interface
 * spécifique {@code IPatientService}.
 * 
 * @author Sophie Lahmar
 *
 */
public class PatientServiceImpl extends DaoServiceImpl<Patient> implements IPatientService {

	/**
	 * @author Sophie Lahmar
	 * 
	 */
	@Override
	public void connexion() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @author Sophie Lahmar
	 * 
	 */
	@Override
	public void consulterPlanning() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @author Sophie Lahmar
	 * 
	 * @return
	 */
	@Override
	public Reservation reserverRdv() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @author Sophie Lahmar
	 * 
	 * @return
	 */
	@Override
	public Reservation modifierRdv() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @author Sophie Lahmar
	 * 
	 */
	@Override
	public void consulterFicheMedicale() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @author Sophie Lahmar
	 * 
	 * @return
	 */
	@Override
	public Patient modifierProfil() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @author Sophie Lahmar
	 * 
	 */
	@Override
	public void remplirQuestionnaireSatisfaction() {
		// TODO Auto-generated method stub
		
	}

}
