package br.com.boleuti.monetae.service;


import java.util.List;

import br.com.boleuti.monetae.model.User;

public interface UserService {
	
	User getUsuarioLogado();
	
	User findById(Long id);

	User findByNome(String name);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<User> findAllUsers();

	boolean isUserExist(User user);
}