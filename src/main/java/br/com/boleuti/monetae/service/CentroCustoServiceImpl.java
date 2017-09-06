package br.com.boleuti.monetae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.boleuti.monetae.model.CentroCusto;
import br.com.boleuti.monetae.repositories.CentroCustoRepository;

@Service("centroCustoService")
@Transactional
public class CentroCustoServiceImpl implements CentroCustoService {

	@Autowired
	private CentroCustoRepository centroCustoRepository;


	@Override
	public List<CentroCusto> findAll() {
		return centroCustoRepository.findAll();
	}

	@Override
	public CentroCusto findByNome(String nome) {
		return centroCustoRepository.findByNome(nome);
	}

	@Override
	public CentroCusto findOne(long id) {
		return centroCustoRepository.findOne(id);
	}

	@Override
	public boolean exists(Long id) {
		return centroCustoRepository.exists(id);
	}

	@Override
	public void delete(long id) {
		centroCustoRepository.delete(id);
	}

	@Override
	public void save(CentroCusto obj) {
		centroCustoRepository.save(obj);
	}

	@Override
	public void deleteAll() {
		centroCustoRepository.deleteAll();
	}

}
