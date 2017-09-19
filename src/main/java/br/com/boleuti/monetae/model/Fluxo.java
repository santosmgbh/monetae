package br.com.boleuti.monetae.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * O Fluxo é uma forma de centralizar as movimentações. 
 * De forma prática seria por exemplo uma Conta, Cartão de crédito, Conta poupança, cheque, etc.
 * @author almir.santos
 *
 */
@Entity
public class Fluxo {
	
	  @Id 
      @GeneratedValue(strategy=GenerationType.AUTO) 
      private Long id; 
      
      @Column(name="NOME", nullable=false)
      private String nome; 
      
      @ManyToOne(cascade = CascadeType.ALL)
      @JoinColumn(name="ID_USER", nullable=false)
      private User user;
      
      @Column(name="DATA_CADASTRO")
      @Temporal(TemporalType.TIMESTAMP)
      private Date dataCadastro = new Date();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
 
    
      
      
      
}
