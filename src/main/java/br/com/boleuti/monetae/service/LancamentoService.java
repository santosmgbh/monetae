package br.com.boleuti.monetae.service;

import java.util.Date;
import java.util.List;

import br.com.boleuti.monetae.model.Lancamento;
import br.com.boleuti.monetae.model.chart.Chart;
import br.com.boleuti.monetae.model.chart.Serie;

public interface LancamentoService {

	List<Lancamento> findAll();

	Lancamento findByDescricao(String nome);

	Lancamento findOne(long id);

	boolean exists(Long id);

	void delete(long id);

	void save(Lancamento obj);

	void deleteAll();

	Chart getLineChartLancamentos(Date inicio, Date fim);	


}
