package com.controller.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Consultation;
import com.entity.FichesMedicales;
import com.entity.Medecin;
import com.service.impl.FichesMedicalesServiceImpl;
import com.service.impl.MedecinServiceImpl;
import com.service.impl.PatientServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe controller {@code MedecinControllerImpl} spécifique de {@link Medecin}
 * qui étend de la classe générique {@code DaoControllerImpl}.
 * 
 * @author Jonathan Rachwal
 * @see DaoControllerImpl
 *
 */
@RestController
@RequestMapping(value = "/medecin")
@Slf4j
public class MedecinControllerImpl extends DaoControllerImpl<Medecin> {
	
	// ATTRIBUTS

	@Autowired
	private MedecinServiceImpl medecinService;

	// METHODES

	/**
	 * Méthode permettant à un medecin de se connecter à son espace personnel dans
	 * l'application.
	 * 
	 * @param login Identifiant associé au compte personnel du medecin.
	 * @param mdp   Mot de passe pour entrer dans l'espace du medecin.
	 */
	@RequestMapping
	public void connexion(String login, String mdp) {
		// TODO : implémenter la méthode
		log.info("Controller spécifique de Medecin : méthode connexion appelée.");
	}
	
	/**
	 * Methode permettant au medecin de confirmer un rdv => ajout d'une consultation dans sa liste (=son planning)
	 * @param idReservation Long associé à la Réservation faite par un patient à réserver.
	 * @param idMedecin Long associé à un medecin qui peut confirmer ou non le rdv.
	 * @param idPatient Long associé à un patient qui a fait la Réservation.
	 * @return List<Consultation> d'un medecin avec la nouvelle consultation prévue une fois la Réservation confirmée
	 * @throws ParseException 
	 */
	@GetMapping(value="/confirmerRdv")
	public List<Consultation> confirmerRdv(@RequestParam Long idReservation, @RequestParam Long idMedecin, @RequestParam Long idPatient) throws ParseException {
		log.info("Controller medecin : méthode confirmerRdv appelée");
		List<Consultation> listeConsultations = medecinService.confirmerRdv(idReservation, idMedecin, idPatient);
		return listeConsultations;
	}

}