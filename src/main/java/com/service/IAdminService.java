package com.service;

import com.entity.Admin;

/**
 * @author Sophie Lahmar
 *
 */
public interface IAdminService extends IDaoService<Admin> {

	/**
	 * Méthode permettant de vérifier l'existence d'un admin par son identifiant.
	 * 
	 * @param username Identifiant de l'admin.
	 * @return Un admin s'il existe déjà, null sinon.
	 */
	public Admin existsByUsername(String username);

	/**
	 * Méthode permettant de vérifier l'existence d'un admin par son identifiant et
	 * son mot de passe.
	 * 
	 * @param username Identifiant de l'admin recherché.
	 * @param password Mot de passe de l'admin recherché.
	 * @return Un admin s'il existe déjà, null sinon.
	 */
	public Admin existsByUsernameAndPassword(String username, String password);

}
