package com.security;

import com.dto.ConnectedUserDto;
import com.enums.Role;

/**
 * Interface {@code ISessionService} qui permet de faire la communication entre
 * un objet {@link ConnectedUser} et le controller.
 * 
 * @author Sophie Lahmar
 *
 */
public interface ISessionService {

	/**
	 * Méthode qui permet de retourner un objet de type String, correspondant à
	 * l'identifiant du User, si le token est bon.
	 * 
	 * @param token Le token envoyé au controller.
	 * @return Un objet String ou null.
	 */
	public String getUserIdentifiant(String token);

	/**
	 * Méthode qui permet de retourner un objet de type String, correspondant au mot
	 * de passe du User, si le token est bon.
	 * 
	 * @param token Le token envoyé au controller.
	 * @return Un objet String ou null.
	 */
	public String getUserMotDePasse(String token);

	/**
	 * Méthode qui permet de retourner un objet de type Role, correspondant au role
	 * du User, si le token est bon.
	 * 
	 * @param token Le token envoyé au controller.
	 * @return Un objet Role ou null.
	 */
	public Role getUserRole(String token);

	/**
	 * Méthode qui permet de retourner l'objet ConnectedUserDto si le token est bon.
	 * 
	 * @param token Le token envoyé au controller.
	 * @return Un objet ConnectedUserDto ou null.
	 */
	public ConnectedUserDto getUser(String token);

}
