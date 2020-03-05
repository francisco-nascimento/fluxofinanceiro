package br.ifpe.web2.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.ifpe.web2.util.ObjetoGeral;

@Entity
public class Movimentacao extends ObjetoGeral {

	@ManyToOne
	@NotNull(message = "Selecione a conta")
	private Conta conta;	
	private String descricao;
	@DecimalMin(value = "0.01", message = "Valor mínimo: R$ 0,01")
	private double valor;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataMovimentacao;
	@Enumerated(EnumType.ORDINAL)
	private TipoMovimentacao tipoMovimentacao;
	
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}
	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}
	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	
	
}
