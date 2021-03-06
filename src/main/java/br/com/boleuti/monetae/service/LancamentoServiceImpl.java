package br.com.boleuti.monetae.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
	
	@Autowired
    private EntityManagerFactory entityManagerFactory;



	public List<Lancamento> findAll() {
		return lancamentoRepository.findAll();
	}

	public Lancamento findByDescricao(String descricao) {
		return lancamentoRepository.findByDescricao(descricao);
	}

	public Lancamento findOne(long id) {
		return lancamentoRepository.findOne(id);
	}

	public boolean exists(Long id) {
		return lancamentoRepository.exists(id);
	}

	public void delete(long id) {
		lancamentoRepository.delete(id);
	}

	public void save(Lancamento obj) {
		lancamentoRepository.save(obj);
	}

	public void deleteAll() {
		lancamentoRepository.deleteAll();
	}

	public Chart getLineChartLancamentos(Date inicio, Date fim) {
		Chart chartResultadoLancamentos = new Chart();
		chartResultadoLancamentos.setLabel("Resultado dos lan�amentos");
		List<String> seriesLabel = new ArrayList<String>();
		SimpleDateFormat formatLabel = new SimpleDateFormat("MM-yyyy");		
		Calendar dtInicio = Calendar.getInstance();
		dtInicio.setTime(inicio);
		Calendar dtFim = Calendar.getInstance();
		dtFim.setTime(fim);
		List<Date> datas = new ArrayList<Date>();
		while(dtInicio.before(dtFim)){
			dtInicio.add(Calendar.MONTH, 1);	
			seriesLabel.add(formatLabel.format(dtInicio.getTime()));
			datas.add(dtInicio.getTime());
		}
		
		chartResultadoLancamentos.setSeriesLabel(seriesLabel);
		
		
		List<Object[]> creditos = lancamentoRepository.getSomaValores(inicio, fim, tipoLancamentoRepository.findByNome("RECEBIMENTO"));
		Serie serieCreditos = new Serie();	
		serieCreditos.setTitle("Cr�ditos");
		serieCreditos.setColor("blue");
		a: for( Date d: datas){
			serieCreditos.addLabel(String.valueOf(formatLabel.format(d)));
			 for(Object[] o: creditos){							
				if(formatLabel.format(d).equals(o[0])){
					serieCreditos.addValor(o[1]);
					continue a;
				}
			}
			serieCreditos.addValor("0");			
		}
		chartResultadoLancamentos.addSerie(serieCreditos);
		
		List<Object[]> debitos = lancamentoRepository.getSomaValores(inicio, fim, tipoLancamentoRepository.findByNome("PAGAMENTO"));
		Serie serieDebitos = new Serie();
		serieDebitos.setTitle("D�bitos");
		serieDebitos.setColor("red");
		a: for( Date d: datas){
			serieDebitos.addLabel(String.valueOf(formatLabel.format(d)));
			 for(Object[] o: debitos){							
				if(formatLabel.format(d).equals(o[0])){					
					serieDebitos.addValor(o[1]);
					continue a;
				}
			}
			 serieDebitos.addValor("0");			
		}
		chartResultadoLancamentos.addSerie(serieDebitos);		
		List<Object[]> saldos = getSaldos(fim);
		Serie serieSaldos = new Serie();
		serieSaldos.setTitle("Saldos");
		serieSaldos.setColor("green");
		a: for( Date d: datas){
			if(d.after(inicio)){
				serieSaldos.addLabel(String.valueOf(formatLabel.format(d)));
				 for(Object[] o: saldos){							
					if(formatLabel.format(d).equals(o[0])){					
						serieSaldos.addValor(o[1]);
						continue a;
					}
				}
			}
			 serieSaldos.addValor("0");			
		}
		chartResultadoLancamentos.addSerie(serieSaldos);

		
		return chartResultadoLancamentos;
	}

	private List<Object[]> getSaldos(Date fim) {
		EntityManager session = entityManagerFactory.createEntityManager();
		StringBuilder query = new StringBuilder();
		query.append(" select data, @saldo \\:= @saldo + entrada_saida from (SELECT @saldo \\:= 0) as vars, ").
				append(" (select date_format(data, '%m-%Y') data, sum(if(L.TIPO_LANCAMENTO = 1, valor, valor*-1)) entrada_saida ").
				append(" from LANCAMENTO L where  data between str_to_date('1900-01-01', '%Y-%m-%d') and :dtFim group by date_format(data, '%y-%m')) es ");
		return session.createNativeQuery(query.toString()).setParameter("dtFim", fim).getResultList();
	}


}
