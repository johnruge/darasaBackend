package com.darasa.darasa_ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DarasaAiApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
        		// Only set properties if they exist in .env file
		if (dotenv.get("DB_URL") != null) {
			System.setProperty("DB_URL", dotenv.get("DB_URL"));
		}
		if (dotenv.get("DB_USER") != null) {
			System.setProperty("DB_USER", dotenv.get("DB_USER"));
		}
		if (dotenv.get("DB_PASSWORD") != null) {
			System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		}
		if (dotenv.get("JWT_SECRET") != null) {
			System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
		}
		if (dotenv.get("SIGNUP_CODE") != null) {
			System.setProperty("SIGNUP_CODE", dotenv.get("SIGNUP_CODE"));
		}
		SpringApplication.run(DarasaAiApplication.class, args);
	}

}
