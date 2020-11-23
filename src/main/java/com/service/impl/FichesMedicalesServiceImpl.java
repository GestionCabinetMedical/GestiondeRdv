package com.service.impl;

import org.springframework.stereotype.Service;

import com.entity.FichesMedicales;
import com.service.IFichesMedicalesService;

/**
 * Classe service {@code FichesMedicalesServiceImpl} spécifique de
 * {@link FichesMedicales} qui étend de la classe générique
 * {@code DaoServiceImpl} et implémente l'interface spécifique
 * {@code IFichesMedicalesService}.
 * 
 * @author Pauline Humbert
 * @see DaoServiceImpl
 * @see IFichesMedicalesService
 *
 */
@Service
public class FichesMedicalesServiceImpl extends DaoServiceImpl<FichesMedicales> implements IFichesMedicalesService {

}
