package br.ifpe.web2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.ifpe.web2.acesso.Usuario;
import br.ifpe.web2.model.Conta;
import br.ifpe.web2.persistence.ContaDAO;

@Service
public class ContaService {

	@Autowired
	private ContaDAO contaDAO;
	
	public List<Conta> listarTodasContas(Usuario usuario){
//		return this.contaDAO.findAll(Sort.by("nomeConta"));
		return this.contaDAO.findByCriadoPor(usuario, Sort.by("nomeConta"));
	}

	public void inserirConta(Conta conta) {
		this.contaDAO.save(conta);
	}
	
	public Conta obterContaPorCodigo(Integer codigo) {
		return this.contaDAO.getOne(codigo);
	}
	
	public void removerConta(Integer codigo) {
		this.contaDAO.deleteById(codigo);
	}
}
