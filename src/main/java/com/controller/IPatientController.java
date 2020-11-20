package com.controller;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.dto.ResponseDto;
import com.entity.FichesMedicales;
import com.entity.Patient;

/**
 * Interface controller {@code IPatientController} spécifique de Patient
 * héritant de l'interface générique {@code IDaoController}.
 * 
 * @author Sophie Lahmar
 * @see IDaoController
 *
 */
@NoRepositoryBean
public interface IPatientController extends IDaoController<Patient> {

	/**
	 * Méthode permettant à un patient de se connecter à son espace personnel dans
	 * l'application.
	 * 
	 * @param login Identifiant associé au compte personnel du patient.
	 * @param mdp   Mot de passe pour entrer dans l'espace du patient.
	 */
	public void connexion(String login, String mdp);

	/**
	 * Méthode permettant au patient de consulter sa liste de fiches médicales.
	 * 
	 * @param id Id du patient.
	 * @return Une liste de fiches médicales d'un patient.
	 */
	public ResponseDto<List<FichesMedicales>> consulterFicheMedicale(Long id);

}
