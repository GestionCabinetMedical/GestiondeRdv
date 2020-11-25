package com.exception.notsuccess;

/**
 * Classe exception {@code ConnectedUserNotSuccessException} si action sur {@link ConnectedUser} n'est pas réalisée au sein d'une autre méthode. 
 * Elle étend de {@code Throwable}.
 * 
 * @author Pauline Humbert
 * @see Throwable
 */
public class ConnectedUserNotSuccessException extends Throwable {

	/**
	 * Constructeur de l'exception avec le message indiqué au niveau de l'appel à
	 * l'exception dans le service
	 * 
	 * @param msg
	 */
	public ConnectedUserNotSuccessException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
