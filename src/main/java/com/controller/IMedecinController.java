package com.controller;

import org.springframework.data.repository.NoRepositoryBean;

import com.entity.Medecin;

/**
 * Interface controller {@code IMedecinController} spécifique de {@link Medecin}
 * qui hérite de l'interface générique {@code IDaoController}.
 * 
 * @author Jonathan Rachwal
 * @see IDaoController
 *
 */
@NoRepositoryBean
public interface IMedecinController extends IDaoController<Medecin> {

}