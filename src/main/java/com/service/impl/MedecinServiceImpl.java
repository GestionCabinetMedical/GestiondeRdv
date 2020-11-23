package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.GainDto;
import com.entity.FichesMedicales;
import com.entity.Medecin;
import com.entity.Reservation;
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
	public Reservation confirmerRdv(Long idReservation) {
		log.info("Service spécifique du Medecin : méthode confirmerRdv appelée.");
		if (idReservation != null) {
			Reservation reservationToConfirm = reservationRepository.findById(idReservation).get();
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
