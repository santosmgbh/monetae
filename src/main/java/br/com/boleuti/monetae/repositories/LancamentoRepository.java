package br.com.boleuti.monetae.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.boleuti.monetae.model.CentroCusto;
import br.com.boleuti.monetae.model.Lancamento;
import br.com.boleuti.monetae.model.TipoLancamento;
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
    
    @Query(value = "select date_format(data, '%m-%Y'), sum(valor) valor from LANCAMENTO where TIPO_LANCAMENTO = :tipo AND data between :dtIni and :dtFim group by date_format(data, '%y-%m') ", nativeQuery = true)
    List<Object[]> getSomaValores(@Param("dtIni") Date dtIni, @Param("dtFim") Date dtFim, @Param("tipo") TipoLancamento tipo);

    @Query(value = "select date_format(data, '%m-%Y'), sum(CASE WHEN TIPO_LANCAMENTO = 1 THEN valor ELSE (VALOR * -1) END) creditos from LANCAMENTO where data between :dtIni and :dtFim group by date_format(data, '%y-%m') ", nativeQuery = true)
    List<Object[]> getSaldos(@Param("dtIni") Date dtIni, @Param("dtFim") Date dtFim);
       
}
