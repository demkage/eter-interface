package com.eter.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DockerInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerInterfaceApplication.class, args);
	}
}
