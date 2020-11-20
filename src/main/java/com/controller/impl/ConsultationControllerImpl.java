package com.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.IConsultationController;
import com.entity.Consultation;

/**
 * Classe controller {@code ConsultationControllerImpl} spécifique de
 * {@link Consultation} qui étend de la classe générique
 * {@code DaoControllerImpl} et implémente l'interface spécifique
 * {@code IConsultationController}.
 * 
 * @author Jonathan Rachwal
 * @see DaoControllerImpl
 * @see IConsultationController
 *
 */
@RestController
@RequestMapping(value = "/consultation")
public class ConsultationControllerImpl extends DaoControllerImpl<Consultation> implements IConsultationController {

}