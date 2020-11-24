package com.exception.notfound;

import javassist.NotFoundException;

/**
 * Classe exception {@code MedecinNotFoundException} si un objet de {@link Medecin} n'est
 * pas trouvé. Elle étend de {@code NotFoundException}.
 * 
 * @author Sophie Lahmar
 * @see NotFoundException
 */
public class MedecinNotFoundException extends NotFoundException {

	/**
	 * Constructeur de l'exception avec le message indiqué au niveau de l'appel à
	 * l'exception dans le service.
	 * 
	 * @param msg
	 */
	public MedecinNotFoundException(String msg) {
		super(msg);
	}

}
