/**
 * 
 */
package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.repo.IDaoRepo;
import com.service.IDaoService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe service générique implémentant IDaoService.
 * 
 * @author Maxime rembert
 *
 */
@Slf4j
public class DaoServiceImpl<E> implements IDaoService<E>{
	
	@Autowired
	private IDaoRepo<E> repo;

	@Override
	public E addOrUpdate(E entite) {
		log.info("Servcie générique : méthode add Or Update appelée ");
		log.info("Appel repo OK");
		return repo.save(entite);
	}

	@Override
	public boolean deleteById(Long id) {
		log.info("Servcie générique : méthode delete By Id appelée ");
		log.info("Appel repo OK");
		repo.deleteById(id);
		return true;
	}

	@Override
	public E findById(Long id) {
		log.info("Servcie générique : méthode find By Id appelée ");
		log.info("Appel repo OK");
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<E> findAll() {
		log.info("Servcie générique : méthode find All appelée ");
		log.info("Appel repo OK");
		return repo.findAll();
	}

}
