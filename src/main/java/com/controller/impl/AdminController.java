package com.controller.impl;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ConnectedUserDto;
import com.dto.ConnexionDto;
import com.dto.ResponseDto;
import com.entity.Admin;
import com.entity.Role;
import com.security.ITokenManagement;
import com.service.IAdminService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Sophie Lahmar
 *
 */
@RestController
@RequestMapping(value = "/admin")
//@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
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
	 */
	@GetMapping(value = "/exists/identifiant")
	public ResponseDto<Admin> existsByUsername(@RequestParam(name = "identifiant") String username) {
		log.info("Controller spécifique de Admin : méthode 'existsByUsername' appelée.");
		Admin admin = adminService.existsByUsername(username);
		return makeDtoResponse(admin);
	}


	/**
	 * @author Sophie Lahmar
	 * 
	 * @param tableau
	 * @return
	 */
	@PostMapping(path = "/identifiant-mdp")
	public ConnexionDto existsByUsernameAndPassword(@RequestBody String[] tableau) {
		// TODO : ajouter les exceptions !
		log.info("Controller spécifique de Admin : méthode 'existsByUsernameAndPassword' appelée.");
		
		ConnexionDto connexionDto = new ConnexionDto();

		try {
			String username = tableau[0];
			String mdp = tableau[1];
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
	 * 
	 * @param admin
	 * @return
	 */
	private ConnectedUserDto makeConnectedUserDtoResponse(Admin admin) {
		ConnectedUserDto response = new ConnectedUserDto();
		if (admin != null) {
			log.info("makeConnectedUserDtoResponse : admin OK.");
			response.setRole(Role.Admin);
			response.setIdentifiant(admin.getUsername());
			response.setMdp(admin.getPassword());
			response.setError(false);
			response.setStatus(HttpStatus.SC_OK);
		} else {
			log.info("Erreur 'makeConnectedUserDtoResponse' : admin null.");
			response.setRole(Role.None);
			response.setError(true);
			response.setStatus(HttpStatus.SC_BAD_REQUEST);
		}
		return response;
	}

}
