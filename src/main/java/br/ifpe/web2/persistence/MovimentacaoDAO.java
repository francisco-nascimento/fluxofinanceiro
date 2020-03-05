package br.ifpe.web2.persistence;

import java.util.Date;
import java.util.List;

import br.ifpe.web2.acesso.Usuario;
import br.ifpe.web2.model.Conta;
import br.ifpe.web2.model.Movimentacao;

public interface MovimentacaoDAO extends DAOGeral<Movimentacao, Integer> {

	public List<Movimentacao> findByContaAndDataMovimentacaoBetweenAndCriadoPor(Conta conta, Date data1, Date data2,
			Usuario usuario);
	
	public List<Movimentacao> findByContaOrderByDataMovimentacao(Conta conta);
}
