package com.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Reservation;
import com.enums.HeureRdv;
import com.exception.notfound.ReservationNotFoundException;
import com.repo.IReservationRepo;
import com.service.IReservationService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe service {@code ReservationServiceImpl} spécifique de
 * {@link Reservation} qui étend de la classe générique {@code DaoServiceImpl}
 * et implémente l'interface spécifique {@code IReservationService}.
 *
 * @author Sophie Lahmar
 * @see DaoServiceImpl
 * @see IReservationService
 *
 */
@Service
@Slf4j
public class ReservationServiceImpl extends DaoServiceImpl<Reservation> implements IReservationService {

	@Autowired
	IReservationRepo repo;

	@Override
	public List<Reservation> findReservationsDispo() throws ReservationNotFoundException {
		try {
			log.info("Service spécifique de Reservation : méthode 'findReservationsDispo' appelée.");
			List<Reservation> listeReservations = repo.findAll();
			if (listeReservations != null) {
				log.info("Appel repo OK.");
				listeReservations = repo.findAll().stream().filter(r -> r.isStatus() == false)
						.collect(Collectors.toList());
				if (listeReservations != null) {
					return listeReservations;
				} else {
					log.warn("Tout est complet il n'y a pas de réservations disponibles.");
				}
			} else {
				log.warn("Les réservations n'ont pas été trouvées.");
				throw new ReservationNotFoundException("List<Reservation> pas trouvée findAll = null.");
			}
		} catch (ReservationNotFoundException rnfe) {
			rnfe.printStackTrace();
			rnfe.getMessage();
		}
		return null;
	}

	@Override
	public List<HeureRdv> findResaParDateParMedecin(Date date, Long idMedecin) throws ReservationNotFoundException {
		log.info("Service spécifique de Reservation : méthode find Resa dispo par medecin appelée.");

		// aller chercher toutes les resa status true par date et pour 1 medeicn
		List<Reservation> listeResa = repo.findAllResaParDateEtMedecin(date, idMedecin);

		//lire les heure rdv prise
		List<HeureRdv> listeRdvStatusTrue = new ArrayList<HeureRdv>();
		for (Reservation e : listeResa) {
			listeRdvStatusTrue.add(e.getHeureRdv());
		}

		//deduire les heures libre
		List<HeureRdv> listeRdvDispo = new ArrayList<HeureRdv>();
		listeRdvDispo.add(HeureRdv.huit);
		listeRdvDispo.add(HeureRdv.neuf);
		listeRdvDispo.add(HeureRdv.dix);
		listeRdvDispo.add(HeureRdv.onze);
		listeRdvDispo.add(HeureRdv.quatorze);
		listeRdvDispo.add(HeureRdv.quinze);
		listeRdvDispo.add(HeureRdv.seize);
		listeRdvDispo.add(HeureRdv.dixSept);
		for (HeureRdv h : listeRdvStatusTrue) {
			for(HeureRdv rdvlibre : listeRdvDispo) {
				if (h == rdvlibre) {
					listeRdvDispo.remove(rdvlibre);
				}
			}
		}
		//renvoyer liste des heure rdv libre
		return listeRdvDispo;
	}

}
