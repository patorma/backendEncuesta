package com.patriciocontreras.encuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EncuestaApplication  implements CommandLineRunner  {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EncuestaApplication.class, args);
	}

	// se van a generar las claves de ejemplo para encriptarlas
	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		// vamos a crear 4 contraseñas encriptadas
		for (int i = 0; i < 4; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println(passwordBcrypt);
		}
	}

}
