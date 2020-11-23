package com.entity;

import java.io.Serializable;
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
 * Classe {@code Medecin} qui caractérise un médecin. Elle contient une liste
 * non-exhaustive d'attributs qui décrivent un médecin, et possède une relation
 * {@code @OneToMany} avec la classe {@code Consultation}.
 *
 * @author Jonathan Rachwal
 * @see Consultation
 *
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Medecin implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MEDECIN", unique = true, nullable = false)
	private Long idMedecin;

	@Column(name = "NOM", unique = false, nullable = false)
	private String nom;

	@Column(name = "PRENOM", unique = false, nullable = false)
	private String prenom;

	@Column(name = "ID_FORMULE", unique = false, nullable = false)
	private Long idFormule;

	@Column(name = "ADRESSE_CABINET", unique = false, nullable = false)
	private String adresseCabinet;

	@Column(name = "SPECIALITE", unique = false, nullable = false)
	private String specialite;

	@Column(name = "IDENTIFIANT", unique = true, nullable = true)
	private String identifiant;

	@Column(name = "MOT_DE_PASSE", unique = true, nullable = true)
	private String motDePasse;

	@OneToMany
	@JoinColumn(name = "FK_MEDECIN", referencedColumnName = "ID_MEDECIN", unique = false, nullable = true)
	private List<Consultation> consultations;

}
