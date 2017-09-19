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

@Entity
public class Lancamento {
	
	  @Id 
      @GeneratedValue(strategy=GenerationType.AUTO) 
      private Long id; 
      
      @Column(name="DESCRICAO", nullable=false)
      private String descricao;
      
      @Column(name = "TIPO_LANCAMENTO")
      @Enumerated(EnumType.ORDINAL)
      private TipoLancamento tipoLancamento;
      
      @Column(name="DATA", nullable=true)
      private Date data;
      
      @Column(name="VALOR", nullable=false)
      private Double valor;
      
      @Column(name="PARCELAS", nullable=false)
      private Integer parcelas;
      
      @ManyToOne(cascade = CascadeType.ALL)
      @JoinColumn(name="ID_CENTRO_CUSTO")
      private CentroCusto centroCusto;
      
      @ManyToOne(cascade = CascadeType.ALL)
      @JoinColumn(name="ID_FLUXO")
      private Fluxo fluxo;
      
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

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	
	

	

}
