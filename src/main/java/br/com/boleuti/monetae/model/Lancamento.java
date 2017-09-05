package br.com.boleuti.monetae.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Lancamento {
	
	  @Id 
      @GeneratedValue(strategy=GenerationType.AUTO) 
      private Long id; 
      
      @Column(name="DESCRICAO", nullable=false)
       private String descricao;
      
      @Column(name="DATA", nullable=true)
      private Date data;
      
      @Column(name="VALOR", nullable=false)
      private Double valor;
      
      @ManyToOne(cascade = CascadeType.ALL)
      @JoinColumn(name="ID_CENTRO_CUSTO")
      private CentroCusto centroCusto;
      
      @ManyToOne(cascade = CascadeType.ALL)
      @JoinColumn(name="ID_FLUXO")
      private Fluxo fluxo;
 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}

	public Fluxo getFluxo() {
		return fluxo;
	}

	public void setFluxo(Fluxo fluxo) {
		this.fluxo = fluxo;
	}

	

}
