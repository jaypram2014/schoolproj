package com.catalyst.schoolproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.catalyst.*")

public class SchoolprojApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/schoolproj");
		SpringApplication.run(SchoolprojApplication.class, args);
	}

}
