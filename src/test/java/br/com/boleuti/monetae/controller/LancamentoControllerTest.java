package br.com.boleuti.monetae.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Generated;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.UriComponentsBuilderMethodArgumentResolver;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.boleuti.monetae.MonetaeAppTests;
import br.com.boleuti.monetae.controller.LancamentoController;
import br.com.boleuti.monetae.model.Lancamento;
import br.com.boleuti.monetae.model.TipoLancamento;

@Generated(value = "org.junit-tools-1.0.6")
public class LancamentoControllerTest extends MonetaeAppTests{
	
	@Autowired
	LancamentoController testSubject;

	@Before
	public void setUp() throws Exception {		
	}


	@Test
	public void testListAll() throws Exception {
		
		ResponseEntity<List<Lancamento>> result;

		// default test
		
		result = testSubject.listAll();
	}

	@Test
	public void testGetTiposLancamento() throws Exception {
		
		ResponseEntity<List<TipoLancamento>> result;

		// default test
		
		result = testSubject.getTiposLancamento();
	}

	@Test
	public void testFindByDescricao() throws Exception {
		
		String nome = "";
		ResponseEntity<?> result;

		// test 1
		
		nome = null;
		result = testSubject.findByDescricao(nome);
		Assert.assertEquals(HttpStatus.METHOD_NOT_ALLOWED, result.getStatusCode());

		// test 2
		
		nome = "";
		result = testSubject.findByDescricao(nome);
		Assert.assertEquals(HttpStatus.METHOD_NOT_ALLOWED, result.getStatusCode());
		
		nome = "teste";
		result = testSubject.findByDescricao(nome);		
		Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
		
	}

	@Test
	public void testGet() throws Exception {
		
		long id = 0L;
		ResponseEntity<?> result;

		
		result = testSubject.get(id);
		
		Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void testCreate() throws Exception {
		
		Lancamento obj = null;
		UriComponentsBuilder ucBuilder = null;
		ResponseEntity<?> result;

		
		result = testSubject.create(obj, ucBuilder);
		Assert.assertEquals(HttpStatus.METHOD_NOT_ALLOWED, result.getStatusCode());
	}

	@Test
	public void testUpdate() throws Exception {
		
		long id = 0l;
		Lancamento user = null;
		ResponseEntity<?> result;

		
		result = testSubject.update(id, user);
		Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void testDelete() throws Exception {
		
		long id = 0l;
		ResponseEntity<?> result;

		
		result = testSubject.delete(id);
		Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void testDeleteAll() throws Exception {
		
		ResponseEntity<Lancamento> result;

		result = testSubject.deleteAll();
		Assert.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
	}
}