package com.ghitaprojects.digitalgameshopproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ghitaprojects.digitalgameshopproject")
public class DigitalGameShopProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalGameShopProjectApplication.class, args);
	}

}
