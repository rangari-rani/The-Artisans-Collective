package com.art;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class TheArtisansCollectiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheArtisansCollectiveApplication.class, args);

		// Generate and print the secret key
//		String secretKey = JWTKeyGenerator.generateSecretKey();
//		System.out.println("Generated Secret Key: " + secretKey);
	}

	// Static nested class for JWT key generation
//	public static class JWTKeyGenerator {
//		public static String generateSecretKey() {
//			SecureRandom secureRandom = new SecureRandom();
//			byte[] randomBytes = new byte[32]; // 256-bit key (32 bytes)
//			secureRandom.nextBytes(randomBytes);
//			return Base64.getEncoder().encodeToString(randomBytes);
//		}
//	}

	// Simple controller to display "Hello World" at http://localhost:5454/
	@RestController
	public class HelloWorldController {
		@GetMapping("/")
		public String sayHello() {
			return "Welcome to THE ARTISANS COLLECTIVE";
		}
	}
}
