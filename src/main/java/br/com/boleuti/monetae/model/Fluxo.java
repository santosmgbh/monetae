package br.com.boleuti.monetae.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fluxo {
	  @Id 
      @GeneratedValue(strategy=GenerationType.AUTO) 
      private Long id; 
      
      @Column(name="NOME", nullable=false)
      private String nome; 
      
      @Column(name = "TIPO")
      @Enumerated(EnumType.ORDINAL)
      private TipoFluxo tipoFluxo;

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

	public TipoFluxo getTipoFluxo() {
		return tipoFluxo;
	}

	public void setTipoFluxo(TipoFluxo tipoFluxo) {
		this.tipoFluxo = tipoFluxo;
	}
 
    
      
      
      
}
