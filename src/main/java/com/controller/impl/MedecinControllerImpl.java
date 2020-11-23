package com.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Medecin;

/**
 * Classe controller {@code MedecinControllerImpl} spécifique de {@link Medecin}
 * qui étend de la classe générique {@code DaoControllerImpl}.
 * 
 * @author Jonathan Rachwal
 * @see DaoControllerImpl
 *
 */
@RestController
@RequestMapping(value = "/medecin")
public class MedecinControllerImpl extends DaoControllerImpl<Medecin> {

}