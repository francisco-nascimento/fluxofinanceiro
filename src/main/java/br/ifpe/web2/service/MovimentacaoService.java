package br.ifpe.web2.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.ifpe.web2.acesso.Usuario;
import br.ifpe.web2.model.Conta;
import br.ifpe.web2.model.FiltroEntrada;
import br.ifpe.web2.model.Movimentacao;
import br.ifpe.web2.model.TipoMovimentacao;
import br.ifpe.web2.persistence.ContaDAO;
import br.ifpe.web2.persistence.MovimentacaoDAO;
import br.ifpe.web2.util.UtilFNJ;

@Service
public class MovimentacaoService {

	@Autowired
	private MovimentacaoDAO movimentacaoDAO;
	@Autowired
	private ContaDAO contaDAO;

	public void registrarMovimentacao(Movimentacao movimentacao) {
		if (movimentacao.getDataMovimentacao() == null) {
			movimentacao.setDataMovimentacao(new Date());
		}
		Conta conta = movimentacao.getConta();
		if (movimentacao.getTipoMovimentacao().equals(TipoMovimentacao.ENTRADA)) {
			
			conta.setSaldo(conta.getSaldo() + movimentacao.getValor());			
		} else {
			conta.setSaldo(conta.getSaldo() - movimentacao.getValor());
		}
		this.contaDAO.saveAndFlush(conta);
		this.movimentacaoDAO.save(movimentacao);
	}
	
	public List<Movimentacao> listarTodasEntradas(Usuario usuario){
		return this.movimentacaoDAO.findByCriadoPor(usuario, Sort.by("conta.nomeConta"));
	}

	public List<Movimentacao> listarTodasEntradas(Conta conta){
		return this.movimentacaoDAO.findByContaOrderByDataMovimentacao(conta);
	}

	public List<Movimentacao> pesquisarEntrada(FiltroEntrada filtro, Usuario usuario) {
		if (filtro.getDataMovimentacaoInicio() == null) {
			filtro.setDataMovimentacaoInicio(UtilFNJ.converterParaDate(LocalDate.of(2000, 01, 01)));
		}
		if (filtro.getDataMovimentacaoFim() == null) {
			filtro.setDataMovimentacaoFim(new Date());
		}
		return this.movimentacaoDAO.findByContaAndDataMovimentacaoBetweenAndCriadoPor(filtro.getConta(), 
			filtro.getDataMovimentacaoInicio(), filtro.getDataMovimentacaoFim(), usuario);
	}
	
	public Movimentacao obterEntradaPorCodigo(Integer codigo) {
		return this.movimentacaoDAO.getOne(codigo);
	}
	
	public void removerEntrada(Integer codigo) {
		this.movimentacaoDAO.deleteById(codigo);
	}

	
}
