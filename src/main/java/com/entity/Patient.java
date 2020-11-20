package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
 * Classe {@code Patient} qui caractérise un patient. Elle contient une liste
 * non-exhaustive d'attributs qui décrivent un patient, et possède une relation
 * {@code @OneToMany} avec la classe {@code Reservation}.
 * 
 * @author Sophie Lahmar
 * @see Reservation
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PATIENT", unique = true, nullable = false)
	private Long idPatient;

	@Column(name = "NOM", unique = true, nullable = false)
	private String nom;

	@Column(name = "PRENOM", unique = false, nullable = false)
	private String prenom;

	@Column(name = "ADRESSE", unique = true, nullable = false)
	private String adresse;

	@Column(name = "NUMERO_SECURITE_SOCIALE", unique = true, nullable = false)
	private Long numSecu;

	@Column(name = "IDENTIFIANT", unique = true, nullable = false)
	private String identifiant;

	@Column(name = "MOT_DE_PASSE", unique = true, nullable = false)
	private String motDePasse;

	@OneToMany
	@JoinColumn(name = "FK_PATIENT", referencedColumnName = "ID_PATIENT", unique = false, nullable = true)
	private List<Reservation> reservations = new ArrayList<>();;

}
