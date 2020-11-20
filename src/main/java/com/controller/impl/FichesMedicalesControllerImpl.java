package com.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.IFichesMedicalesController;
import com.entity.FichesMedicales;

/**
 * Classe controller {@code FichesMedicalesControllerImpl} spécifique de
 * {@link FichesMedicales} qui étend de la classe générique
 * {@code DaoControllerImpl} et implémente l'interface spécifique
 * {@code IFichesMedicalesController}.
 * 
 * @author Pauline Humbert
 * @see DaoControllerImpl
 * @see IFichesMedicalesController
 *
 */
@RestController
@RequestMapping(value = "/fiches_medicales")
public class FichesMedicalesControllerImpl extends DaoControllerImpl<FichesMedicales> implements IFichesMedicalesController {

}
