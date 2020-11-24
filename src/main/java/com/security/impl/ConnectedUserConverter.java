package com.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dto.ConnectedUserDto;
import com.security.IConnectedUserConverter;
import com.session.IConnectedUser;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Sophie Lahmar
 *
 */
@Component
@Slf4j
public class ConnectedUserConverter implements IConnectedUserConverter {

	@Autowired
	IConnectedUser user;

	@Override
	public ConnectedUserDto convertUserToDto(String token) {
		log.info("Bean PatientConnected : méthode 'convert User To Dto' appelée.");
		if (token != null) {
			log.info("Conversion user OK.");
			ConnectedUserDto dto = new ConnectedUserDto();
			dto.setIdentifiant(user.getIdentifiant(token));
			dto.setMdp(user.getMotDePasse(token));
			dto.setRole(user.getRole(token));
			return dto;
		} else {
			log.warn("Erreur méthode 'convert User To Dto': token null.");
			return null;
		}
	}

}
