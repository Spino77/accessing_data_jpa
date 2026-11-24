package com.example.accessingdatajpa;

import com.example.accessingdatajpa.model.Customer;
import com.example.accessingdatajpa.model.Incident;
import com.example.accessingdatajpa.repository.CustomerRepository;
import com.example.accessingdatajpa.repository.IncidentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class AccessingDataJpaApplication {

	private static final Logger logger = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository customerRepository, IncidentRepository incidentRepository ) {
		return (args) -> {
			// save a few customers
			customerRepository.save(new Customer("Jack", "Bauer", "Jack@email"));
			customerRepository.save(new Customer("Chloe", "O'Brian", "Chloe@email"));
			customerRepository.save(new Customer("Kim", "Bauer", "Kim@email"));
			customerRepository.save(new Customer("David", "Palmer", "David@email"));
			customerRepository.save(new Customer("Michelle", "Dessler", "Michelle@email"));

			incidentRepository.save(new Incident("Incidencia1", "Prova", LocalDateTime.now(), "OBERT"));
			incidentRepository.save(new Incident("Incidencia2", "Prova2", LocalDateTime.now(), "EN_PROGRES"));

			// fetch all customers
			logger.info("Customers found with findAll():");
			logger.info("-------------------------------");
			customerRepository.findAll().forEach(customer -> {
				logger.info(customer.toString());
			});
			logger.info("");

			// fetch an individual customer by ID
			Customer customer = customerRepository.findById(1L);
			logger.info("Customer found with findById(1L):");
			logger.info("--------------------------------");
			logger.info(customer.toString());
			logger.info("");

			// fetch customers by last name
			logger.info("Customer found with findByLastName('Bauer'):");
			logger.info("--------------------------------------------");
			customerRepository.findByLastName("Bauer").forEach(bauer -> {
				logger.info(bauer.toString());
			});
			logger.info("");
		};
	}

}