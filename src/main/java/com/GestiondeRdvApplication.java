package com;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.entity.Consultation;
import com.entity.FichesMedicales;
import com.service.impl.ConsultationServiceImpl;
import com.service.impl.FichesMedicalesServiceImpl;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author Pauline Humbert
 *
 * Classe GestiondeRdvApplication instancier des Objects et vérifier la bonne communication le back et la base de données
 *
 */
@EnableDiscoveryClient
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
	CommandLineRunner start(FichesMedicalesServiceImpl fichesMedicalesService, ConsultationServiceImpl consultationService)
	{
		return (args) -> {
			
			Stream.of(new Consultation(1L, 1L,1L),
					new Consultation(2L, 2L,2L))
			.forEach((consultations) -> {
				consultationService.addOrUpdate(consultations);
			});
			consultationService.findAll().forEach(System.out::println);
			
			Stream.of(new FichesMedicales(1L, "Grippe", "traitement contre la grippe", "pas de commentaires en plus", consultationService.findById(1L)),
					new FichesMedicales(2L, "Angine", "traitement contre l'angine", "commentaires sur les contre indications", consultationService.findById(2L)))
					.forEach((fiches) -> {
						fichesMedicalesService.addOrUpdate(fiches);
					});
			fichesMedicalesService.findAll().forEach(System.out::println);
		};
	}

}
