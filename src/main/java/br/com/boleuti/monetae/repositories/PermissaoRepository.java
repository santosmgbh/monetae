package br.com.boleuti.monetae.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.boleuti.monetae.model.Permissao;

@Repository("permissaoRepository")
public interface PermissaoRepository extends JpaRepository<Permissao, Integer>{
	Permissao findByNome(String role);

}