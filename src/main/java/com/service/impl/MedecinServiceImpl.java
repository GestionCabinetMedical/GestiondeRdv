package com.service.impl;

import org.springframework.stereotype.Service;

import com.dto.GainDto;
import com.entity.FichesMedicales;
import com.entity.Medecin;
import com.entity.Reservation;
import com.service.IMedecinService;

/**
 * Classe service {@code MedecinServiceImpl} spécifique de {@link Medecin} qui
 * étend de la classe générique {@code DaoServiceImpl} et implémente l'interface
 * spécifique {@code IMedecinService}.
 * 
 * @author Jonathan Rachwal
 * @see DaoServiceImpl
 * @see IMedecinService
 *
 */
@Service
public class MedecinServiceImpl extends DaoServiceImpl<Medecin> implements IMedecinService {
	/**
	 * @author Jonathan Rachwal
	 *
	 */
	@Override
	public void connexion() {
		// TODO Auto-generated method stub
	}

	/**
	 * @author Jonathan Rachwal
	 *
	 */
	@Override
	public float totalDesMedecins() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @author Jonathan Rachwal
	 *
	 */
	@Override
	public float totalMedecinsParSpecialite() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @author Jonathan Rachwal
	 *
	 */
	@Override
	public Reservation confirmerRdv() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @author Jonathan Rachwal
	 *
	 */
	@Override
	public GainDto consulterGainsParJour() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @author Jonathan Rachwal
	 *
	 */
	@Override
	public FichesMedicales modifierFichesMedicales() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @author Jonathan Rachwal
	 *
	 */
	@Override
	public void consulterPlanning() {
		// TODO Auto-generated method stub

	}

	/**
	 * @author Jonathan Rachwal
	 *
	 */
	@Override
	public void consulterRapportSatisfaction() {
		// TODO Auto-generated method stub

	}
}
