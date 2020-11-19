package com.service.impl;

import org.springframework.stereotype.Service;

import com.entity.Medecin;
import com.service.IMedecinService;

/**
 * @author Jonathan RACHWAL
 *
 * Classe Medecin service qui étend de DaoServiceImpl et implémente IMedecinService.
 *
 */
@Service
public class MedecinServiceImpl extends DaoServiceImpl<Medecin> implements IMedecinService {
/**
	 * @author Jonathan Rachwal
	 *
	 */
    @Override
	public void connexion(){
        // TODO Auto-generated method stub
    }

	/**
	 * @author Jonathan Rachwal
	 *
	 */
    @Override
	public float totalDesMedecins{
        // TODO Auto-generated method stub
    }

	/**
	 * @author Jonathan Rachwal
	 *
	 * @return
	 */
    @Override
	public float totalMedecinsParSpecialite{
        // TODO Auto-generated method stub
    }

	/**
	 * @author Jonathan Rachwal
	 *
	 * @return
	 */
    @Override
	public Reservation confirmerRdv{
        // TODO Auto-generated method stub
    }

	/**
	 * @author Jonathan Rachwal
	 *
	 */
    @Override
	public Gain consulterGainsParJour{
        // TODO Auto-generated method stub
    }

	/**
	 * @author Jonathan Rachwal
	 *
	 * @return
	 */
    @Override
	public FichesMedicales modifierFichesMedicales{
        // TODO Auto-generated method stub
    }

	/**
	 * @author Jonathan Rachwal
	 *
	 */
    @Override
    public void consulterPlanning{
        // TODO Auto-generated method stub
    }

  	/**
	 * @author Jonathan Rachwal
	 *
	 */
    @Override
    public void consulterRapportSatisfaction{
        // TODO Auto-generated method stub
    }
}
