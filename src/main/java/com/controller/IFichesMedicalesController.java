/**
 * 
 */
package com.controller;

import org.springframework.data.repository.NoRepositoryBean;

import com.entity.FichesMedicales;

/**
 * @author Pauline Humbert
 * Interfacle de fichesMédicles controller qui étend de IDaoController
 *
 */
@NoRepositoryBean
public interface IFichesMedicalesController extends IDaoController<FichesMedicales>{

}
