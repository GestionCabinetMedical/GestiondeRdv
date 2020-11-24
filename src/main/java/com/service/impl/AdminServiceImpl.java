package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Admin;
import com.repo.IAdminRepo;
import com.service.IAdminService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Sophie Lahmar
 *
 */
@Service
@Slf4j
public class AdminServiceImpl extends DaoServiceImpl<Admin> implements IAdminService {

	// ATTRIBUTS

	@Autowired
	private IAdminRepo adminRepo;

	// METHODES

	@Override
	public Admin existsByUsername(String username) {
		// TODO : ajouter les exceptions !
		
		if (username != null) {
			log.info("Service spécifique de Admin: méthode 'existsByUsername' appelée.");
			return adminRepo.findByUsername(username);
		} else {
			log.warn("Erreur méthode 'existsByUsername': username null.");
			return null;
		}
	}

	@Override
	public Admin existsByUsernameAndPassword(String username, String password) {
		// TODO : ajouter les exceptions !
		
		if (username != null && password != null) {
			log.info("Service spécifique de Admin: méthode 'existsByUsernameAndPassword' appelée.");
			return adminRepo.findByUsernameAndPassword(username, password);
		} else {
			log.warn("Erreur méthode 'existsByUsernameAndPassword': username null.");
			return null;
		}
	}

}
