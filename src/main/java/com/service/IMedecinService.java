package com.service;

import com.entity.Medecin;

/**
 * @author Jonathan RACHWAL
 * Interface Medecin service qui extend de IDaoService
 * 
 *
 */
public interface IMedecinService extends IDaoService<Medecin>{
    /**
	 * @author Jonathan Rachwal
	 * 
	 */
	public void connexion();

	/**
	 * @author Jonathan Rachwal
	 * 
	 */
	public float totalDesMedecins();

	/**
	 * @author Jonathan Rachwal
	 * 
	 * @return
	 */
	public float totalMedecinsParSpecialite();

	/**
	 * @author Jonathan Rachwal
	 * 
	 * @return
	 */
	public Reservation confirmerRdv();

	/**
	 * @author Jonathan Rachwal
	 * 
	 */
	public Gain consulterGainsParJour();

	/**
	 * @author Jonathan Rachwal
	 * 
	 * @return
	 */
	public FichesMedicales modifierFichesMedicales();

	/**
	 * @author Jonathan Rachwal
	 * 
	 */
    public void consulterPlanning();
    
  	/**
	 * @author Jonathan Rachwal
	 * 
	 */
    public void consulterRapportSatisfaction();  
}

}