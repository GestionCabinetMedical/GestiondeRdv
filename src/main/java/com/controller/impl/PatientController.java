package com.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.IPatientController;
import com.entity.Patient;

/**
 * Classe controller {@code PatientController} spécifique de Patient qui hérite
 * de la classe générique {@code DaoControllerImpl} et implémente l'interface
 * spécifique {@code IPatientController}.
 * 
 * @author Sophie Lahmar
 *
 */
@RestController
@RequestMapping(value = "/patient-rest")
public class PatientController extends DaoControllerImpl<Patient> implements IPatientController {

}
