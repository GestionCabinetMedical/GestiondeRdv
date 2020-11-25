package com.security;

import com.dto.ConnectedUserDto;
import com.enums.Role;
import com.exception.notsuccess.ConnectedUserDtoNotSuccessException;
import com.exception.notsuccess.ConnectedUserNotSuccessException;

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
	 * @throws ConnectedUserNotSuccessException 
	 */
	public String getUserIdentifiant(String token) throws ConnectedUserNotSuccessException;

	/**
	 * Méthode qui permet de retourner un objet de type String, correspondant au mot
	 * de passe du User, si le token est bon.
	 * 
	 * @param token Le token envoyé au controller.
	 * @return Un objet String ou null.
	 * @throws ConnectedUserNotSuccessException 
	 */
	public String getUserMotDePasse(String token) throws ConnectedUserNotSuccessException;

	/**
	 * Méthode qui permet de retourner un objet de type Role, correspondant au role
	 * du User, si le token est bon.
	 * 
	 * @param token Le token envoyé au controller.
	 * @return Un objet Role ou null.
	 * @throws ConnectedUserNotSuccessException 
	 */
	public Role getUserRole(String token) throws ConnectedUserNotSuccessException;

	/**
	 * Méthode qui permet de retourner l'objet ConnectedUserDto si le token est bon.
	 * 
	 * @param token Le token envoyé au controller.
	 * @return Un objet ConnectedUserDto ou null.
	 * @throws ConnectedUserNotSuccessException 
	 * @throws ConnectedUserDtoNotSuccessException 
	 */
	public ConnectedUserDto getUser(String token) throws ConnectedUserNotSuccessException, ConnectedUserDtoNotSuccessException;

}
