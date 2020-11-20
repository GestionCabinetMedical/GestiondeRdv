package com.service;

import java.util.List;

import com.entity.FichesMedicales;
import com.entity.Patient;
import com.entity.Reservation;
import com.exception.ReservationNotFoundException;

/**
 * Interface service {@code IPatientService} spécifique de Patient héritant de
 * l'interface générique {@code IDaoService}.
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
	 * Méthode permettant à un patient de modifier son profil sur sn espace
	 * personnel.
	 * 
	 * @param patientUpdated Patient dont le profil est à modifier.
	 * @return Le patient avec un profil modifié.
	 */
	public Patient modifierProfil(Patient patient);

	/**
	 * Méthode permettant à un patient de consulter la liste des rendez-vous
	 * médicaux disponibles.
	 * 
	 * @return Une liste des réservations disponibles.
	 */
	public List<Reservation> consulterPlanning();

	/**
	 * Méthode permettant à un patient de réserver un rendez-vous médical, dans la
	 * base de donnée.
	 * 
	 * @param reservation Réservation à ajouter.
	 * @return La réservation ajoutée par le patient.
	 */
	public Reservation reserverRdv(Reservation reservation);

	/**
	 * Méthode permettant à un patient de modifier un rendez-vous médical.
	 * 
	 * @param reservation Réservation à modifier.
	 */
	public void modifierRdv(Reservation reservation) throws ReservationNotFoundException;

	/**
	 * Méthode permettant au patient de consulter sa liste de fiches médicales.
	 * 
	 * @param id Id du patient.
	 * @return Une liste de fiches médicales d'un patient.
	 */
	public List<FichesMedicales> consulterFicheMedicale(Long id);

	public void remplirQuestionnaireSatisfaction();

}
