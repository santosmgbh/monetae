package br.com.boleuti.monetae.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Generated;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.boleuti.monetae.MonetaeAppTests;
import br.com.boleuti.monetae.model.CentroCusto;
import br.com.boleuti.monetae.model.User;
import org.junit.Assert;

@Generated(value = "org.junit-tools-1.0.6")
public class CentroCustoServiceTest extends MonetaeAppTests {

	@Autowired
	CentroCustoService testSubject;
	

	@Test
	public void testFindAll() throws Exception {
		
		List<CentroCusto> result;

		// default test
		
		result = testSubject.findAll();
	}

	@Test
	public void testFindByNome() throws Exception {
		
		String nome = "";
		CentroCusto result;

		// default test
		
		result = testSubject.findByNome(nome);
	}

	@Test
	public void testFindOne() throws Exception {
		
		long id = 0l;
		CentroCusto result;

		// default test
		
		result = testSubject.findOne(id);
	}

	@Test
	public void testExists() throws Exception {
		
		Long id = 0l;
		boolean result;

		// default test
		
		result = testSubject.exists(id);
	}

	@Test
	public void testDelete() throws Exception {
		
		long id = 0l;

		// default test
		
		testSubject.delete(id);
	}

	@Test
	public void testSave() throws Exception {
		User user = new User();
		user.setLogin("test1");
		user.setNome("test 1");
		user.setSenha("123");
		
		CentroCusto obj = new CentroCusto();
		obj.setNome("Centro custo 1");
		obj.setDataCadastro(new Date());
		obj.setUser(user);

		// default test
		
		testSubject.save(obj);
		
		Assert.assertNotNull(obj.getId());
	}

	@Test
	public void testDeleteAll() throws Exception {
		

		testSubject.deleteAll();
	}
}