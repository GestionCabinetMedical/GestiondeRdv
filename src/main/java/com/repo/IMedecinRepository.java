package com.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Medecin;
import com.exception.notsuccess.MedecinNotSuccessException;

/**
 * Interface repository {@code IMedecinRepository} spécifique de {@link Medecin}
 * étend de l'interface générique {@code IDaoRepo}. Cette interface permet la
 * communication avec la table medecin dans la base de données.
 * 
 * @author Jonathan Rachwal
 * @see IDaoRepo
 *
 */
@Repository
public interface IMedecinRepository extends IDaoRepo<Medecin> {

	/**
	 * Requête sur la table medecin du nombre total de medecin dans BDD.
	 * 
	 * @return int Nombre total de médecins dans la BDD.
	 * @throws MedecinNotSuccessException
	 */
	@Query(value="SELECT COUNT(*) FROM medecin", nativeQuery = true)
	public int totalDesMedecins() throws MedecinNotSuccessException;

	/**
	 * Requête sur la table medecin dans la BDD du nombre total de médecins pour
	 * chaque spécialité.
	 * 
	 * @return Map<String, Integer> key: specialité et value: nombre total de
	 *         medecin pour cette specialite dans la BD.
	 * @throws MedecinNotSuccessException
	 */
	@Query(value="SELECT specialite, COUNT(*)  FROM medecin GROUP BY specialite", nativeQuery = true)
	public Map<String, Integer> totalMedecinsParSpecialite() throws MedecinNotSuccessException;

	/**
	 * Méthode permettant de rechercher un médecin par son identifiant.
	 * 
	 * @param identifiant Identifiant du médecin recherché.
	 * @return Un médecin correpondant à l'identifiant entré.
	 * @throws MedecinNotSuccessException
	 */
	public Medecin findByIdentifiant(String identifiant) throws MedecinNotSuccessException;

	/**
	 * Méthode permettant de rechercher un médecin par son identifiant et son mot de
	 * passe.
	 * 
	 * @param identifiant Identifiant du médecin recherché.
	 * @param mdp Mot de passe du médecin recherché.
	 * @return Un médecin correpondant à l'identifiant et au mot de passe entrés.
	 * @throws MedecinNotSuccessException
	 */
	public Medecin findByIdentifiantAndMotDePasse(String identifiant, String mdp) throws MedecinNotSuccessException;
	
	
	/**
	 * Méthode permettant la recherche de medecin par nom.
	 * @param nom Nom du medecin recherché.
	 * @return Une liste de Medecin.
	 */
	public List<Medecin> findByNomContainingIgnoreCase (String nom);
	/**
	 * méthode permettant la recherche de medecin par specialite.
	 * @param specialite Specialite du medecin recherché.
	 * @return Une liste de Medecin.
	 */
	public List<Medecin> findBySpecialiteContainingIgnoreCase (String specialite);

}