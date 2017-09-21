package br.com.boleuti.monetae.model.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="LANCAMENTO")
public class LancamentoVO {
	
	  @Id 
      @GeneratedValue(strategy=GenerationType.AUTO) 
      private Long id; 
      
      @Column(name="DESCRICAO", nullable=false)
      private String descricao;
      
      @Column(name = "TIPO_LANCAMENTO")      
      private String tipoLancamento;
      
      @Column(name="DATA", nullable=true)
      @Temporal(TemporalType.TIMESTAMP)
      private Date data;
      
      @Column(name="VALOR", nullable=false)
      private Double valor;
      
      @Column(name="PARCELAS", nullable=false)
      private Integer parcelas;
            
      @Column(name="ID_CENTRO_CUSTO")
      private String centroCusto;
            
      @Column(name="ID_FLUXO")
      private String fluxo;
            
      @Column(name="ID_USER")
      private String user;
      
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

	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
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

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

	public String getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
	}

	public String getFluxo() {
		return fluxo;
	}

	public void setFluxo(String fluxo) {
		this.fluxo = fluxo;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
 

	
	

	

}
