package com.enums;

import com.entity.Admin;
import com.entity.Medecin;
import com.entity.Patient;

/**
 * Classe {@code Role} de type enum composée d'une liste de constantes qui
 * correspondent à un {@link Admin}, un {@link Medecin} et un {@link Patient},
 * ou à aucun des utilisateurs ({@code NONE}).
 * 
 * @author Sophie Lahmar
 *
 */
public enum Role {

	ADMIN, MEDECIN, PATIENT, NONE
}
