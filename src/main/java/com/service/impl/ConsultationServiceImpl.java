package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Consultation;
import com.exception.notfound.ConsultationNotFoundException;
import com.repo.IConsultationRepository;
import com.repo.IReservationRepo;
import com.service.IConsultationService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe service {@code ConsultationServiceImpl} spécifique de
 * {@link Consultation} qui étend de la classe générique {@code DaoServiceImpl}
 * et implémente l'interface spécifique {@code IConsultationService}.
 * 
 * @author Jonathan Rachwal
 * @see DaoServiceImpl
 * @see IConsultationService
 *
 */
@Service
@Slf4j
public class ConsultationServiceImpl extends DaoServiceImpl<Consultation> implements IConsultationService {
	
	@Autowired
	private IConsultationRepository consultRepo;
	
	@Autowired
	private IReservationRepo resaRepo;
	
	@Override
	public Consultation addConsultAndResa(Consultation c) throws ConsultationNotFoundException {
		log.info("Consultation service : méthode add consult and resa appelée");
		//sauvegarde de la resa en status false
		if (c != null) {
			log.info("Sauvegarde de l'objet reservation en status false");
			c.getReservation().setStatus(false);
			resaRepo.save(c.getReservation());
			log.info("Sauvegarde de la consultation");
			return consultRepo.save(c);
		}
		log.warn("Consultation et reservation non sauvegardé : objet consultation null !");
		throw new ConsultationNotFoundException("Fail - Objet consultation null !");
	}

}