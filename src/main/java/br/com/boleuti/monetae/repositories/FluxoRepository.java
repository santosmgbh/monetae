package br.com.boleuti.monetae.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.boleuti.monetae.model.Fluxo;


public interface FluxoRepository  extends JpaRepository<Fluxo, Long>  {

    /**
     * Encontra todos os centros de custos.
     * 
     * @param autor
     * @return lista de centro de custos
     */
    List<Fluxo> findAll();
       
}
