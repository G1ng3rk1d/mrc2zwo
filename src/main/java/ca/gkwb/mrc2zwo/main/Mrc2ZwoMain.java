package ca.gkwb.mrc2zwo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ca.gkwb.mrc2zwo.config.AppConfig;

@SpringBootApplication
public class Mrc2ZwoMain {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AppConfig.class);
		app.run(args);
	}		
	
}