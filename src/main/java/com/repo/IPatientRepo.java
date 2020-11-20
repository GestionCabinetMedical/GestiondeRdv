package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.Patient;

/**
 * Interface repository {@code IPatientRepo} spécifique de {@link Patient}
 * qui hérite de l'interface générique {@code IDaoRepo}.
 * 
 * @author Sophie Lahmar
 * @see IDaoRepo
 *
 */
@Repository
public interface IPatientRepo extends IDaoRepo<Patient> {

}
