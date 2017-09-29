package br.com.boleuti.monetae.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.boleuti.monetae.model.Lancamento;
import br.com.boleuti.monetae.model.TipoLancamento;
import br.com.boleuti.monetae.model.chart.Chart;
import br.com.boleuti.monetae.model.chart.Serie;
import br.com.boleuti.monetae.repositories.LancamentoRepository;
import br.com.boleuti.monetae.repositories.TipoLancamentoRepository;

@Service("lancamentoService")
@Transactional
public class LancamentoServiceImpl implements LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private TipoLancamentoRepository tipoLancamentoRepository;


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
		List<String> seriesLabel = new ArrayList<String>();
		SimpleDateFormat formatLabel = new SimpleDateFormat("MM-yyyy");		
		Calendar dtInicio = Calendar.getInstance();
		dtInicio.setTime(inicio);
		Calendar dtFim = Calendar.getInstance();
		dtFim.setTime(fim);
		List<Date> datas = new ArrayList<Date>();
		while(dtInicio.get(Calendar.MONTH) != dtFim.get(Calendar.MONTH)){
			dtInicio.add(Calendar.MONTH, 1);	
			seriesLabel.add(formatLabel.format(dtInicio.getTime()));
			datas.add(dtInicio.getTime());
		}
		
		chartResultadoLancamentos.setSeriesLabel(seriesLabel);
		
		NumberFormat formatNumber = new DecimalFormat("#.####");
		
		List<Object[]> creditos = lancamentoRepository.getSomaValores(inicio, fim, tipoLancamentoRepository.findByNome("RECEBIMENTO"));
		Serie serieCreditos = new Serie();	
		serieCreditos.setTitle("Créditos");
		serieCreditos.setColor("blue");
		a: for( Date d: datas){
			serieCreditos.addLabel(String.valueOf(formatLabel.format(d)));
			 for(Object[] o: creditos){							
				if(formatLabel.format(d).equals(o[0])){
					serieCreditos.addValor(formatNumber.format(o[1]));
					continue a;
				}
			}
			serieCreditos.addValor("0");			
		}
		chartResultadoLancamentos.addSerie(serieCreditos);
		
		List<Object[]> debitos = lancamentoRepository.getSomaValores(inicio, fim, tipoLancamentoRepository.findByNome("PAGAMENTO"));
		Serie serieDebitos = new Serie();
		serieDebitos.setTitle("Débitos");
		serieDebitos.setColor("red");
		a: for( Date d: datas){
			serieDebitos.addLabel(String.valueOf(formatLabel.format(d)));
			 for(Object[] o: debitos){							
				if(formatLabel.format(d).equals(o[0])){					
					serieDebitos.addValor(formatNumber.format(o[1]));
					continue a;
				}
			}
			 serieDebitos.addValor("0");			
		}
		chartResultadoLancamentos.addSerie(serieDebitos);
		
		List<Object[]> saldos = lancamentoRepository.getSaldos(inicio, fim);
		Serie serieSaldos = new Serie();
		serieSaldos.setTitle("Saldos");
		serieSaldos.setColor("green");
		a: for( Date d: datas){
			serieSaldos.addLabel(String.valueOf(formatLabel.format(d)));
			 for(Object[] o: saldos){							
				if(formatLabel.format(d).equals(o[0])){					
					serieSaldos.addValor(formatNumber.format(o[1]));
					continue a;
				}
			}
			 serieSaldos.addValor("0");			
		}
		chartResultadoLancamentos.addSerie(serieSaldos);

		
		return chartResultadoLancamentos;
	}


}
