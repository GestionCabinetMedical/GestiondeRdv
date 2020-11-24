package com.session;

import com.enums.Role;

/**
 * Interface {@code IConnectedUser} qui permet le stockage des détails d'une
 * session utilisateur.
 * 
 * @author Sophie Lahmar
 *
 */
public interface IConnectedUser {

	/**
	 * Méthode permettant de récupérer l'identifiant d'un utilisateur dans la base
	 * de données.
	 * 
	 * @param token Identificateur de l'identifiant utilisateur.
	 * @return Un objet de type String correspondant à l'identifiant de
	 *         l'utilisateur dans la BDD.
	 */
	public String getIdentifiant(String token);

	/**
	 * Méthode permettant de récupérer le mot de passe d'un utilisateur dans la base
	 * de données.
	 * 
	 * @param token Identificateur du mot de passe de l'utilisateur.
	 * @return Un objet de type String correspondant au mot de passe de
	 *         l'utilisateur dans la BDD.
	 */
	public String getMotDePasse(String token);

	/**
	 * Méthode permettant de récupérer le role d'un utilisateur (Admin, Medecin ou
	 * Patient) dans la base de données.
	 * 
	 * @param token Identificateur du role de l'utilisateur.
	 * @return Un objet de type Role correspondant au role de l'utilisateur dans la
	 *         BDD.
	 */
	public Role getRole(String token);

	/**
	 * Méthode permettant de récupérer l'identifiant d'un utilisateur dans la base
	 * de données.
	 * 
	 * @param token
	 * @return True si le token entré est égal au token de la BDD, false sinon.
	 */
	public boolean testToken(String token);

}
