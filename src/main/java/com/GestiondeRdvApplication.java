package com;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.entity.FichesMedicales;
import com.service.impl.FichesMedicalesServiceImpl;


/**
 * @author Pauline Humbert
 * 
 * Classe GestiondeRdvApplication instancier des Objects et vérifier la bonne communication le back et la base de données
 *
 */
@SpringBootApplication
public class GestiondeRdvApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiondeRdvApplication.class, args);
	}
	
 
	/**
	 * @param fichesMedicalesService
	 * @return affiche tous les éléments dans la bases de données concernant les fiches médicales après création des intances de fiches médicales
	 */
	@Bean
	CommandLineRunner start(FichesMedicalesServiceImpl fichesMedicalesService)
	{
		return (args) -> {
			
			Stream.of(new FichesMedicales(null, "Grippe", "traitement contre la grippe", "pas de commentaires en plus", consultation1), 
					new FichesMedicales(null, "Angine", "traitement contre l'angine", "commentaires sur les contre indications", consultation2))
					.forEach((fiches) -> {
						fichesMedicalesService.addOrUpdate(fiches);
					});
			fichesMedicalesService.findAll().forEach(System.out::println);
		};
	}

}
