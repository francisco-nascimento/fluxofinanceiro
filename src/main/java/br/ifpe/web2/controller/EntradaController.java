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
import br.ifpe.web2.model.FiltroEntrada;
import br.ifpe.web2.model.Movimentacao;
import br.ifpe.web2.service.ContaService;
import br.ifpe.web2.service.MovimentacaoService;

@Controller
public class EntradaController {

	@Autowired
	private MovimentacaoService movimentacaoService;
	@Autowired
	private ContaService contaService;

	@GetMapping("/formMovimentacao")
	public String exibirForm(Movimentacao movimentacao, Model model, HttpSession session) {
		Conta contaAtiva = (Conta) session.getAttribute("contaAtiva");
		model.addAttribute("listaEntradas", this.movimentacaoService.listarTodasEntradas(contaAtiva));
		movimentacao.setDataMovimentacao(new Date());
		model.addAttribute("movimentacao", movimentacao);
		return "entrada/entrada-form";
	}

	@PostMapping("/inserirEntrada")
	public String inserirEntrada(@Valid Movimentacao movimentacao, BindingResult br, Model model, RedirectAttributes ra,
			HttpSession session) {
		if (br.hasErrors()) {
			return exibirForm(movimentacao, model, session);
		}
		movimentacao.setCriadoPor((Usuario) session.getAttribute("usuarioLogado"));
		this.movimentacaoService.registrarMovimentacao(movimentacao);
		ra.addFlashAttribute("mensagem", "Movimentação cadastrada com Sucesso");
		return "redirect:/formMovimentacao";
	}

	@GetMapping("/formPesquisarEntradas")
	public String exibirPesquisarEntradas(Model model, HttpSession session) {
		model.addAttribute("listaContas",
				this.contaService.listarTodasContas((Usuario) session.getAttribute("usuarioLogado")));
		model.addAttribute("filtro", new FiltroEntrada());
		return "entrada/entrada-filter";
	}

	@PostMapping("/pesquisarEntradas")
	public String pesquisarEntradas(FiltroEntrada filtro, Model model, HttpSession session) {

		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

		model.addAttribute("listaContas", this.contaService.listarTodasContas(usuarioLogado));
		model.addAttribute("listaEntradas", this.movimentacaoService.pesquisarEntrada(filtro, usuarioLogado));
		model.addAttribute("filtro", filtro);
		return "entrada/entrada-filter";
	}

	@GetMapping("/editarEntrada")
	public String editarEntrada(Integer codigo, Model model, HttpSession session) {
		Movimentacao entrada = this.movimentacaoService.obterEntradaPorCodigo(codigo);
		return this.exibirForm(entrada, model, session);
	}

	@GetMapping("/removerEntrada")
	public String removerEntrada(Integer codigo) {
		this.movimentacaoService.removerEntrada(codigo);
		return "redirect:/formEntrada";
	}

}
