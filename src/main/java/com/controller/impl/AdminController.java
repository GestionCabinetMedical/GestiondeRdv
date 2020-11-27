package com.controller.impl;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ConnectedUserDto;
import com.dto.ConnexionDto;
import com.dto.ResponseDto;
import com.entity.Admin;
import com.enums.Role;
import com.exception.notfound.AdminNotFoundException;
import com.exception.notsuccess.AdminNotSuccessException;
import com.exception.notsuccess.ConnectedUserDtoNotSuccessException;
import com.exception.notsuccess.ConnectedUserNotSuccessException;
import com.exception.notsuccess.ResponseDtoNotSuccessException;
import com.exception.notsuccess.TokenNotSuccessException;
import com.security.ITokenManagement;
import com.service.IAdminService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe controller {@code AdminController} spécifique de {@link Admin} qui
 * hérite de la classe générique {@code DaoControllerImpl}.
 *
 * @author Sophie Lahmar
 * @see DaoControllerImpl
 *
 */
@RestController
@RequestMapping(value = "/gestion-rdv/admin")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
@Slf4j
public class AdminController extends DaoControllerImpl<Admin> {

	// ATTRIBUTS

	@Autowired
	IAdminService adminService;

	@Autowired
	ITokenManagement tokenManage;

	// METHODES

	/**
	 * Méthode permettant de vérifier l'existence d'un admin par son identifiant.
	 *
	 * @param username Identifiant de l'admin.
	 * @return Un admin s'il existe déjà, null sinon.
	 * @throws AdminNotFoundException
	 * @throws AdminNotSuccessException
	 * @throws ResponseDtoNotSuccessException
	 */
	@GetMapping(value = "/identifiant")
	public ResponseDto<Admin> existsByUsername(@RequestParam(name = "identifiant") String username)
			throws AdminNotFoundException, AdminNotSuccessException, ResponseDtoNotSuccessException {
		log.info("Controller spécifique de Admin : méthode 'existsByUsername' appelée.");
		Admin admin = adminService.existsByUsername(username);
		return makeDtoResponse(admin);
	}

	/**
	 * Méthode permettant de vérifier l'existence d'un admin par son identifiant et
	 * son mot de passe (= connexion).
	 *
	 * @param username Identifiant de l'admin recherché.
	 * @param password Mot de passe de l'admin recherché.
	 * @return Un admin s'il existe déjà, null sinon.
	 * @throws AdminNotFoundException
	 * @throws AdminNotSuccessException
	 * @throws TokenNotSuccessException
	 * @throws ConnectedUserNotSuccessException
	 * @throws ConnectedUserDtoNotSuccessException
	 */
	@PostMapping(path = "/connexion")
	public ConnexionDto existsByUsernameAndPassword(@RequestParam String username, @RequestParam String mdp)
			throws AdminNotFoundException, AdminNotSuccessException, ConnectedUserNotSuccessException,
			TokenNotSuccessException, ConnectedUserDtoNotSuccessException {
		log.info("Controller spécifique de Admin : méthode 'existsByUsernameAndPassword' appelée.");

		ConnexionDto connexionDto = new ConnexionDto();
		try {
			Admin admin = adminService.existsByUsernameAndPassword(username, mdp);
			if (admin != null) {
				log.info("Admin existant dans la BDD.");
				ConnectedUserDto adminDto = makeConnectedUserDtoResponse(admin);
				String token = tokenManage.makeAdminSession(adminDto);

				connexionDto.setUser(adminDto);
				connexionDto.setToken(token);
				connexionDto.setError(false);
				connexionDto.setStatus(HttpStatus.SC_OK);
				return connexionDto;

			} else {
				log.info("Aucune entité Admin ne correspond à ces entrées.");
				connexionDto.setUser(null);
				connexionDto.setToken(null);
				connexionDto.setError(true);
				connexionDto.setStatus(HttpStatus.SC_BAD_REQUEST);
				return connexionDto;
			}

		} catch (NullPointerException e) {
			log.info("Null Pointer Exception : " + e.getMessage());
			connexionDto.setUser(null);
			connexionDto.setToken(null);
			return connexionDto;
		}
	}

	/**
	 * Méthode permettant de créer une réponse de type ConnectedUserDto, et
	 * d'injecter les paramètres de connection d'un Admin (identifiant et mdp) dans
	 * un adminDto.
	 *
	 * @param admin Instance de la classe Admin.
	 * @return Un objet ConnectedUserDto.
	 * @throws ConnectedUserDtoNotSuccessException
	 */
	private ConnectedUserDto makeConnectedUserDtoResponse(Admin admin) throws ConnectedUserDtoNotSuccessException {
		try {
			ConnectedUserDto response = new ConnectedUserDto();
			if (admin != null) {
				log.info("makeConnectedUserDtoResponse : admin OK.");
				response.setRole(Role.ADMIN);
				response.setIdentifiant(admin.getUsername());
				response.setMdp(admin.getPassword());
				response.setError(false);
				response.setMsg("Success !");
			} else {
				log.info("Erreur 'makeConnectedUserDtoResponse' : admin null.");
				response.setRole(Role.NONE);
				response.setError(true);
				response.setMsg("Error: Bad request.");
			}
			if (response.getMsg() != null) {
				return response;
			} else {
				log.warn("Erreur méthode 'makeConnectedUserDtoResponse': set du ConnectedUserDto non fonctionnel.");
				throw new ConnectedUserDtoNotSuccessException("Modification ConnectedUserDto échouée");
			}
		} catch (ConnectedUserDtoNotSuccessException dnse) {
			dnse.printStackTrace();
			dnse.getMessage();

		}
		return null;

	}

}
