package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sophie Lahmar
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PATIENT")
	private Long idPatient;

	@Column(name = "NOM")
	private String nom;

	@Column(name = "PRENOM")
	private String prenom;

	@Column(name = "ADRESSE")
	private String adresse;

	@Column(name = "NUMERO_SECURITE_SOCIALE")
	private Long numSecu;

	@Column(name = "IDENTIFIANT")
	private String identifiant;

	@Column(name = "MOT_DE_PASSE")
	private String motDePasse;

	@OneToMany
	@JoinColumn(name = "fk_patient", referencedColumnName = "ID_PATIENT")
	private Reservation reservation;

	/**
	 * @author Sophie Lahmar
	 * 
	 */
	public void connexion() {
		// TODO: implement the method
	}

	/**
	 * @author Sophie Lahmar
	 * 
	 */
	public void consulterPlanning() {
		// TODO: implement the method
	}

	/**
	 * @author Sophie Lahmar
	 * 
	 * @return
	 */
	public Reservation reserverRdv() {
		// TODO: implement the method
		return reservation;
	}

	/**
	 * @author Sophie Lahmar
	 * 
	 * @return
	 */
	public Reservation modifierRdv() {
		// TODO: implement the method
		return reservation;
	}

	/**
	 * @author Sophie Lahmar
	 * 
	 */
	public void consulterFicheMedicale() {
		// TODO: implement the method
	}

	/**
	 * @author Sophie Lahmar
	 * 
	 * @return
	 */
	public Patient modifierProfil() {
		// TODO: implement the method
		return null;
	}

	/**
	 * @author Sophie Lahmar
	 * 
	 */
	public void remplirQuestionnaireSatisfaction() {
		// TODO: implement the method
	}

}
