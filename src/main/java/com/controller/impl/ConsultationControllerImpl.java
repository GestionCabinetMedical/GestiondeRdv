package com.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ResponseDto;
import com.entity.Consultation;
import com.exception.notfound.ConsultationNotFoundException;
import com.exception.notsuccess.ResponseDtoNotSuccessException;
import com.service.IConsultationService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe controller {@code ConsultationControllerImpl} spécifique de
 * {@link Consultation} qui étend de la classe générique
 * {@code DaoControllerImpl}
 * 
 * @author Jonathan Rachwal
 * @see DaoControllerImpl
 *
 */
@RestController
@RequestMapping(value = "/consultation")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
@Slf4j
public class ConsultationControllerImpl extends DaoControllerImpl<Consultation> {
	
	@Autowired
	private IConsultationService consultService;
	
	/**
	 * Méthode permettant de créer une consultation avec medecin et reservation.
	 * @param c Consultation à créer.
	 * @return Un objet ResponseDto comprenant un booléen error, un body (Consultation ou null) et un status (HTTP response).
	 * @throws ConsultationNotFoundException 
	 * @throws ResponseDtoNotSuccessException 
	 */
	@PostMapping (path = "addConsultAndResa")
	public ResponseDto<Consultation> addConsultationAndResa (@RequestBody Consultation c) throws ConsultationNotFoundException, ResponseDtoNotSuccessException{
		log.info("Consultation controller : méthode add consultation and resa appelée");
		log.info("objet consultation dto : "+c);
		return makeDtoResponse(consultService.addConsultAndResa(c));
	}

}