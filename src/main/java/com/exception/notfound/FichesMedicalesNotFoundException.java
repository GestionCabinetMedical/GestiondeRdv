package com.exception.notfound;

import com.entity.FichesMedicales;

import javassist.NotFoundException;

/**
 * Classe exception {@code FichesMedicalesNotFoundException} si un objet de
 * {@link FichesMedicales} n'est pas trouvé. Elle étend de
 * {@code NotFoundException}.
 * 
 * @author Pauline Humbert
 * @see NotFoundException
 */
public class FichesMedicalesNotFoundException extends NotFoundException {

	/**
	 * Constructeur de l'exception avec le message indiqué au niveau de l'appel à
	 * l'exception dans le service
	 * 
	 * @param msg
	 */
	public FichesMedicalesNotFoundException(String msg) {
		super(msg);
	}

}
