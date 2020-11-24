package com.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dto.ConnectedUserDto;
import com.entity.Role;
import com.security.IConnectedUserConverter;
import com.security.ISessionService;
import com.session.IConnectedUser;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe : couche entre l'objet ConnectedUser et le controller.
 * 
 * @author Sophie Lahmar
 *
 */
@Component
@Slf4j
public class SessionService implements ISessionService {

	@Autowired
	private IConnectedUser user;

	@Autowired
	private IConnectedUserConverter converter;

	@Override
	public String getUserIdentifiant(String token) {
		log.info("Bean SessionService : méthode 'get User Identifiant' appelée.");
		if (token != null) {
			log.info("getUserIdentifiant OK.");
			return user.getIdentifiant(token);
		} else {
			log.warn("Erreur méthode 'get User Identifiant': token null.");
			return null;
		}
	}

	@Override
	public String getUserMotDePasse(String token) {
		log.info("Bean SessionService : méthode 'get User MotDePasse' appelée.");
		if (token != null) {
			log.info("getUserMotDePasse OK.");
			return user.getMotDePasse(token);
		} else {
			log.warn("Erreur méthode 'get User MotDePasse': token null.");
			return null;
		}
	}

	@Override
	public Role getUserRole(String token) {
		log.info("Bean SessionService : méthode 'get User Role' appelée.");
		if (token != null) {
			log.info("getUserRole OK.");
			return user.getRole(token);
		} else {
			log.warn("Erreur méthode 'get User Role': token null.");
			return null;
		}
	}

	@Override
	public ConnectedUserDto getUser(String token) {
		log.info("Bean SessionService : méthode 'get User' appelée.");
		log.debug("user: " + user);
		if (token != null && user.testToken(token)) {
			log.info("Conversion user to dto OK.");
			return converter.convertUserToDto(token);
		} else {
			log.warn("Erreur méthode 'get User': token null.");
			return null;
		}
	}

}
