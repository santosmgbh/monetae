package br.com.boleuti.monetae.service;

import java.util.List;

import br.com.boleuti.monetae.model.CentroCusto;
import br.com.boleuti.monetae.model.Lancamento;

public interface LancamentoService {

	List<Lancamento> findAll();

	Lancamento findByDescricao(String nome);

	Lancamento findOne(long id);

	boolean exists(Long id);

	void delete(long id);

	void save(Lancamento obj);

	void deleteAll();


}
