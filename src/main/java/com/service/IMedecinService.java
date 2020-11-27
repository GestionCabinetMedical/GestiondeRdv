package com.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.entity.Consultation;
import com.entity.FichesMedicales;
import com.entity.Medecin;
import com.entity.Reservation;
import com.exception.notfound.MedecinNotFoundException;
import com.exception.notfound.ReservationNotFoundException;
import com.exception.notsuccess.MedecinNotSuccessException;
import com.exception.notsuccess.ReservationNotSuccessException;

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
	 * Méthode permettant de vérifier l'existence d'un médecin par son identifiant.
	 * 
	 * @param identifiant Identifiant du médecin recherché.
	 * @return Un médecin s'il existe déjà, null sinon.
	 * @throws MedecinNotFoundException
	 * @throws MedecinNotSuccessException 
	 */
	public Medecin existsByIdentifiant(String identifiant) throws MedecinNotFoundException, MedecinNotSuccessException;

	/**
	 * Méthode permettant de vérifier l'existence d'un médecin par son identifiant
	 * et son mot de passe.
	 * 
	 * @param identifiant Identifiant du médecin recherché.
	 * @param mdp         Mot de passe du médecin recherché.
	 * @return Un médecin s'il existe déjà, null sinon.
	 * @throws MedecinNotFoundException
	 * @throws MedecinNotSuccessException 
	 */
	public Medecin existsByIdentifiantAndMotDePasse(String identifiant, String mdp) throws MedecinNotFoundException, MedecinNotSuccessException;

	/**
	 * Methode permettant d'avoir le nombre total de medecin dans l'application.
	 * 
	 * @return int du nombre total de medecin.
	 * @throws MedecinNotSuccessException 
	 */
	public int totalDesMedecins() throws MedecinNotSuccessException;

	/**
	 * Methode permettant d'avoir le nombre total de medecin par specialite dans
	 * l'application.
	 * 
	 * @return Map<String, Integer> key: specialité et value: nombre total de
	 *         medecin pour cette specialite dans la BD.
	 * @throws MedecinNotSuccessException 
	 */
	public Map<String, Integer> totalMedecinsParSpecialite() throws MedecinNotSuccessException;

	/**
	 * Methode permettant au medecin de confirmer un rdv => modification d'une resa en status true
	 * 
	 * @param Reservation  Entite Reservation a confirmer.
	 * @return Booléen true si la modification a été effectué, false sinon.
	 * @throws ReservationNotSuccessException 
	 */
	public Boolean confirmerRdv(Reservation r) throws ReservationNotSuccessException;

	/**
	 * Methode permettant au medecin de consulter ses demande de rendez-vous
	 * 
	 * @param identifiant Identifiant du medecin.
	 * @return List<Consultation> qui comprend toutes les consultations en status false du medecin.
	 * @throws MedecinNotFoundException 
	 * @throws MedecinNotSuccessException
	 */
	public List<Consultation> consulterResa(String identifiant) throws MedecinNotFoundException, MedecinNotSuccessException;
	
	/**
	 * Methode permettant au medecin de consulter ses rdv prévus / consultations (=
	 * son planning).
	 * 
	 * @param identifiant Identifiant du medecin.
	 * @return List<Consultation> qui comprend toutes les consultations en status true du medecin.
	 * @throws MedecinNotFoundException 
	 * @throws MedecinNotSuccessException
	 */
	public List<Consultation> consulterPlanning(String identifiant) throws MedecinNotFoundException, MedecinNotSuccessException;
	
	public Map<String, List<Consultation>> consulterPlanningByDate(String identifiant,String date) throws MedecinNotFoundException, MedecinNotSuccessException, ParseException ;
	
	
	/**
	 * méthode permettant la recherche de medecin par nom.
	 * @param nom Nom du medecin recherché.
	 * @return Une liste de medecin.
	 */
	public List<Medecin> findByNom (String nom) throws MedecinNotFoundException ;
	
	/**
	 * méthode permettant la recherche de medecin par specialite.
	 * @param specialite Specialite du medecin recherché.
	 * @return Une liste de Medecin.
	 */
	public List<Medecin> findBySpecialite (String specialite) throws MedecinNotFoundException ;

}
