package com.service.impl;

import org.springframework.stereotype.Service;

import com.entity.Medecin;
import com.service.IMedecinService;

/**
 * @author Jonathan RACHWAL
 * 
 * Classe Medecin service qui étend de DaoServiceImpl et implémente IMedecinService.
 *
 */
@Service
public class MedecinServiceImpl extends DaoServiceImpl<Medecin> implements IMedecinService {

}