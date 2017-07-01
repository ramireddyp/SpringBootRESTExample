package com.someco.mainapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.*"})
public class SpringBootRestExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestExampleApplication.class, args);
	}
}
