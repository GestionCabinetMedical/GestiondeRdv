package com.service;

import com.dto.GainDto;
import com.entity.FichesMedicales;
import com.entity.Medecin;
import com.entity.Reservation;

/**
 * Interface service {@code IFichesMedicalesService} spécifique de
 * {@link FichesMedicales} qui étend de l'interface générique
 * {@code IDaoService}.
 * 
 * @author Jonathan Rachwal
 * @see IDaoService
 */
public interface IMedecinService extends IDaoService<Medecin> {

	/**
	 * @author Jonathan Rachwal
	 *
	 */
	public void connexion();

	/**
	 * @author Jonathan Rachwal
	 * 
	 * @return
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
	public GainDto consulterGainsParJour();

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
