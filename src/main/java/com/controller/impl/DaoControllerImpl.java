/**
 * 
 */
package com.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.controller.IDaoController;
import com.service.IDaoService;

/**
 * Classe controller générique implémentant IDaoController.
 * 
 * @author Maxime
 *
 */
public class DaoControllerImpl<E> implements IDaoController<E>{
	
	@Autowired
	private IDaoService<E> serv;

	@Override
	public E add(E entite) {
		if (entite != null) {
			return serv.addOrUpdate(entite);
		}
		return null;
	}

	@Override
	public E update(E entite) {
		if (entite != null) {
			return serv.addOrUpdate(entite);
		}
		return null;
	}

	@Override
	public boolean deleteById(Long id) {
		if (id != null) {
			serv.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public E findById(Long id) {
		if (id != null) {
			return serv.findById(id);
		}
		return null;
	}

	@Override
	public List<E> findAll() {
		return serv.findAll();
	}

}
