package br.com.boleuti.monetae.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.boleuti.monetae.model.CentroCusto;
import br.com.boleuti.monetae.model.Lancamento;
import br.com.boleuti.monetae.model.chart.Serie;


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
    
    @Query(value = "SELECT DATE_FORMAT(d.selected_date, '%y-%m') data FROM DatePeriods d WHERE d.selected_date BETWEEN :dtIni AND :dtFim ", nativeQuery = true)     
    List<String> getLabelSeries(@Param("dtIni") String dtIni, @Param("dtFim") String dtFim);
    
    @Query(value = "SELECT DATE_FORMAT(l.data, '%y-%m') label, SUM(l.VALOR) valor FROM lancamento l where DATE_FORMAT(l.data, '%y-%m') in (:datas) AND l.tipo_lancamento = 1 GROUP BY DATE_FORMAT(l.data, '%y-%m')", nativeQuery = true)
	List<Object[]> getSerieDebitos(@Param("datas") List<String> datas);
    
    @Query(value = "SELECT DATE_FORMAT(l.data, '%y-%m') label, SUM(l.VALOR) valor FROM lancamento l where DATE_FORMAT(l.data, '%y-%m') in (:datas) AND l.tipo_lancamento = 2 GROUP BY DATE_FORMAT(l.data, '%y-%m') ", nativeQuery = true)
    List<Object[]> getSerieCreditos(@Param("datas") List<String> datas);

       
}
