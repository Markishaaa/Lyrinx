package com.markisha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("model")
public class LyricsWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LyricsWebApplication.class, args);
	}

}
