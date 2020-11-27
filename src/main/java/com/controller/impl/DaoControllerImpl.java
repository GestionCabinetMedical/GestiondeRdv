package com.controller.impl;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.controller.IDaoController;
import com.dto.ResponseDto;
import com.exception.notsuccess.ResponseDtoNotSuccessException;
import com.service.IDaoService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe controller générique implémentant l'interface générique
 * {@code IDaoController}. Son rôle est de contrôler les flux de données
 * entrant.
 *
 * @author Maxime Rembert
 * @see IDaoController
 *
 */
@Slf4j
public class DaoControllerImpl<E> implements IDaoController<E> {

	// ATTRIBUTS

	@Autowired
	private IDaoService<E> serv;

	// METHODES

	@Override
	@PostMapping
	public ResponseDto<E> add(@RequestBody E entite) throws ResponseDtoNotSuccessException {
		log.info("Controller générique : méthode add appelée");
		E e = serv.addOrUpdate(entite);
		return makeDtoResponse(e);
	}

	@Override
	@PutMapping
	public ResponseDto<E> update(@RequestBody E entite) throws ResponseDtoNotSuccessException {
		log.info("Controller générique : méthode update appelée");
		log.info("print objet - "+entite);
		E e = serv.addOrUpdate(entite);
		return makeDtoResponse(e);
	}

	@Override
	@DeleteMapping(path = "/{id}")
	public ResponseDto<Boolean> deleteById(@PathVariable Long id) throws ResponseDtoNotSuccessException {
		log.info("Controller générique : méthode delete By Id appelée");
		boolean status = serv.deleteById(id);
		return makeBooleanResponse(status);
	}

	@Override
	@GetMapping(path = "/{id}")
	public ResponseDto<E> findById(@PathVariable Long id) throws ResponseDtoNotSuccessException {
		log.info("Controller générique : méthode find By Id appelée");
		E e = serv.findById(id);
		return makeDtoResponse(e);
	}

	@Override
	@GetMapping(path = "/all")
	public ResponseDto<List<E>> findAll() throws ResponseDtoNotSuccessException {
		log.info("Controller générique : méthode find all appelée");
		List<E> liste = serv.findAll();
		return makeListResponse(liste);
	}

	public ResponseDto<E> makeDtoResponse(E e) throws ResponseDtoNotSuccessException {
		try {
			ResponseDto<E> resp = new ResponseDto<>();
			if (e != null) {
				log.info("makeDtoResponse : responseDto Ok");
				resp.setBody(e);
				resp.setError(false);
				resp.setMessage("Success");
				resp.setStatus(HttpStatus.SC_OK);
			} else {
				log.info("makeDtoResponse : responseDto Erreur");
				resp.setError(true);
				resp.setBody(null);
				resp.setMessage("Error");
				resp.setStatus(HttpStatus.SC_BAD_REQUEST);
			}
			if (resp.getStatus() != 200 || resp.getStatus() != 400) {
				return resp;
			}
			else {
				log.warn("Erreur méthode 'makeDtoResponse': set du ResponseDto non fonctionnel.");
				throw new ResponseDtoNotSuccessException("Modification ResponseDto échouée");
			}
		} catch (ResponseDtoNotSuccessException dnse) {
			dnse.printStackTrace();
			dnse.getMessage();

		}
		return null;
	}

	public ResponseDto<Boolean> makeBooleanResponse(Boolean status) throws ResponseDtoNotSuccessException {
		try {
			ResponseDto<Boolean> resp = new ResponseDto<>();
			if (status) {
				log.info("makeBooleanResponse : ResponseDto<Boolean> Ok");
				resp.setError(false);
				resp.setBody(null);
				resp.setStatus(HttpStatus.SC_OK);
			} else {
				log.info("makeBooleanResponse : ResponseDto<Boolean> Erreur");
				resp.setError(true);
				resp.setBody(null);
				resp.setStatus(HttpStatus.SC_BAD_REQUEST);
			}
			if (resp.getStatus() != 200 || resp.getStatus() != 400) {
				return resp;
			}
			else {
				log.warn("Erreur méthode 'makeBooleanResponse': set du ResponseDto non fonctionnel.");
				throw new ResponseDtoNotSuccessException("Modification ResponseDto échouée");
			}
		} catch (ResponseDtoNotSuccessException dnse) {
			dnse.printStackTrace();
			dnse.getMessage();

		}
		return null;
	}

	public ResponseDto<List<E>> makeListResponse(List<E> liste) throws ResponseDtoNotSuccessException {
		try {
			ResponseDto<List<E>> resp = new ResponseDto<>();
			if (liste != null) {
				log.info("makeListResponse : ResponseDto<List<E>> Ok");
				resp.setError(false);
				resp.setBody(liste);
				resp.setStatus(HttpStatus.SC_OK);
			} else {
				log.info("makeListResponse : ResponseDto<List<E>> Erreur");
				resp.setError(true);
				resp.setBody(null);
				resp.setStatus(HttpStatus.SC_BAD_REQUEST);
			}
			if (resp.getStatus() != 200 || resp.getStatus() != 400) {
				return resp;
			}
			else {
				log.warn("Erreur méthode 'makeListResponse': set du ResponseDto non fonctionnel.");
				throw new ResponseDtoNotSuccessException("Modification ResponseDto échouée");
			}
		} catch (ResponseDtoNotSuccessException dnse) {
			dnse.printStackTrace();
			dnse.getMessage();

		}
		return null;
	}

}
