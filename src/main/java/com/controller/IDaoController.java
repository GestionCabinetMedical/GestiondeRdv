package com.controller;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.dto.ResponseDto;
import com.exception.notsuccess.ResponseDtoNotSuccessException;

/**
 * Interface controller générique responsable des méthodes CRUD pour l'ensemble
 * du projet.
 * 
 * @author Maxime Rembert
 *
 */
@NoRepositoryBean
public interface IDaoController<E> {

	/**
	 * Méthode permettant d'ajouter une entité E à la base de données.
	 * 
	 * @param entite Entité à sauvegarder.
	 * @return L'entité sauvegardée (peut être null)
	 * @throws ResponseDtoNotSuccessException 
	 */
	public ResponseDto<E> add(E entite) throws ResponseDtoNotSuccessException;

	/**
	 * Méthode permettant de modifier une entité dans la base de données.
	 * 
	 * @param entite Entité à modifier.
	 * @return L'entité modifiée (peut être null).
	 * @throws ResponseDtoNotSuccessException 
	 */
	public ResponseDto<E> update(E entite) throws ResponseDtoNotSuccessException;

	/**
	 * Méthode permettant de supprimer une entité dans la base de données.
	 * 
	 * @param id Id de l'entité à supprimer.
	 * @return True si la suppression a été effectuée, false sinon.
	 * @throws ResponseDtoNotSuccessException 
	 */
	public ResponseDto<Boolean> deleteById(Long id) throws ResponseDtoNotSuccessException;

	/**
	 * Méthode permettant de rechercher une entité E dans la base de données.
	 * 
	 * @param id Id de l'entité à rechercher.
	 * @return L'entité recherchée (peut être null).
	 * @throws ResponseDtoNotSuccessException 
	 */
	public ResponseDto<E> findById(Long id) throws ResponseDtoNotSuccessException;

	/**
	 * Méthode permettant la recherche de toutes les entités.
	 * 
	 * @return Une liste d'entités (peut être vide).
	 * @throws ResponseDtoNotSuccessException 
	 */
	public ResponseDto<List<E>> findAll() throws ResponseDtoNotSuccessException;

}
