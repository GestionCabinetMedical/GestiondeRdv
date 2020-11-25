package com.exception.notsuccess;

/**
 * 
 * Classe exception {@code ConsultationNotSuccessException} si action sur {@link Consultation} n'est pas réalisée au sein d'une autre méthode du service. 
 * Elle étend de {@code Throwable}. 
 * @see Throwable
 * @author Pauline Humbert
 *
 */
public class ConsultationNotSuccessException extends Throwable{
	
	/**
	 * Constructeur de l'exception avec le message indiqué au niveau de l'appel à
	 * l'exception dans le service.
	 * 
	 * @param msg
	 */
	public ConsultationNotSuccessException(String msg) {
		super(msg);
	}

}
