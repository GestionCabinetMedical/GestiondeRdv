package com.security;

import com.dto.ConnectedUserDto;

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
	 */
	public String makePatientSession(ConnectedUserDto patient);

	/**
	 * Méthode permettant de configurer la session d'un médecin en enregistrant les
	 * données nécessaires et en générant un token.
	 * 
	 * @param user Un PatientUpdateDto.
	 * @return Un token sous format String.
	 */
	public String makeMedecinSession(ConnectedUserDto medecin);

	/**
	 * Méthode permettant de configurer la session d'un admin en enregistrant les
	 * données nécessaires et en générant un token.
	 * 
	 * @param admin Un AdminUpdateDto.
	 * @return Un token sous format String.
	 */
	public String makeAdminSession(ConnectedUserDto admin);

	/**
	 * Méthode permettant de générer un token de 20 caractères.
	 * 
	 * @return Un String aléatoire, 20 caractères de long.
	 */
	public String generateToken();

}
