package mnemosine.mnemosine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"mnemosine.controller", "mnemosine.service", "mnemosine.swagger"})
@SpringBootApplication
public class MnemosineApplication {
	public static void main(String[] args) {
		SpringApplication.run(MnemosineApplication.class, args);
	}
}
