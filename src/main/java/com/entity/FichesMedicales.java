package com.entity;

import java.io.Serializable;
import java.util.List;

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
 * @author Pauline Humbert
 * 
 * Classe qui définit les fiches médicales : générer à la fin d'une consulatation par un médecin
 * Elle comprend 5 attributs dont une relation OneToOne avec la classe Consultation
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FichesMedicales implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FICHE_MEDICAL", unique = true, nullable = false)
	private Long idFicheMedical;
	@Column(name="PATHOLOGIE", unique = false, nullable = true)
	private String pathologie;
	@Column(name="TRAITEMENT",  unique = false, nullable = false)
	private String traitement;
	@Column(name="COMMENTAIRES",  unique = false, nullable = false)
	private String commentaires;
	@OneToOne
	@JoinColumn(name="FK_CONSULTATION", referencedColumnName = "ID_CONSULTATION",  unique = true, nullable = true)
	private Consultation consultation;

}
