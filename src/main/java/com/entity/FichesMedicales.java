package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe {@code FichesMedicales} qui définit les fiches médicales d'un
 * {@link Patient} : générées à la fin d'une consultation par un
 * {@link Medecin}. Elle comprend 5 attributs dont une relation
 * {@code @OneToOne} avec la classe {@code Consultation}.
 * 
 * @author Pauline Humbert
 * @see Consultation
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FichesMedicales implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FICHE_MEDICAL")
	private Long idFicheMedical;

	@Column(name = "PATHOLOGIE", nullable = false)
	private String pathologie;

	@Column(name = "TRAITEMENT",  nullable = false)
	private String traitement;

	@Column(name = "COMMENTAIRES")
	private String commentaires;

	@OneToOne
	@JoinColumn(name = "FK_CONSULTATION", referencedColumnName = "ID_CONSULTATION", unique = true, nullable = false)
	private Consultation consultation;

}
