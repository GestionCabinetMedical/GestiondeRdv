package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@Column(name = "ID_PATIENT")
	private Long idPatient;

	@Column(name = "NOM", nullable = false)
	private String nom;

	@Column(name = "PRENOM",  nullable = false)
	private String prenom;

	@Column(name = "ADRESSE",  nullable = false)
	private String adresse;

	@Column(name = "NUMERO_SECURITE_SOCIALE", unique = true, nullable = false)
	private Long numSecu;

	@Column(name = "IDENTIFIANT", unique = true, nullable = false)
	private String identifiant;

	@Column(name = "MOT_DE_PASSE", unique = true, nullable = false)
	private String motDePasse;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_PATIENT", referencedColumnName = "ID_PATIENT")
	private List<Reservation> reservations = new ArrayList<>();

}
