package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.repo.IDaoRepo;
import com.service.IDaoService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe service générique implémentant l'interface générique
 * {@code IDaoService}.
 *
 * @author Maxime Rembert
 * @see IDaoService
 *
 */
@Slf4j
public class DaoServiceImpl<E> implements IDaoService<E> {

	// ATTRIBUTS

	@Autowired
	private IDaoRepo<E> repo;

	// METHODES

	@Override
	public E addOrUpdate(E entite) {
		log.info("Service générique : méthode add Or Update appelée ");
		if (entite != null) {
			log.info("Appel repo OK");
			return repo.save(entite);
		}
		log.warn("Erreur méthode add or update: entite null");
		return null;
	}

	@Override
	public boolean deleteById(Long id) {
		log.info("Service générique : méthode delete By Id appelée ");
		if (id != null) {
			log.info("Appel repo OK");
			repo.deleteById(id);
			return true;
		}
		log.warn("Erreur méthode delete By id: id null");
		return false;
	}

	@Override
	public E findById(Long id) {
		log.info("Service générique : méthode find By Id appelée ");
		if (id != null) {
			log.info("Appel repo OK");
			return repo.findById(id).orElse(null);
		}
		log.warn("Erreur méthode find By id: id null");
		return null;
	}

	@Override
	public List<E> findAll() {
		log.info("Service générique : méthode find All appelée ");
		log.info("Appel repo OK");
		return repo.findAll();
	}

}
