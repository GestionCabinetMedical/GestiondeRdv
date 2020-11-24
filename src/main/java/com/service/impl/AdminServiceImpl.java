package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Admin;
import com.exception.notfound.AdminNotFoundException;
import com.repo.IAdminRepo;
import com.service.IAdminService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe service {@code AdminServiceImpl} spécifique de {@link Admin} qui étend
 * de la classe générique {@code DaoServiceImpl} et implémente l'interface
 * spécifique {@code IAdminService}.
 * 
 * @author Sophie Lahmar
 * @see DaoServiceImpl
 * @see IAdminService
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
	public Admin existsByUsername(String username) throws AdminNotFoundException {
		try {
			log.info("Service spécifique de Admin: méthode 'existsByUsername' appelée.");
			if (username != null) {
				log.info("Appel repo OK.");
				Admin admin = adminRepo.findByUsername(username);
				return admin;
			} else {
				log.warn("Erreur méthode 'existsByUsername': username null.");
				throw new AdminNotFoundException("Aucun Admin n'existe avec cet identifiant.");
			}
		} catch (AdminNotFoundException anfe) {
			anfe.printStackTrace();
			anfe.getMessage();
		}
		return null;
	}

	@Override
	public Admin existsByUsernameAndPassword(String username, String password) throws AdminNotFoundException {
		try {
			log.info("Service spécifique de Admin: méthode 'existsByUsernameAndPassword' appelée.");
			if (username != null) {
				if (password != null) {
					log.info("Appel repo OK.");
					Admin admin = adminRepo.findByUsernameAndPassword(username, password);
					return admin;
				} else {
					log.warn("Erreur méthode 'existsByUsernameAndPassword': password null.");
					throw new AdminNotFoundException("Admin non trouvé : password = null.");
				}
			} else {
				log.warn("Erreur méthode 'existsByUsernameAndPassword': username null ET password null.");
				throw new AdminNotFoundException("Aucun Admin n'existe avec cet identifiant et ce mdp.");
			}
		} catch (AdminNotFoundException anfe) {
			anfe.printStackTrace();
			anfe.getMessage();
		}
		return null;
	}

}
