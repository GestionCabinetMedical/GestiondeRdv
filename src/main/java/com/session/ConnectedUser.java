package com.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.enums.Role;
import com.exception.notsuccess.ConnectedUserNotSuccessException;

import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe {@code ConnectedUser} qui permet de stocker les données pour une
 * session d'un utilisateur. Elle implémente l'interface {@code IConnectedUser}.
 * 
 * @author Sophie Lahmar
 * @see IConnectedUser
 *
 */
@SessionScope
@Component
@Setter
@ToString
@Slf4j
public class ConnectedUser implements IConnectedUser {

	// ATTRIBUTS

	private String identifiant;
	private String motDePasse;
	private String token;
	private Role role;

	// METHODES

	@Override
	public String getIdentifiant(String token) throws ConnectedUserNotSuccessException {
		log.info("Bean ConnectedUser : méthode 'get Identifiant' appelée.");
		return token.equals(this.token) ? identifiant : null;
	}

	@Override
	public String getMotDePasse(String token) throws ConnectedUserNotSuccessException {
		log.info("Bean ConnectedUser : méthode 'get Mot De Passe' appelée.");
		return token.equals(this.token) ? motDePasse : null;
	}

	@Override
	public Role getRole(String token) throws ConnectedUserNotSuccessException {
		log.info("Bean ConnectedUser : méthode 'get Role' appelée.");
		log.debug("token input: " + token);
		log.debug("token in memory: " + this.token);
		log.debug("id: " + this.role);
		return token.equals(this.token) ? role : Role.NONE;
	}

	@Override
	public boolean testToken(String token) throws ConnectedUserNotSuccessException {
		log.info("Bean ConnectedUser : méthode 'test Token' appelée.");
		return token.equals(this.token);
	}

}
