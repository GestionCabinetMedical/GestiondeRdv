package com;

import java.sql.Date;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.entity.Patient;
import com.entity.Reservation;
import com.service.IPatientService;
import com.service.IReservationService;

@EnableDiscoveryClient
@SpringBootApplication
public class GestiondeRdvApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiondeRdvApplication.class, args);
	}

	/**
	 * @author Sophie Lahmar
	 * 
	 * @param patientService
	 * @param reservationService
	 * @return 
	 */
	@Bean
	CommandLineRunner start(IPatientService patientService, IReservationService reservationService) {
		Patient p1 = new Patient(null, "NIVON", "Steven", "Mandelieu", 19478l, "stiti", "nini", null);
		Patient p2 = new Patient(null, "MOREAU", "Cathy", "Marseille", 26757l, "mom", "lolo", null);

		Reservation r1 = new Reservation(null, false, Date.valueOf("19/11/2020"), false, p1);
		Reservation r2 = new Reservation(null, false, Date.valueOf("30/11/2020"), false, p2);

		return (args) -> {
			System.out.println("****** PATIENTS ******");
			Stream.of(p1, p2).forEach((Patient p) -> {
				patientService.addOrUpdate(p);
			});

			System.out.println("****** RESERVATIONS ******");
			Stream.of(r1, r2).forEach((Reservation r) -> {
				reservationService.addOrUpdate(r);
			});

			patientService.findAll().forEach(System.out::println);
			reservationService.findAll().forEach(System.out::println);
		};
	}

}
