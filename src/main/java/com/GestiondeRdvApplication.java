package com;

import java.sql.Date;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.entity.Consultation;
import com.entity.FichesMedicales;
import com.service.impl.ConsultationServiceImpl;
import com.service.impl.FichesMedicalesServiceImpl;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.entity.Patient;
import com.entity.Reservation;
import com.service.IPatientService;
import com.service.IReservationService;

/**
 * Classe principale {@code GestiondeRdvApplication} permet d'instancier des
 * Objets et de vérifier la bonne communication entre le back et la base de
 * données.
 * 
 * @author Pauline Humbert
 *
 */
@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
public class GestiondeRdvApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiondeRdvApplication.class, args);
	}

	/**
	 * Méthode de l'interface CommandLineRunner permettant d'exécuter les beans qui
	 * sont en entrée de la méthode.
	 * 
	 * @author Sophie Lahmar
	 *
	 */
//	@Bean
//	CommandLineRunner start(IPatientService patientService, IReservationService reservationService,
//			FichesMedicalesServiceImpl fichesMedicalesService, ConsultationServiceImpl consultationService) {
//		Patient p1 = new Patient(null, "NIVON", "Steven", "Mandelieu", 19478l, "stiti", "nini", null);
//		Patient p2 = new Patient(null, "MOREAU", "Cathy", "Marseille", 26757l, "mom", "lolo", null);
//
//		Reservation r1 = new Reservation(null, false, Date.valueOf("2020-11-11"), false);
//		Reservation r2 = new Reservation(null, false, Date.valueOf("2020-11-16"), false);
//
//		return (args) -> {
//			System.out.println("****** PATIENTS ******");
//			Stream.of(p1, p2).forEach((Patient p) -> {
//				patientService.addOrUpdate(p);
//			});
//
//			System.out.println("****** RESERVATIONS ******");
//			Stream.of(r1, r2).forEach((Reservation r) -> {
//				reservationService.addOrUpdate(r);
//			});
//
//			patientService.findAll().forEach(System.out::println);
//			reservationService.findAll().forEach(System.out::println);
//
//			// pour tester fiches médicales
//			Stream.of(new Consultation(1L, 1L, 1L), new Consultation(2L, 2L, 2L)).forEach((consultations) -> {
//				consultationService.addOrUpdate(consultations);
//			});
//			consultationService.findAll().forEach(System.out::println);
//
//			Stream.of(
//					new FichesMedicales(1L, "Grippe", "traitement contre la grippe", "pas de commentaires en plus",
//							consultationService.findById(1L)),
//					new FichesMedicales(2L, "Angine", "traitement contre l'angine",
//							"commentaires sur les contre indications", consultationService.findById(2L)))
//					.forEach((fiches) -> {
//						fichesMedicalesService.addOrUpdate(fiches);
//					});
//			fichesMedicalesService.findAll().forEach(System.out::println);
//		};
//	}

}
