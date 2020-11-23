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
	 * Méthode permettant à un medecin de se connecter à son espace personnel dans
	 * l'application.
	 * 
	 * @param login Identifiant associé au compte personnel du medecin.
	 * @param mdp   Mot de passe pour entrer dans l'espace du medecin.
	 */
	public void connexion();

	/**
	 * Methode permettant d'avoir le nombre total de medecin dans l'application
	 * @return int du nombre total de medecin 
	 */
	public int totalDesMedecins();

	/**
	 * Methode permettant d'avoir le nombre total de medecin par specialite dans l'application
	 * @return int du nombre total de medecin par specialite 
	 */
	public int totalMedecinsParSpecialite();

	/**
	 * Methode permettant au medecin de confirmer un rdv => ajout d'une consultation dans sa liste (=son planning)
	 * @return ???
	 */
	public Reservation confirmerRdv(Long idReservation);

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
