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

    @Query(value = "select data, @saldo := @saldo + entrada_saida from (SELECT @saldo := 0) as vars, (select date_format(data, '%m-%Y') data, sum(if(L.TIPO_LANCAMENTO = 1, valor, valor*-1)) entrada_saida from LANCAMENTO L where  data between str_to_date('1900-01-01', '%Y-%m-%d') and :dtFim group by date_format(data, '%y-%m')) es ", nativeQuery = true)
    List<Object[]> getSaldos(@Param("dtFim") Date dtFim);
       
}
