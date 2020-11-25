package com.exception.notsuccess;

/**
 * 
 * Classe exception {@code ReservationNotSuccessException} si action sur {@link Reservation} n'est pas réalisée au sein d'une autre méthode du service. 
 * Elle étend de {@code Throwable}. 
 * @see Throwable
 * @author Pauline Humbert
 *
 */
public class ReservationNotSuccessException extends Throwable{
	
	/**
	 * Constructeur de l'exception avec le message indiqué au niveau de l'appel à
	 * l'exception dans le service.
	 * 
	 * @param msg
	 */
	public ReservationNotSuccessException(String msg) {
		super(msg);
	}

}
