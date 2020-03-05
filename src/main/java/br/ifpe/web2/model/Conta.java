package br.ifpe.web2.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import br.ifpe.web2.util.ObjetoGeral;

@Entity
public class Conta extends ObjetoGeral {

	@NotBlank(message = "Preencha o nome da conta")
	private String nomeConta;
	private double saldo;

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
}
