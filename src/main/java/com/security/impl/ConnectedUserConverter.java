package com.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dto.ConnectedUserDto;
import com.exception.notsuccess.ConnectedUserDtoNotSuccessException;
import com.exception.notsuccess.ConnectedUserNotSuccessException;
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
	public ConnectedUserDto convertUserToDto(String token) throws ConnectedUserDtoNotSuccessException, ConnectedUserNotSuccessException {
		try {
			log.info("Bean ConnectedUserDto : méthode 'convertUserToDto' appelée.");
			if (token != null) {
				ConnectedUserDto dto = new ConnectedUserDto();
				dto.setIdentifiant(user.getIdentifiant(token));
				dto.setMdp(user.getMotDePasse(token));
				dto.setRole(user.getRole(token));
				log.info("Conversion user OK.");
				return dto;
			} else {
				log.warn("Erreur méthode 'convertUserToDto': token null.");
				throw new ConnectedUserDtoNotSuccessException("ConnectedUserDto non modifié : token = null.");
				
			}
		} catch (ConnectedUserDtoNotSuccessException cudnfe) {
			cudnfe.printStackTrace();
			cudnfe.getMessage();
		}
		return null;
	}

}
