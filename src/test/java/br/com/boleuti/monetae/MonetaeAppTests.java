package br.com.boleuti.monetae;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.boleuti.monetae.MonetaeApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonetaeApp.class)
@TestPropertySource(locations="classpath:test.properties")
public class MonetaeAppTests {

	@Test
	public void contextLoads() {
	}

}