package br.com.boleuti.monetae.service;

import java.util.List;

import br.com.boleuti.monetae.model.CentroCusto;

public interface CentroCustoService {

	List<CentroCusto> findAll();

	CentroCusto findByNome(String nome);

	CentroCusto findOne(long id);

	boolean exists(Long id);

	void delete(long id);

	void save(CentroCusto obj);

	void deleteAll();


}
