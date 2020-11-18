/**
 * 
 */
package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.repo.IDaoRepo;
import com.service.IDaoService;

/**
 * Classe service générique implémentant IDaoService.
 * 
 * @author Maxime
 *
 */
public class DaoServiceImpl<E> implements IDaoService<E>{
	
	@Autowired
	private IDaoRepo<E> repo;

	@Override
	public E addOrUpdate(E entite) {
		return repo.save(entite);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public E findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<E> findAll() {
		return repo.findAll();
	}

}
