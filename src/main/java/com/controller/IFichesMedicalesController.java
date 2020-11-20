package com.controller;

import org.springframework.data.repository.NoRepositoryBean;

import com.entity.FichesMedicales;

/**
 * Interface controller {@code IFichesMedicalesController} spécifique de
 * {@link FichesMedicales} qui étend de l'interface générique
 * {@code IDaoController}.
 * 
 * @author Pauline Humbert
 * @see IDaoController
 *
 */
@NoRepositoryBean
public interface IFichesMedicalesController extends IDaoController<FichesMedicales> {

}
