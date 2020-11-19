package com.controller;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.dto.ResponseDto;
import com.entity.FichesMedicales;
import com.entity.Patient;
import com.entity.Reservation;

/**
 * Interface controller {@code IPatientController} spécifique de Patient
 * héritant de l'interface générique {@code IDaoController}.
 * 
 * @author Sophie Lahmar
 * @see IDaoController
 *
 */
@NoRepositoryBean
public interface IPatientController extends IDaoController<Patient> {

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
	public ResponseDto<Patient> modifierProfil(Patient patientUpdated);

	/**
	 * Méthode permettant à un patient de consulter la liste des rendez-vous
	 * médicaux disponibles.
	 * 
	 * @return Une liste des réservations disponibles.
	 */
	public ResponseDto<List<Reservation>> consulterPlanning();

	/**
	 * Méthode permettant à un patient de réserver un rendez-vous médical, dans la
	 * base de donnée.
	 * 
	 * @param reservation Réservation à ajouter.
	 * @return La réservation ajoutée par le patient.
	 */
	public ResponseDto<Reservation> reserverRdv(Reservation reservation);

	/**
	 * Méthode permettant à un patient de modifier un rendez-vous médical.
	 * 
	 * @param reservation Réservation à modifier.
	 * @return La réservation modifiée par le patient.
	 */
	public ResponseDto<Reservation> modifierRdv(Reservation reservation);

	/**
	 * Méthode permettant au patient de consulter sa fiche médicale, suite à un
	 * rendez-vous chez un médecin.
	 * 
	 * @param id Id de la fiche médicale d'un patient.
	 * @return Une fiche médicale d'un patient.
	 */
	public ResponseDto<FichesMedicales> consulterFicheMedicale(Long id);

	public void remplirQuestionnaireSatisfaction();

}
