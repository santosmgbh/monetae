package br.com.boleuti.monetae.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.boleuti.monetae.model.CentroCusto;
import br.com.boleuti.monetae.model.Lancamento;


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
       
    
       
}
