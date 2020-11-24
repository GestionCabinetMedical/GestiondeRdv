package com.repo;

import org.springframework.stereotype.Repository;

import com.entity.Admin;

/**
 * Interface qui manage la couche repository de l'entité ADmin.
 * 
 * @author Sophie Lahmar
 *
 */
@Repository
public interface IAdminRepo extends IDaoRepo<Admin> {

	/**
	 * Méthode permettant de rechercher un admin par son identifiant.
	 * 
	 * @param identifiant Identifiant de l'admin recherché.
	 * @return Un admin correpondant à l'admin recherché.
	 */
	public Admin findByUsername(String username);

	/**
	 * Méthode permettant de rechercher un admin par son identifiant et son mot de
	 * passe.
	 * 
	 * @param identifiant Identifiant de l'admin recherché.
	 * @param mdp         Mot de passe de l'admin recherché.
	 * @return Un admin correpondant à l'admin recherché.
	 */
	public Admin findByUsernameAndPassword(String username, String password);

}
