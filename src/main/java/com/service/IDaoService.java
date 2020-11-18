/**
 *
 */
package com.service;

import java.util.List;

/**
 * Interface service générique responsable des méthodes CRUD à l'ensemble du
 * projet.
 * 
 * @author Maxime
 *
 */
public interface IDaoService<E> {

	public E addOrUpdate(E entite);

	public void deleteById(Long id);

	public E findById(Long id);

	public List<E> findAll();

}
