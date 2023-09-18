package com.abrigo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication

@OpenAPIDefinition(info = @Info(title = "API Gerenciamento do Abrigo - CWB", version = "1", description = "API para gerenciar o acolhimento das pessoas em situação de rua no abrigo CWB"))
public class AbrigoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbrigoApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("1111"));
	}

}
