package com.tennet.opdracht;

import com.tennet.opdracht.entities.ContactPerson;
import com.tennet.opdracht.entities.ProductInstallation;
import com.tennet.opdracht.repositories.ContactPersonRepo;
import com.tennet.opdracht.repositories.ProductInstallationRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class OpdrachtApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpdrachtApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ContactPersonRepo contactPersonRepo, ProductInstallationRepo productInstallationRepo) {
		return args -> {
			ContactPerson p1 = new ContactPerson(null, "Enverny Iengibe", "0000AA", "Almelo", "12a");
			ContactPerson p2 = new ContactPerson(null, "Malik Mujic", "0000BA", "Enschede", "12b");
			contactPersonRepo.save(p1);
			contactPersonRepo.save(p2);
			contactPersonRepo.save(new ContactPerson(null, "Bernadette Nijhuis", "0010AB", "Arnhem", "12c"));
			contactPersonRepo.save(new ContactPerson(null, "Kevin Barelds", "0020AA", "Enschede", "12c"));
			contactPersonRepo.save(new ContactPerson(null, "Branko van Abel", "0001CB", "Arnhem", "12d"));
			contactPersonRepo.save(new ContactPerson(null, "Stephan Koehler", "0002BB", "Arnhem", "12e"));
			contactPersonRepo.save(new ContactPerson(null, "Lebron James", "1003CC", "Almelo", "12f"));
			contactPersonRepo.save(new ContactPerson(null, "Stephen Curry", "1020DC", "Almelo", "12g"));

			productInstallationRepo.save(new ProductInstallation(null, "Public Install", p1, 30.45));
			productInstallationRepo.save(new ProductInstallation(null, "Server Install", p2, 75.35));
		};
	}

	@Bean
	public CorsFilter corsFilter () {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
