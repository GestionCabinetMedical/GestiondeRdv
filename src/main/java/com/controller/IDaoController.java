/**
 * 
 */
package com.controller;

import java.util.List;

/**
 * Interfacle controller générique responsables des méthodes CRUD pour
 * l'ensemble du projet.
 * 
 * @author Maxime
 *
 */
public interface IDaoController<E> {

	public E add(E entite);

	public E update(E entite);

	public boolean deleteById(Long id);

	public E findById(Long id);

	public List<E> findAll();

}
