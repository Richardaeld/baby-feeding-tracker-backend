package com.richardaeld.baby_event_tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BabyEventTrackerApplication {

	private static final Logger log = LoggerFactory.getLogger(BabyEventTrackerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BabyEventTrackerApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner() {
//		return args -> {
//			Event event = new Event(1, 1, EventType.BATH, LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS));
//			log.info("Event: {}", event);
//		};
//	}
}
