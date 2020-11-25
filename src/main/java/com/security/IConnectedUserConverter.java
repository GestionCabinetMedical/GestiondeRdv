package com.security;

import com.dto.ConnectedUserDto;
import com.exception.notsuccess.ConnectedUserDtoNotSuccessException;
import com.exception.notsuccess.ConnectedUserNotSuccessException;

/**
 * Interface converteur {@code IConnectedUserConverter} qui permet d'extraire
 * les données de session de {@link ConnectedUser}.
 * 
 * @author Sophie Lahmar
 *
 */
public interface IConnectedUserConverter {

	/**
	 * Méthode qui permet de convertir les données de session d'une entité User en
	 * celles d'un UserDto.
	 * 
	 * @param token Le token des donnée de session.
	 * @return Un objet de type ConnectedUserDto.
	 * @throws ConnectedUserDtoNotSuccessException 
	 * @throws ConnectedUserNotSuccessException 
	 */
	public ConnectedUserDto convertUserToDto(String token) throws ConnectedUserDtoNotSuccessException, ConnectedUserNotSuccessException;

}
