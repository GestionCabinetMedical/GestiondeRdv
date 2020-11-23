package com.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.GainDto;
import com.entity.Consultation;
import com.entity.FichesMedicales;
import com.entity.Medecin;
import com.entity.Reservation;
import com.repo.IConsultationRepository;
import com.repo.IMedecinRepository;
import com.repo.IReservationRepo;
import com.service.IMedecinService;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class MedecinServiceImpl extends DaoServiceImpl<Medecin> implements IMedecinService {
	
	// ATTRIBUTS
	@Autowired
	private IMedecinRepository medecinRepository;
	
	@Autowired
	private IReservationRepo reservationRepository;
	
	@Autowired 
	private IConsultationRepository consultationRepository;
	
	// METHODES
	
	@Override
	public void connexion() {
		// TODO Auto-generated method stub
	}

	@Override
	public int totalDesMedecins() {
		return medecinRepository.totalDesMedecins();
	}


	@Override
	public int totalMedecinsParSpecialite() {
		return medecinRepository.totalMedecinsParSpecialite();
	}

	@Override
	public List<Consultation> confirmerRdv(Long idReservation, Long idMedecin, Long idPatient) {
		log.info("Service spécifique du Medecin : méthode confirmerRdv appelée.");
		if (idReservation != null) {
			Reservation reservationToConfirm = reservationRepository.findById(idReservation).get();
			log.info("Appel repo reservation OK.");
			if(reservationToConfirm.isStatus() == false) {
				Date dateToConfirm = reservationToConfirm.getDateRervation();
				Medecin medecinConcerned = medecinRepository.findById(idMedecin).get();
				List<Consultation> listeConsultation = medecinConcerned.getConsultations();
				log.info("Appel repo medecin OK.");
				List<Consultation> listeConsultationAlreadyToThisDate = listeConsultation.stream().
																	filter(c -> c.getReservation().getDateRervation() == dateToConfirm).
																	collect(Collectors.toList());
				if (listeConsultationAlreadyToThisDate.isEmpty()) {
//					SimpleDataFormat 
					// verifier que l'heure est entre 8h-12h et 14h-18h
					Consultation consultationConfirmed = new Consultation(null, idMedecin, idPatient, reservationToConfirm);
					consultationRepository.save(consultationConfirmed);
					listeConsultation.add(consultationConfirmed);
					medecinConcerned.setConsultations(listeConsultation);
					medecinRepository.save(medecinConcerned);
					return medecinConcerned.getConsultations();
				}
				else {
					System.out.println("medecin déjà occupé");
				}
			} 
			else {
				System.out.println("déjà réservé");
			}
		}
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
