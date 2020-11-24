package com.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dto.ConnectedUserDto;
import com.security.IConnectedUserConverter;
import com.session.IConnectedUser;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe {@code ConnectedUserConverter} qui permet qui permet d'extraire les
 * données de session de {@link ConnectedUserDto}. Elle implémente l'interface
 * {@code IConnectedUserConverter}.
 * 
 * @author Sophie Lahmar
 * @see IConnectedUserConverter
 *
 */
@Component
@Slf4j
public class ConnectedUserConverter implements IConnectedUserConverter {

	// ATTRIBUTS
	
	@Autowired
	IConnectedUser user;

	// METHODES
	
	@Override
	public ConnectedUserDto convertUserToDto(String token) {
		log.info("Bean ConnectedUserDto : méthode 'convertUserToDto' appelée.");
		if (token != null) {
			log.info("Conversion user OK.");
			ConnectedUserDto dto = new ConnectedUserDto();
			dto.setIdentifiant(user.getIdentifiant(token));
			dto.setMdp(user.getMotDePasse(token));
			dto.setRole(user.getRole(token));
			return dto;
		} else {
			log.warn("Erreur méthode 'convertUserToDto': token null.");
			return null;
		}
	}

}
