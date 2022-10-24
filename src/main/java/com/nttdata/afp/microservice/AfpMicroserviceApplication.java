package com.nttdata.afp.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AfpMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AfpMicroserviceApplication.class, args);
	}

}
