package com.controller;

import org.springframework.data.repository.NoRepositoryBean;

import com.entity.Consultation;

/**
 * Interface controller {@code IConsultationController} spécifique de
 * {@link Consultation} qui hérite de l'interface générique
 * {@code IDaoController}.
 * 
 * @author Jonathan Rachwal
 * @see IDaoController
 *
 */
@NoRepositoryBean
public interface IConsultationController extends IDaoController<Consultation> {

}