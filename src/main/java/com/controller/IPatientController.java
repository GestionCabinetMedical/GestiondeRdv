package com.controller;

import org.springframework.data.repository.NoRepositoryBean;

import com.entity.Patient;

/**
 * Interface controller {@code IPatientController} spécifique de Patient
 * héritant de l'interface générique {@code IDaoController}.
 * 
 * @author Sophie Lahmar
 *
 */
public interface IPatientController extends IDaoController<Patient> {

}
