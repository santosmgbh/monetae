package br.com.boleuti.monetae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.boleuti.monetae.model.CentroCusto;
import br.com.boleuti.monetae.model.Lancamento;
import br.com.boleuti.monetae.repositories.CentroCustoRepository;
import br.com.boleuti.monetae.repositories.LancamentoRepository;

@Service("lancamentoService")
@Transactional
public class LancamentoServiceImpl implements LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;


	@Override
	public List<Lancamento> findAll() {
		return lancamentoRepository.findAll();
	}

	@Override
	public Lancamento findByDescricao(String descricao) {
		return lancamentoRepository.findByDescricao(descricao);
	}

	@Override
	public Lancamento findOne(long id) {
		return lancamentoRepository.findOne(id);
	}

	@Override
	public boolean exists(Long id) {
		return lancamentoRepository.exists(id);
	}

	@Override
	public void delete(long id) {
		lancamentoRepository.delete(id);
	}

	@Override
	public void save(Lancamento obj) {
		lancamentoRepository.save(obj);
	}

	@Override
	public void deleteAll() {
		lancamentoRepository.deleteAll();
	}

}
