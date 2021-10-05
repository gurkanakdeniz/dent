package com.dent.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DentBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(DentBffApplication.class, args);
	}

}
