package mnemosine.mnemosine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"mnemosine.controller", "mnemosine.service", "mnemosine.config", "mnemosine.swagger"})
@SpringBootApplication
public class MnemosineApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(MnemosineApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MnemosineApplication.class);
	}
}
