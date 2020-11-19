package com.controller;

import org.springframework.data.repository.NoRepositoryBean;

import com.entity.Medecin;

/**
 * @author Jonathan Rachwal
 * Interface de Medecin controller provenant de IDaoController
 *
 */
@NoRepositoryBean
public interface IMedecinController extends IDaoController<Medecin>{

}