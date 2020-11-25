package com.service;

import com.entity.Admin;
import com.exception.notfound.AdminNotFoundException;
import com.exception.notsuccess.AdminNotSuccessException;

/**
 * Interface service {@code IAdminService} spécifique de {@link Admin} qui étend
 * de l'interface générique {@code IDaoService}.
 * 
 * @author Sophie Lahmar
 * @see IDaoService
 *
 */
public interface IAdminService extends IDaoService<Admin> {

	/**
	 * Méthode permettant de vérifier l'existence d'un admin par son identifiant.
	 * 
	 * @param username Identifiant de l'admin.
	 * @return Un admin s'il existe déjà, null sinon.
	 * @throws AdminNotFoundException
	 * @throws AdminNotSuccessException 
	 */
	public Admin existsByUsername(String username) throws AdminNotFoundException, AdminNotSuccessException;

	/**
	 * Méthode permettant de vérifier l'existence d'un admin par son identifiant et
	 * son mot de passe.
	 * 
	 * @param username Identifiant de l'admin recherché.
	 * @param password Mot de passe de l'admin recherché.
	 * @return Un admin s'il existe déjà, null sinon.
	 * @throws AdminNotFoundException 
	 * @throws AdminNotSuccessException 
	 */
	public Admin existsByUsernameAndPassword(String username, String password) throws AdminNotFoundException, AdminNotSuccessException;

}
