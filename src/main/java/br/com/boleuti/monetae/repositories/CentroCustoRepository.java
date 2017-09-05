package br.com.boleuti.monetae.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.boleuti.monetae.model.CentroCusto;


public interface CentroCustoRepository  extends JpaRepository<CentroCusto, Long>  {
    /**
     * Encontra o centro de custo por nome
     * 
     * @param autor
     * @return lista de centro de custos
     */
    CentroCusto findByNome(String nome);
    
    /**
     * Encontra todos os centros de custos.
     * 
     * @param autor
     * @return lista de centro de custos
     */
    List<CentroCusto> findAll();
    
       
}
