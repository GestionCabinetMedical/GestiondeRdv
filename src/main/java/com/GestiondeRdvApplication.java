package com;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.entity.FichesMedicales;
import com.service.impl.FichesMedicalesServiceImpl;


@SpringBootApplication
public class GestiondeRdvApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiondeRdvApplication.class, args);
	}
	
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
