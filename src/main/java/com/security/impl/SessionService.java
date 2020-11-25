package com.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dto.ConnectedUserDto;
import com.enums.Role;
import com.exception.notsuccess.ConnectedUserDtoNotSuccessException;
import com.exception.notsuccess.ConnectedUserNotSuccessException;
import com.security.IConnectedUserConverter;
import com.security.ISessionService;
import com.session.IConnectedUser;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe {@code SessionService} qui permet de faire la communication entre un
 * objet {@link ConnectedUser} et le controller. Elle implémente l'interface
 * {@code ISessionService}.
 * 
 * @author Sophie Lahmar
 * @see ISessionService
 *
 */
@Component
@Slf4j
public class SessionService implements ISessionService {

	// ATTRIBUTS
	
	@Autowired
	private IConnectedUser user;

	@Autowired
	private IConnectedUserConverter converter;

	// METHODES
	
	@Override
	public String getUserIdentifiant(String token) throws ConnectedUserNotSuccessException{
		try {
			log.info("Bean SessionService : méthode 'get User Identifiant' appelée.");
			if (token != null) {
				log.info("getUserIdentifiant OK.");
				return user.getIdentifiant(token);
			} else {
				log.warn("Erreur méthode 'get User Identifiant': token null.");
				throw new ConnectedUserNotSuccessException("Récupération identifiant échouée token = null");
			}
		} catch (ConnectedUserNotSuccessException cunse) {
			cunse.printStackTrace();
			cunse.getMessage();
		}
		return null;
	}

	@Override
	public String getUserMotDePasse(String token) throws ConnectedUserNotSuccessException{
		try {
			log.info("Bean SessionService : méthode 'get User MotDePasse' appelée.");
			if (token != null) {
				log.info("getUserMotDePasse OK.");
				return user.getMotDePasse(token);
			} else {
				log.warn("Erreur méthode 'get User MotDePasse': token null.");
				throw new ConnectedUserNotSuccessException("Récupération mot de passe échouée token = null");
			}
		} catch (ConnectedUserNotSuccessException cunse) {
			cunse.printStackTrace();
			cunse.getMessage();
		}
		return null;
	}

	@Override
	public Role getUserRole(String token) throws ConnectedUserNotSuccessException{
		try {
			log.info("Bean SessionService : méthode 'get User Role' appelée.");
			if (token != null) {
				log.info("getUserRole OK.");
				return user.getRole(token);
			} else {
				log.warn("Erreur méthode 'get User Role': token null.");
				throw new ConnectedUserNotSuccessException("Récupération role échouée token = null");
			}
		} catch (ConnectedUserNotSuccessException cunse) {
			cunse.printStackTrace();
			cunse.getMessage();
		}
		return null;
	}

	@Override
	public ConnectedUserDto getUser(String token) throws ConnectedUserNotSuccessException, ConnectedUserDtoNotSuccessException {
		try {
			log.info("Bean SessionService : méthode 'get User' appelée.");
			log.debug("user: " + user);
			if (token != null && user.testToken(token)) {
				log.info("Conversion user to dto OK.");
				return converter.convertUserToDto(token);
			} else {
				log.warn("Erreur méthode 'get User': token null ou user.testToken null.");
				throw new ConnectedUserNotSuccessException("Récupération user échouée token ou user.testToken = null");
			}
		} catch (ConnectedUserNotSuccessException cunse) {
			cunse.printStackTrace();
			cunse.getMessage();
		}
		return null;
	}
}
