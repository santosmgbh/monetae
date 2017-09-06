package br.com.boleuti.monetae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"br.com.boleuti.monetae"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class MonetaeApp {

	public static void main(String[] args) {
		SpringApplication.run(MonetaeApp.class, args);
	}
}
