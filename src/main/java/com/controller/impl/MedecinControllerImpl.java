package com.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.IMedecinController;
import com.entity.Medecin;

/**
 * @author Jonathan Rachwal
 *
 * Classe Medecin controller extend de DaoControllerImpl et implémente IMedecinController
 */
@RestController
@RequestMapping(value="/medecin")
public class MedecinControllerImpl extends DaoControllerImpl<Medecin> implements IMedecinController{

}