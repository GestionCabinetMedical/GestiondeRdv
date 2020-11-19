package com.controller;

import org.springframework.data.repository.NoRepositoryBean;

import com.entity.Consultation;

/**
 * @author Jonathan Rachwal
 * Interface de Consultation controller provenant de IDaoController
 *
 */
@NoRepositoryBean
public interface IConsultationController extends IDaoController<Consultation>{

}