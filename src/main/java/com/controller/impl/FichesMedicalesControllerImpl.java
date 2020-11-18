/**
 * 
 */
package com.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.IFichesMedicalesController;
import com.entity.FichesMedicales;

/**
 * @author Pauline Humbert
 *
 * Classe fichesMédicales controller qui étend de DaoControllerImpl et implémente IFichesMedicalesController.
 */
@RestController
@RequestMapping(value="/fiches_medicales")
public class FichesMedicalesControllerImpl extends DaoControllerImpl<FichesMedicales> implements IFichesMedicalesController{

}
