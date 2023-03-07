package com.tbkj.rent;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RentApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(RentApplication.class, args);
	}

}
