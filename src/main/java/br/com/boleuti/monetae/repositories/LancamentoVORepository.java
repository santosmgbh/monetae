package br.com.boleuti.monetae.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.boleuti.monetae.model.CentroCusto;
import br.com.boleuti.monetae.model.Lancamento;
import br.com.boleuti.monetae.model.vo.LancamentoVO;


public interface LancamentoVORepository  extends JpaRepository<LancamentoVO, Long>  {
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
    List<LancamentoVO> findAll();
       
    
       
}
