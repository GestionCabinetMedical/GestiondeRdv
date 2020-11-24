package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.Admin;

/**
 * Interface repository {@code IReservationRepo} spécifique de {@link Admin} qui
 * hérite de l'interface générique {@code IDaoRepo}. Cette interface est
 * responsable de la communication avec la table admin dans la base de données.
 * 
 * @author Sophie Lahmar
 * @see IDaoRepo
 *
 */
@Repository
public interface IAdminRepo extends IDaoRepo<Admin> {

	/**
	 * Méthode permettant de rechercher un admin par son identifiant.
	 * 
	 * @param username Identifiant de l'admin recherché.
	 * @return Un admin correpondant à l'identifiant entré.
	 */
	public Admin findByUsername(String username);

	/**
	 * Méthode permettant de rechercher un admin par son identifiant et son mot de
	 * passe.
	 * 
	 * @param username Identifiant de l'admin recherché.
	 * @param password Mot de passe de l'admin recherché.
	 * @return Un admin correpondant à l'identifiant et au mot de passe entrés.
	 */
	public Admin findByUsernameAndPassword(String username, String password);

}
