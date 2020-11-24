package com.security;

import com.dto.ConnectedUserDto;
import com.enums.Role;

/**
 * Interface : couche entre l'objet ConnectedUser et le controller.
 * 
 * @author Sophie Lahmar
 *
 */
public interface ISessionService {

	public String getUserIdentifiant(String token);

	public String getUserMotDePasse(String token);

	public Role getUserRole(String token);

	/**
	 * Méthode qui permet de retourner l'objet ConnectedUserDto si le token est bon.
	 * 
	 * @param token Le token envoyé au controller.
	 * @return Un objet ConnectedUserDto ou null.
	 */
	public ConnectedUserDto getUser(String token);

}
