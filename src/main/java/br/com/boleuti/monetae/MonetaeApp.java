package br.com.boleuti.monetae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages={"br.com.boleuti.monetae"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class MonetaeApp extends SpringBootServletInitializer{

	public static void main(String[] args)  {
		SpringApplication.run(MonetaeApp.class, args);		
	}

	@Override
	protected SpringApplicationBuilder configure(
	        SpringApplicationBuilder application) {
	    return application.sources(MonetaeApp.class);
	}

} 
