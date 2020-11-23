package com.service.impl;

import org.springframework.stereotype.Service;

import com.entity.Consultation;
import com.service.IConsultationService;

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
public class ConsultationServiceImpl extends DaoServiceImpl<Consultation> implements IConsultationService {

}