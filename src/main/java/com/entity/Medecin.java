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
	@Column(name = "ID_MEDECIN")
	private Long idMedecin;

	@Column(name = "NOM", nullable = false)
	private String nom;

	@Column(name = "PRENOM", nullable = false)
	private String prenom;

	@Column(name = "ID_FORMULE", nullable = true)
	private Long idFormule;

	@Column(name = "ADRESSE_CABINET",  nullable = false)
	private String adresseCabinet;

	@Column(name = "SPECIALITE",  nullable = false)
	private String specialite;

	@Column(name = "IDENTIFIANT", unique = true, nullable = false)
	private String identifiant;

	@Column(name = "MOT_DE_PASSE", nullable = false)
	private String motDePasse;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_MEDECIN", referencedColumnName = "ID_MEDECIN", nullable = true)
	private List<Consultation> consultations= new ArrayList<>();

}
