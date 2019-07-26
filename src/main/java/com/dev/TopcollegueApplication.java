package com.dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dev.persistence.TopCollegueRepository;

@SpringBootApplication
public class TopcollegueApplication {
   

	public static void main(String[] args) {
		SpringApplication.run(TopcollegueApplication.class, args);
		
	}

}
