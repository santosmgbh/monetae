package br.com.boleuti.monetae.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.boleuti.monetae.model.CentroCusto;
import br.com.boleuti.monetae.model.TipoLancamento;


public interface TipoLancamentoRepository  extends JpaRepository<TipoLancamento, Long>  {
    /**
     * Encontra o centro de custo por nome
     * 
     * @param autor
     * @return lista de centro de custos
     */
	TipoLancamento findByNome(String nome);
    
    /**
     * Encontra todos os centros de custos.
     * 
     * @param autor
     * @return lista de centro de custos
     */
    List<TipoLancamento> findAll();
       
    
       
}
