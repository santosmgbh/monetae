package br.com.boleuti.monetae.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.boleuti.monetae.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByNome(String nome);

}
