package com.service.impl;

import org.springframework.stereotype.Service;

import com.entity.Consultation;
import com.service.IConsultationService;

/**
 * @author Jonathan RACHWAL
 * 
 * Classe Consultation service qui étend de DaoServiceImpl et implémente IMedecinService.
 *
 */
@Service
public class ConsultationServiceImpl extends DaoServiceImpl<Consultation> implements IConsultationService {

}