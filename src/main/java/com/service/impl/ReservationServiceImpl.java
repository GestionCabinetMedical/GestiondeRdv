package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Reservation;
import com.enums.HeureRdv;
import com.exception.notsuccess.ReservationNotSuccessException;
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
	public List<HeureRdv> findResaParDateParMedecin(String stringDate, Long idMedecin)
			throws ReservationNotSuccessException {
		try {
			log.info("Service spécifique de Reservation : méthode find Resa dispo par medecin appelée.");
			// aller chercher toutes les resa status true par date et pour 1 medeicn
			List<Reservation> listeResa = repo.findAllResaParDateEtMedecin(stringDate, idMedecin);
			log.info("appel repo find all resa par date et idMedecin OK");
			
			if (listeResa != null) {
				// lire les heure rdv prise
				List<HeureRdv> listeRdvStatusTrue = new ArrayList<HeureRdv>();
				for (Reservation e : listeResa) {
					listeRdvStatusTrue.add(e.getHeureRdv());
				}
				log.info("Parcours liste des résa et ajout des heures déjà occupée dans listeRdvStatusTrue OK");
				
				// deduire les heures libre
				List<HeureRdv> listeRdvDispo = new ArrayList<HeureRdv>();
				listeRdvDispo.add(HeureRdv.huit);
				listeRdvDispo.add(HeureRdv.neuf);
				listeRdvDispo.add(HeureRdv.dix);
				listeRdvDispo.add(HeureRdv.onze);
				listeRdvDispo.add(HeureRdv.quatorze);
				listeRdvDispo.add(HeureRdv.quinze);
				listeRdvDispo.add(HeureRdv.seize);
				listeRdvDispo.add(HeureRdv.dixSept);
				log.info("Ajout des heures de rdv possibles dans listeRdvDispo OK");
				
				//parcours de la liste et suppression des rdv deja pris
				for (HeureRdv h : listeRdvStatusTrue) {
					switch (h) {
					case huit:
						listeRdvDispo.remove(HeureRdv.huit);
						break;
					case neuf:
						listeRdvDispo.remove(HeureRdv.neuf);
						break;
					case dix:
						listeRdvDispo.remove(HeureRdv.dix);
						break;
					case onze:
						listeRdvDispo.remove(HeureRdv.onze);
						break;
					case quatorze:
						listeRdvDispo.remove(HeureRdv.quatorze);
						break;
					case quinze:
						listeRdvDispo.remove(HeureRdv.quinze);
						break;
					case seize:
						listeRdvDispo.remove(HeureRdv.seize);
						break;
					case dixSept:
						listeRdvDispo.remove(HeureRdv.dixSept);
						break;
					default:
						break;
					}
				}
				log.info("Supprimer les horaires déjà réservés de la liste des rdv Dispo OK");
				// renvoyer liste des heure rdv libre
				return listeRdvDispo;
			}
			else {
				log.warn("Erreur méthode 'findResaParDateParMedecin': listeResa = null.");
				throw new ReservationNotSuccessException("Repo réservation a échoué pour trouver les réservations: listeResa = null");
			}
		} catch (ReservationNotSuccessException rnse) {
			
		}
		return null;
		
	}
}
