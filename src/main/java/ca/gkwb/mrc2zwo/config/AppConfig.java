package ca.gkwb.mrc2zwo.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import ca.gkwb.mrc2zwo.bo.WorkoutConverterBO;
import ca.gkwb.mrc2zwo.bo.WorkoutConverterBOImpl;
import ca.gkwb.mrc2zwo.controller.MainController;

@Configuration
@Component
@EnableAutoConfiguration
public class AppConfig {

	@Bean
	public WorkoutConverterBO getWorkoutConverter() {
		return new WorkoutConverterBOImpl();
	}
	
	@Bean
	public MainController getMainController() {
		return new MainController(getWorkoutConverter());
	}
}
