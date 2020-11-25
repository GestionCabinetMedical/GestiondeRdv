package com.service;

import com.entity.Consultation;
import com.exception.notfound.ConsultationNotFoundException;

/**
 * Interface service {@code IConsultationService} spécifique de
 * {@link Consultation} qui étend de l'interface générique {@code IDaoService}.
 * 
 * @author Jonathan Rachwal
 * @see IDaoService
 *
 */
public interface IConsultationService extends IDaoService<Consultation> {
	
	/**
	 * méthode permettant de créer une consultation et une reservation en status false
	 * @param c Consultation à créer
	 * @return Un objet consultation
	 */
	public Consultation addConsultAndResa(Consultation c) throws ConsultationNotFoundException;

}
