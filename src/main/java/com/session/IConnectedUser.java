package com.session;

import com.entity.Role;

/**
 * Interface qui permet le stockage des détails d'une session utilisateur.
 * 
 * @author Sophie Lahmar
 *
 */
public interface IConnectedUser {

	public String getIdentifiant(String token);

	public String getMotDePasse(String token);

	public Role getRole(String token);

	public boolean testToken(String token);

}
