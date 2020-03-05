package br.ifpe.web2.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.web2.acesso.Usuario;
import br.ifpe.web2.model.Conta;
import br.ifpe.web2.model.Movimentacao;
import br.ifpe.web2.service.ContaService;

@Controller
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	@GetMapping("/formConta")
	public String exibirForm(Conta conta, Model model, HttpSession session) {
		
		model.addAttribute("listaContas", this.contaService.listarTodasContas(
				(Usuario) session.getAttribute("usuarioLogado")));
		model.addAttribute("conta", conta);
		return "conta/conta-form";
	}
	
	@GetMapping("/entrarConta")
	public String entrarConta(Integer codigo, RedirectAttributes ra,HttpSession session) {
		Conta conta = this.contaService.obterContaPorCodigo(codigo);
		if (verificarProprietarioConta(ra, session, conta)){
			session.setAttribute("contaAtiva", conta);
			return "redirect:/formMovimentacao";			
		} else {
			return "/erro";
		}
	}

	private boolean verificarProprietarioConta(RedirectAttributes ra, HttpSession session, Conta conta) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		if (!conta.getCriadoPor().equals(usuarioLogado)) {
			ra.addFlashAttribute("mensagem", "Conta não pertence ao usuário");
			return false;			
		} 
		return true;
	}
	
	
	@PostMapping("/inserirConta")
	public String inserirConta(@Valid Conta conta, BindingResult br, Model model, RedirectAttributes ra,
			HttpSession session) {
		if (br.hasErrors()) {
			return exibirForm(conta, model, session);
		}
//		try {
			conta.setDataCriacao(new Date());
			
			if (session.getAttribute("usuarioLogado") != null) {
				conta.setCriadoPor((Usuario) session.getAttribute("usuarioLogado"));	
			}
			
			this.contaService.inserirConta(conta);
			ra.addFlashAttribute("mensagem", "Conta cadastrada com Sucesso");
			return "redirect:/formConta";
//		} catch (ServiceException e) {
//			br.addError(new ObjectError("global", e.getMessage()));
//			return exibirForm(entrada, model); 
//		} 
		
	}
	
	@GetMapping("/editarConta")
	public String editarConta(Integer codigo, Model model, HttpSession session) {
		Conta conta = this.contaService.obterContaPorCodigo(codigo);
		return this.exibirForm(conta, model, session);
	}

	@GetMapping("/removerConta")
	public String removerConta(Integer codigo) {
		this.contaService.removerConta(codigo);
		return "redirect:/formConta";
	}

}
