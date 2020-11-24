package com.exception.notfound;

import javassist.NotFoundException;

/**
 * Classe exception {@code ReservationNotFoundException} si une
 * {@link Réservation} n'est pas trouvée. Elle étend de
 * {@code NotFoundException}.
 * 
 * @author Pauline Humbert
 * @see NotFoundException
 */
public class ReservationNotFoundException extends NotFoundException {

	/**
	 * Constructeur de l'exception avec le message indiqué au niveau de l'appel à
	 * l'exception dans le service.
	 * 
	 * @param msg
	 */
	public ReservationNotFoundException(String msg) {
		super(msg);
	}

}
