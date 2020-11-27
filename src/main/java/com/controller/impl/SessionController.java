package com.controller.impl;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ConnectedUserDto;
import com.dto.ResponseDto;
import com.enums.Role;
import com.security.ISessionService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe controller {@code AdminController} pour la récupération des données
 * associées à une session.
 * 
 * @author Sophie Lahmar
 * 
 */
@RestController
@RequestMapping(path = "/gestion-rdv/session")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
@Slf4j
public class SessionController {

	@Autowired
	ISessionService service;

	/**
	 * Méthode utilisée par le service authentification du front, qui permet de
	 * récupérer un utilisateur connecté.
	 * 
	 * @param token Le token associé à la session.
	 * @return Un objet ConnectedUserDto.
	 */
	@PostMapping(path = "/user")
	public ConnectedUserDto getUser(@RequestBody String token) {
		log.info("Controller Session : méthode 'getUser' appelée.");
		ConnectedUserDto user = service.getUser(token);

		if (user != null) {
			log.info("Utilisateur trouvé !");
			user.setError(false);
			user.setStatus(HttpStatus.SC_OK);
			return user;
		} else {
			log.info("Utilisateur non trouvé.");
			user.setError(true);
			user.setStatus(HttpStatus.SC_NOT_FOUND);
			return null;
		}
	}

	/**
	 * Méthode utilisée par le service authentification du front, qui permet de
	 * récupérer le {@link Role} d'un utilisateur (admin/medecin/patient/none).
	 * 
	 * @param token Le token associé à la session.
	 * @return Un objet ResponseDto contenant un role.
	 */
	@PostMapping(path = "/role")
	public ResponseDto<Role> getUserRole(@RequestBody String token) {
		log.info("Controller Session : méthode 'getUserRole' appelée");
		Role role = service.getUserRole(token);
		int status;

		if (role == Role.NONE) {
			log.info("Erreur : utilisateur sans rôle.");
			status = HttpStatus.SC_BAD_REQUEST;
			return null;
		} else {
			log.info("Controller Session: méthode 'getUserRole' OK.");
			status = HttpStatus.SC_OK;
			return makeRoleDtoResponse(role);
		}
	}

	/**
	 * Méthode utilisée par le service authentification du front, qui permet de
	 * récupérer l'identifiant d'un utilisateurle associé à la session.
	 * 
	 * @param token Le token associé à la session.
	 * @return Un objet ResponseDto contenant l'identifiant en format string.
	 */
	@PostMapping(path = "/identifiant")
	public ResponseDto<String> getUserIdentifiant(@RequestBody String token) {
		log.info("Controller Session : méthode 'getUserIdentifiant' appelée");
		String identifiant = service.getUserIdentifiant(token);
		int status;

		if (identifiant != null) {
			log.info("Controller Session: méthode 'getUserIdentifiant' OK.");
			status = HttpStatus.SC_OK;
			return makeDtoResponse(identifiant);
		} else {
			log.info("Erreur méthode 'getUserIdentifiant': identifiant = null.");
			status = HttpStatus.SC_BAD_REQUEST;
			return null;
		}
	}

	/**
	 * Méthode utilisée par le service authentification du front, qui permet de
	 * récupérer le mot de passe d'un utilisateurle associé à la session.
	 * 
	 * @param token Le token associé à la session.
	 * @return Un objet ResponseDto contenant le mot de passe en format string.
	 */
	@PostMapping(path = "/mdp")
	public ResponseDto<String> getUserMotDePasse(@RequestBody String token) {
		log.info("Controller Session : méthode 'getUserMotDePasse' appelée");
		String mdp = service.getUserMotDePasse(token);
		int status;

		if (mdp != null) {
			log.info("Controller Session: méthode 'getUserMotDePasse' OK.");
			status = HttpStatus.SC_OK;
			return makeDtoResponse(mdp);
		} else {
			log.info("Erreur méthode 'getUserMotDePasse': mdp = null.");
			status = HttpStatus.SC_BAD_REQUEST;
			return null;
		}
	}

	/**
	 * 
	 * @param role
	 * @return
	 */
	public ResponseDto<Role> makeRoleDtoResponse(Role role) {
		ResponseDto<Role> resp = new ResponseDto<>();
		if (role != null) {
			log.info("makeDtoResponse : responseDto OK.");
			resp.setBody(role);
			resp.setError(false);
			resp.setMessage("Success");
			resp.setStatus(HttpStatus.SC_OK);
		} else {
			log.info("Erreur makeDtoResponse : responseDto null.");
			resp.setError(true);
			resp.setBody(null);
			resp.setMessage("Error");
			resp.setStatus(HttpStatus.SC_BAD_REQUEST);
		}
		return resp;
	}

	/**
	 * 
	 * @param identifiant
	 * @return
	 */
	private ResponseDto<String> makeDtoResponse(String stringDto) {
		ResponseDto<String> resp = new ResponseDto<>();
		if (stringDto != null) {
			log.info("makeDtoResponse : responseDto OK.");
			resp.setBody(stringDto);
			resp.setError(false);
			resp.setMessage("Success");
			resp.setStatus(HttpStatus.SC_OK);
		} else {
			log.info("Erreur makeDtoResponse : responseDto null.");
			resp.setError(true);
			resp.setBody(null);
			resp.setMessage("Error");
			resp.setStatus(HttpStatus.SC_BAD_REQUEST);
		}
		return resp;
	}

}
