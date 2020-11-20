package com.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.IMedecinController;
import com.entity.Medecin;

/**
 * Classe controller {@code MedecinControllerImpl} spécifique de {@link Medecin}
 * qui étend de la classe générique {@code DaoControllerImpl} et implémente
 * l'interface spécifique {@code IMedecinController}.
 * 
 * @author Jonathan Rachwal
 * @see DaoControllerImpl
 * @see IMedecinController
 *
 */
@RestController
@RequestMapping(value = "/medecin")
public class MedecinControllerImpl extends DaoControllerImpl<Medecin> implements IMedecinController {

}