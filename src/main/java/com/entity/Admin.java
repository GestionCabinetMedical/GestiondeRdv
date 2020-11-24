package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Admin implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ADMIN")
	private Long idAdmin;

	@Column(name = "IDENTIFIANT", unique = true, nullable = false)
	private String username;

	@Column(name = "MOT_DE_PASSE", unique = true, nullable = false)
	private String password;

}
