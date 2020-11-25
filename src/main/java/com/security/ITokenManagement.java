package com.security;

import com.dto.ConnectedUserDto;
import com.exception.notsuccess.ConnectedUserNotSuccessException;
import com.exception.notsuccess.TokenNotSuccessException;

/**
 * Interface {@code ITokenManagement} qui permet de configurer des sessions
 * utilisateur dans le front.
 * 
 * @author Sophie Lahmar
 *
 */
public interface ITokenManagement {

	/**
	 * Méthode permettant de configurer la session d'un patient en enregistrant les
	 * données nécessaires et en générant un token.
	 * 
	 * @param user Un PatientUpdateDto.
	 * @return Un token sous format String.
	 * @throws ConnectedUserNotSuccessException 
	 * @throws TokenNotSuccessException 
	 */
	public String makePatientSession(ConnectedUserDto patient) throws ConnectedUserNotSuccessException, TokenNotSuccessException;

	/**
	 * Méthode permettant de configurer la session d'un médecin en enregistrant les
	 * données nécessaires et en générant un token.
	 * 
	 * @param user Un PatientUpdateDto.
	 * @return Un token sous format String.
	 * @throws ConnectedUserNotSuccessException 
	 * @throws TokenNotSuccessException 
	 */
	public String makeMedecinSession(ConnectedUserDto medecin) throws ConnectedUserNotSuccessException, TokenNotSuccessException;

	/**
	 * Méthode permettant de configurer la session d'un admin en enregistrant les
	 * données nécessaires et en générant un token.
	 * 
	 * @param admin Un AdminUpdateDto.
	 * @return Un token sous format String.
	 * @throws ConnectedUserNotSuccessException 
	 * @throws TokenNotSuccessException 
	 */
	public String makeAdminSession(ConnectedUserDto admin) throws ConnectedUserNotSuccessException, TokenNotSuccessException;

	/**
	 * Méthode permettant de générer un token de 20 caractères.
	 * 
	 * @return Un String aléatoire, 20 caractères de long.
	 * @throws TokenNotSuccessException 
	 */
	public String generateToken() throws TokenNotSuccessException;

}
