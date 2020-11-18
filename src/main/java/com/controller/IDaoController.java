/**
 * 
 */
package com.controller;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.dto.ResponseDto;

/**
 * Interfacle controller générique responsables des méthodes CRUD pour
 * l'ensemble du projet.
 * 
 * @author Maxime Rembert
 *
 */
@NoRepositoryBean
public interface IDaoController<E> {

	/**
	 * Méthode permettant d'ajouter une entité E à la base de donnée.
	 * 
	 * @param entite Entité à sauvegarder.
	 * @return L'entité sauvegardé (peut être null)
	 */
	public ResponseDto<E> add(E entite);

	/**
	 * Méthode permettant de modifier une entité dans la base de donnée.
	 * 
	 * @param entite Entité à modifier.
	 * @return L'entité modifié (peut être null).
	 */
	public ResponseDto<E> update(E entite);

	/**
	 * Méthode permettant de supprimer une entité dans la base de donnée.
	 * 
	 * @param id Id de l'entité à supprimer.
	 * @return True si la suppression à été effectuer, false sinon.
	 */
	public ResponseDto<Boolean> deleteById(Long id);

	/**
	 * Méthode permettant de recherche une entité E dans la base de donnée.
	 * 
	 * @param id Id de l'entité à rechercher.
	 * @return L'entité rehcerché (peut être null).
	 */
	public ResponseDto<E> findById(Long id);

	/**
	 * Méthode permettant la recherche de toutesles entités.
	 * 
	 * @return Une liste d'entité (peut être vide).
	 */
	public ResponseDto<List<E>> findAll();

}
