package com.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.FichesMedicales;

/**
 * Classe controller {@code FichesMedicalesControllerImpl} spécifique de
 * {@link FichesMedicales} qui étend de la classe générique
 * {@code DaoControllerImpl}.
 * 
 * @author Pauline Humbert
 * @see DaoControllerImpl
 *
 */
@RestController
@RequestMapping(value = "/fiches_medicales")
public class FichesMedicalesControllerImpl extends DaoControllerImpl<FichesMedicales> {

}
