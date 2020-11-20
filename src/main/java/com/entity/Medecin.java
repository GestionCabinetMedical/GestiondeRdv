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
 * Classe objet Medecin
 *
 * @author rachw
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

	@Column(name = "NOM", unique = false, nullable = true)
	private String nom;

	@Column(name = "PRENOM", unique = false, nullable = true)
	private String prenom;

	@Column(name = "ID_FORMULE", unique = true, nullable = false)
	private Long idFormule;

	@Column(name = "ADRESSE_CABINET", unique = false, nullable = true)
	private String adresseCabinet;

	@Column(name = "SPECIALITE", unique = false, nullable = true)
	private String specialite;

	@Column(name = "IDENTIFIANT", unique = true, nullable = true)
	private String identifiant;

	@Column(name = "MOT_DE_PASSE", unique = false, nullable = true)
	private String motDePasse;

	@OneToMany
	@JoinColumn(name = "fk_medecin", referencedColumnName = "ID_MEDECIN", unique = true, nullable = true)
	private List<Consultation> consultations;

}
