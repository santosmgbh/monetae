package br.com.boleuti.monetae.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.boleuti.monetae.model.Lancamento;
import br.com.boleuti.monetae.model.chart.Chart;
import br.com.boleuti.monetae.model.chart.Serie;
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

	@Override
	public Chart getLineChartLancamentos(Date inicio, Date fim) {
		Chart chartResultadoLancamentos = new Chart();
		chartResultadoLancamentos.setLabel("Resultado dos lançamentos");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		List<String> labelSeries = lancamentoRepository.getLabelSeries(sdf.format(inicio), sdf.format(fim));
		chartResultadoLancamentos.setSeriesLabel(labelSeries);
		
		Serie serieDebitos = new Serie();
		List<Object[]> itensDebitos = lancamentoRepository.getSerieDebitos(labelSeries);
		List<String> valoresSerieDebito = new ArrayList<String>();
		for(int i = 0; i < labelSeries.size(); i++){			
			String valor = labelSeries.indexOf(itensDebitos.get(i)[0].toString()) != -1 && i < itensDebitos.size()   ?String.valueOf(itensDebitos.get(i)[1].toString()):"0.0";
			valoresSerieDebito.add(valor);
		}
		serieDebitos.setValores(valoresSerieDebito);		
		chartResultadoLancamentos.addSerie(serieDebitos);
		
		Serie serieCreditos = new Serie();
		List<Object[]> itensCreditos = lancamentoRepository.getSerieCreditos(labelSeries);
		List<String> valoresSerieCredito = new ArrayList<String>();
		for(int i = 0; i < labelSeries.size(); i++){	
			String valor = labelSeries.indexOf(itensCreditos.get(i)[0].toString()) != -1 && i < itensCreditos.size() ?String.valueOf(itensCreditos.get(i)[1].toString()):"0.0";
			valoresSerieCredito.add(valor);
		}
		serieCreditos.setValores(valoresSerieCredito);
		chartResultadoLancamentos.addSerie(serieCreditos);
		
		return chartResultadoLancamentos;
	}


}
