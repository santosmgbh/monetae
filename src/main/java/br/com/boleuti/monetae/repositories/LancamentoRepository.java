package br.com.boleuti.monetae.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.boleuti.monetae.model.CentroCusto;
import br.com.boleuti.monetae.model.Lancamento;
import br.com.boleuti.monetae.model.LineChart;


public interface LancamentoRepository  extends JpaRepository<Lancamento, Long>  {
    /**
     * Encontra o centro de custo por nome
     * 
     * @param autor
     * @return lista de centro de custos
     */
    Lancamento findByDescricao(String nome);
    
    /**
     * Encontra todos os centros de custos.
     * 
     * @param autor
     * @return lista de centro de custos
     */
    List<Lancamento> findAll();

    @Query(value = "SELECT datas.data AS labels, SUM(l.VALOR) AS valores FROM (SELECT DATE_FORMAT(d.selected_date, '%y-%m') data FROM DatePeriods d WHERE d.selected_date BETWEEN :dtIni AND :dtFim) datas LEFT JOIN test.lancamento l ON datas.data = DATE_FORMAT(l.data, '%y-%m') AND l.tipo_lancamento = 2 GROUP BY datas.data ", nativeQuery = true)
	LineChart getLineDebitos(@Param("dtIni") Date inicio, @Param("dtFim") Date fim);
    
    @Query(value = "SELECT datas.data AS labels, SUM(l.VALOR) AS valores FROM (SELECT DATE_FORMAT(d.selected_date, '%y-%m') data FROM DatePeriods d WHERE d.selected_date BETWEEN :dtIni AND :dtFim) datas LEFT JOIN test.lancamento l ON datas.data = DATE_FORMAT(l.data, '%y-%m') AND l.tipo_lancamento = 1 GROUP BY datas.data ", nativeQuery = true)
	LineChart getLineCreditos(@Param("dtIni") Date inicio, @Param("dtFim") Date fim);
       
    
       
}
