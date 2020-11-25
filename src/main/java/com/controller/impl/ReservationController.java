package com.controller.impl;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ResponseDto;
import com.entity.Reservation;
import com.enums.HeureRdv;
import com.exception.notsuccess.ReservationNotSuccessException;
import com.exception.notsuccess.ResponseDtoNotSuccessException;
import com.service.impl.ReservationServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe controller {@code ReservationController} spécifique de
 * {@link Reservation} qui hérite de la classe générique
 * {@code DaoControllerImpl}.
 *
 * @author Sophie Lahmar, Maxime Rembert
 * @see DaoControllerImpl
 * @see IReservationController
 *
 */
@RestController
@RequestMapping(value = "/reservation")
@Slf4j
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class ReservationController extends DaoControllerImpl<Reservation> {

	// ATTRIBUTS

	@Autowired
	private ReservationServiceImpl service;

	// METHODES


	/**
	 * Méthode permettant de rechercher une liste d'enum d'HeureRdv par la date et l'id du Medecin
	 * (validée ou non par un médecin).
	 * @param idMedecin L'id du medecin concerné.
	 * @param date La date concernée.
	 * @return Un ResponseDto contenant un boolean eror, un body de liste Heure Rdv et un status Http response.
	 * @throws ReservationNotSuccessException 
	 * @throws ResponseDtoNotSuccessException

	 */
	@GetMapping (path="getAllResaParDateEtMedecin/")
	public ResponseDto<List<HeureRdv>> findResaDispoParMedecin(@RequestParam Long idMedecin, @RequestParam  String date )
			throws ReservationNotSuccessException, ResponseDtoNotSuccessException {
		log.info("Controller spécifique de Reservation : méthode find all resa dispo par medecin appelée.");
		return makeListHeureRdvResponse(service.findResaParDateParMedecin(date, idMedecin));
	}

	/**
	 * @author Maxime Rembert
	 *
	 * @param liste Liste d'instances de l'enum HeureRdv.
	 * @return ResponseDto contenant une liste de type HeureRdv.
	 * @throws ResponseDtoNotSuccessException
	 */
	public ResponseDto<List<HeureRdv>> makeListHeureRdvResponse(List<HeureRdv> liste) throws ResponseDtoNotSuccessException {
		try {
			ResponseDto<List<HeureRdv>> resp = new ResponseDto<>();
			if (liste != null) {
				log.info("makeListResponse : ResponseDto<List<HeureRdv>> Ok");
				resp.setError(false);
				resp.setBody(liste);
				resp.setStatus(HttpStatus.SC_OK);
			} else {
				log.info("makeListResponse : ResponseDto<List<HeureRdv>> Erreur");
				resp.setError(true);
				resp.setBody(null);
				resp.setStatus(HttpStatus.SC_BAD_REQUEST);
			}
			if (resp.getStatus() != 200 || resp.getStatus() != 400) {
				return resp;
			}
			else {
				log.warn("Erreur méthode 'makeListHeureRdvResponse': set du ResponseDto non fonctionnel.");
				throw new ResponseDtoNotSuccessException("Modification ResponseDto échouée");
			}
		} catch (ResponseDtoNotSuccessException dnse) {
			dnse.printStackTrace();
			dnse.getMessage();
			
		}
		return null;
	}

}
