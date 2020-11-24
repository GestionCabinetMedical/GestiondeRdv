package com.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.entity.Consultation;
import com.entity.FichesMedicales;
import com.entity.Medecin;
import com.exception.notfound.MedecinNotFoundException;

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
	 */
	public Medecin existsByIdentifiant(String identifiant) throws MedecinNotFoundException;

	/**
	 * Méthode permettant de vérifier l'existence d'un médecin par son identifiant
	 * et son mot de passe.
	 * 
	 * @param identifiant Identifiant du médecin recherché.
	 * @param mdp         Mot de passe du médecin recherché.
	 * @return Un médecin s'il existe déjà, null sinon.
	 * @throws MedecinNotFoundException
	 */
	public Medecin existsByIdentifiantAndMotDePasse(String identifiant, String mdp) throws MedecinNotFoundException;

	/**
	 * Methode permettant d'avoir le nombre total de medecin dans l'application.
	 * 
	 * @return int du nombre total de medecin.
	 */
	public int totalDesMedecins();

	/**
	 * Methode permettant d'avoir le nombre total de medecin par specialite dans
	 * l'application.
	 * 
	 * @return Map<String, Integer> key: specialité et value: nombre total de
	 *         medecin pour cette specialite dans la BD.
	 */
	public Map<String, Integer> totalMedecinsParSpecialite();

	/**
	 * Methode permettant au medecin de confirmer un rdv => ajout d'une consultation
	 * dans sa liste (= son planning).
	 * 
	 * @param idReservation Long associé à la Réservation faite par un patient à
	 *                      réserver.
	 * @param idMedecin     Long associé à un medecin qui peut confirmer ou non le
	 *                      rdv.
	 * @param idPatient     Long associé à un patient qui a fait la Réservation.
	 * @return List<Consultation> d'un medecin avec la nouvelle consultation prévue
	 *         une fois la Réservation confirmée.
	 * @throws ParseException
	 */
	public List<Consultation> confirmerRdv(Long idReservation, Long idMedecin) throws ParseException;

	/**
	 * Methode permettant au medecin de consulter ses rdv prévus / consultations (=
	 * son planning).
	 * 
	 * @param idMedecin Long associé à un medecin qui souhaite consulter le
	 *                  planning.
	 * @return Map<Consultation, Date> qui liste en Key une consultation et en
	 *         valeur la date de celle-ci.
	 */
	public Map<Consultation, Date> consulterPlanning(Long idMedecin);

}
