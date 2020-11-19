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
 * La classe {@code Patient} caractérise un patient. Cette classe contient une
 * liste non-exhaustive d'attributs qui décrivent un patient.
 * 
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

}
