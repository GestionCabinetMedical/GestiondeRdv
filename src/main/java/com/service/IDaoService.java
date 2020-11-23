package com.service;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * Interface service générique responsable des méthodes CRUD à l'ensemble du
 * projet.
 *
 * @author Maxime Rembert
 *
 */
@NoRepositoryBean
public interface IDaoService<E> {

	/**
	 * Méthode permettant d'ajouter ou modifier une entité E dans la base de
	 * données.
	 *
	 * @param entite Entité à ajouter ou modifier.
	 * @return L'entité correspondante (peut être null).
	 */
	public E addOrUpdate(E entite);

	/**
	 * Méthode permettant de supprimer une entité de la base de données par son id.
	 *
	 * @param id Id de l'entité à supprimer.
	 * @return True si la suppression a été effectuée, false sinon.
	 */
	public boolean deleteById(Long id);

	/**
	 * Méthode permettant de trouver une entité par son id.
	 * 
	 * @param id Id de l'entité à rechercher.
	 * @return L'entité recherchée (peut être null).
	 */
	public E findById(Long id);

	/**
	 * Méthode permettant de rechercher la liste de toutes les entités.
	 *
	 * @return Une liste d'entités (peut être vide).
	 */
	public List<E> findAll();

}
