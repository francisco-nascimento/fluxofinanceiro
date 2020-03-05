package br.ifpe.web2.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FiltroEntrada {
	
	private Conta conta;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataMovimentacaoInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataMovimentacaoFim;
	
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Date getDataMovimentacaoInicio() {
		return dataMovimentacaoInicio;
	}
	public void setDataMovimentacaoInicio(Date dataMovimentacaoInicio) {
		this.dataMovimentacaoInicio = dataMovimentacaoInicio;
	}
	public Date getDataMovimentacaoFim() {
		return dataMovimentacaoFim;
	}
	public void setDataMovimentacaoFim(Date dataMovimentacaoFim) {
		this.dataMovimentacaoFim = dataMovimentacaoFim;
	}

}
