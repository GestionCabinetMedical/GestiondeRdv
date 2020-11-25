package com.security.impl;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dto.ConnectedUserDto;
import com.enums.Role;
import com.exception.notsuccess.ConnectedUserNotSuccessException;
import com.exception.notsuccess.TokenNotSuccessException;
import com.security.ITokenManagement;
import com.session.ConnectedUser;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe {@code TokenManagement} qui permet de configurer des sessions
 * utilisateur dans le front. Elle implémente l'interface
 * {@code ITokenManagement}.
 * 
 * @author Sophie Lahmar
 * @see ITokenManagement
 *
 */
@Component
@Slf4j
public class TokenManagement implements ITokenManagement {

	// ATTRIBUTS

	@Autowired
	private ConnectedUser user;

	// METHODES
// TODO EXCEPTION sur generateToken
	@Override
	public String makePatientSession(ConnectedUserDto patient) throws ConnectedUserNotSuccessException, TokenNotSuccessException {
		try {
			if (patient != null) {
				log.info("Bean TokenManagement : méthode 'makePatientSession' appelée.");
				user.setIdentifiant(patient.getIdentifiant());
				user.setMotDePasse(patient.getMdp());
				user.setRole(Role.PATIENT);
		
				String token = generateToken();
				user.setToken(token);
				log.info("Session patient OK.");
				return token;
			}
			else {
				log.warn("Erreur méthode 'make Patient Session': patient null.");
				throw new ConnectedUserNotSuccessException("Modification ConnectedUser échouée patient = null");
			}
		} catch (ConnectedUserNotSuccessException cunse) {
			cunse.printStackTrace();
			cunse.getMessage();
		}
		return null;
	}

	@Override
	public String makeMedecinSession(ConnectedUserDto medecin) throws ConnectedUserNotSuccessException, TokenNotSuccessException {
		try {
			if (medecin != null) {
				log.info("Bean TokenManagement : méthode 'makeMedecinSession' appelée.");
				user.setIdentifiant(medecin.getIdentifiant());
				user.setMotDePasse(medecin.getMdp());
				user.setRole(Role.MEDECIN);

				String token = generateToken();
				user.setToken(token);

				log.info("Session medecin OK.");
				return token;
			}
			else {
				log.warn("Erreur méthode 'make Medecin Session': medecin null.");
				throw new ConnectedUserNotSuccessException("Modification ConnectedUser échouée medecin = null");
			}
		} catch (ConnectedUserNotSuccessException cunse) {
			cunse.printStackTrace();
			cunse.getMessage();
		}
		return null;
		
	}

	@Override
	public String makeAdminSession(ConnectedUserDto admin) throws ConnectedUserNotSuccessException, TokenNotSuccessException{
		try {
			if (admin != null) {
				log.info("Bean TokenManagement : méthode 'makeAdminSession' appelée.");
				user.setIdentifiant(admin.getIdentifiant());
				user.setMotDePasse(admin.getMdp());
				user.setRole(Role.ADMIN);
		
				String token = generateToken();
				user.setToken(token);
		
				log.info("Session admin OK.");
				return token;
			}
			else {
				log.warn("Erreur méthode 'make Admin Session': admin null.");
				throw new ConnectedUserNotSuccessException("Modification ConnectedUser échouée admin = null");
			}
		} catch (ConnectedUserNotSuccessException cunse) {
			cunse.printStackTrace();
			cunse.getMessage();
		}
		return null;
	}

	@Override
	public String generateToken() throws TokenNotSuccessException{
		try {
			log.info("Bean TokenManagement : méthode 'generateToken' appelée.");
			Random random = new SecureRandom();

			String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+-!£€&~#'@<>,.?!$%^*() {}[]=|`¬¦_";
			int tokenSize = 50;
			StringBuilder token = new StringBuilder(tokenSize);

			for (int i = 0; i < tokenSize; i++) {
				int n = random.nextInt(alphabet.length());
				token.append(alphabet.charAt(n));
			}
			if (token != null && tokenSize==50) {
				log.info("Token généré !");
				return new String(token);
			}
			else {
				log.warn("Erreur méthode 'generate Token': token null ou taille != 50.");
				throw new TokenNotSuccessException("Générer token échoué");
			}
		} catch (TokenNotSuccessException tnse) {
			tnse.printStackTrace();
			tnse.getMessage();
		}
		return null;
	}

}
