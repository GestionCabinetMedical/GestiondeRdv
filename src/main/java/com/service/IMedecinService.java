package com.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.dto.GainDto;
import com.entity.Consultation;
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
	 * @return Map<String, Integer> key: specialité et value: nombre total de medecin pour cette specialite dans la BD
	 */
	public Map<String, Integer> totalMedecinsParSpecialite();

	/**
	 * Methode permettant au medecin de confirmer un rdv => ajout d'une consultation dans sa liste (=son planning)
	 * @param idReservation Long associé à la Réservation faite par un patient à réserver.
	 * @param idMedecin Long associé à un medecin qui peut confirmer ou non le rdv.
	 * @param idPatient Long associé à un patient qui a fait la Réservation.
	 * @return List<Consultation> d'un medecin avec la nouvelle consultation prévue une fois la Réservation confirmée
	 * @throws ParseException 
	 */
	public List<Consultation> confirmerRdv(Long idReservation, Long idMedecin, Long idPatient) throws ParseException;

	/**
	 * @author Jonathan Rachwal
	 *
	 */
	public GainDto consulterGainsParJour();

	/**
	 * @author Jonathan Rachwal
	 *
	 */
	//TODO map consultation et date via réservation 
	public void consulterPlanning();

	/**
	 * @author Jonathan Rachwal
	 *
	 */
	public void consulterRapportSatisfaction();

}
