package com.shetu.microservicesdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//@EnableEurekaServer
public class MicroServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceDemoApplication.class, args);
	}

}
