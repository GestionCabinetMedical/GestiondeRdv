package com.security.impl;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dto.ConnectedUserDto;
import com.entity.Role;
import com.security.ITokenManagement;
import com.session.ConnectedUser;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Sophie Lahmar
 *
 */
@Component
@Slf4j
public class TokenManagement implements ITokenManagement {

	@Autowired
	private ConnectedUser user;

	@Override
	public String makePatientSession(ConnectedUserDto patient) {
		log.info("Bean TokenManagement : méthode 'makePatientSession' appelée.");
		user.setIdentifiant(patient.getIdentifiant());
		user.setMotDePasse(patient.getMdp());
		user.setRole(Role.Patient);

		String token = generateToken();
		user.setToken(token);

		log.info("Session patient OK.");
		return token;
	}

	@Override
	public String makeMedecinSession(ConnectedUserDto medecin) {
		log.info("Bean TokenManagement : méthode 'makeMedecinSession' appelée.");
		user.setIdentifiant(medecin.getIdentifiant());
		user.setMotDePasse(medecin.getMdp());
		user.setRole(Role.Medecin);

		String token = generateToken();
		user.setToken(token);

		log.info("Session medecin OK.");
		return token;
	}

	@Override
	public String makeAdminSession(ConnectedUserDto admin) {
		log.info("Bean TokenManagement : méthode 'makeAdminSession' appelée.");
		user.setIdentifiant(admin.getIdentifiant());
		user.setMotDePasse(admin.getMdp());
		user.setRole(Role.Admin);

		String token = generateToken();
		user.setToken(token);

		log.info("Session admin OK.");
		return token;
	}

	@Override
	public String generateToken() {
		log.info("Bean TokenManagement : méthode 'generateToken' appelée.");
		Random random = new SecureRandom();

		String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+-!£€&~#'@<>,.?!$%^*() {}[]=|`¬¦_";
		int tokenSize = 50;
		StringBuilder token = new StringBuilder(tokenSize);

		for (int i = 0; i < tokenSize; i++) {
			int n = random.nextInt(alphabet.length());
			token.append(alphabet.charAt(n));
		}
		log.info("Token généré !");
		return new String(token);
	}

}
