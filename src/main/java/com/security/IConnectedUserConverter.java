package com.security;

import com.dto.ConnectedUserDto;

/**
 * Interface converteur permettant d'extraire les données de session de
 * PatientConnected.
 * 
 * @author Sophie Lahmar
 *
 */
public interface IConnectedUserConverter {

	public ConnectedUserDto convertUserToDto(String token);

}
