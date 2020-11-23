package com.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Consultation;

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
public class ConsultationControllerImpl extends DaoControllerImpl<Consultation> {

}