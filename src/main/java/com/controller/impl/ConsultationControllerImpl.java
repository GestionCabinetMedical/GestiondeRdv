package com.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.IConsultationController;
import com.entity.Consultation;

/**
 * @author Jonathan Rachwal
 *
 * Classe Consultation controller extend de DaoControllerImpl et implémente IConsultationController
 */
@RestController
@RequestMapping(value="/consultation")
public class ConsultationControllerImpl extends DaoControllerImpl<Consultation> implements IConsultationController{

}