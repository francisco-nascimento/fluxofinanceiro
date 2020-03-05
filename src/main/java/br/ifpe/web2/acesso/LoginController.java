package br.ifpe.web2.acesso;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.web2.service.ContaService;
import br.ifpe.web2.util.UtilFNJ;

@Controller
public class LoginController {

	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private ContaService contaService;
	
	@GetMapping("/")
	public String exibirIndex(Usuario usuario) {
		return "index";
	}
	
	@PostMapping("/efetuarLogin")
	public String efetuarLogin(Usuario usuario,
			RedirectAttributes ra,
			HttpSession session) {
		
		usuario = this.usuarioDAO.findByLoginAndSenha(
				usuario.getLogin(), UtilFNJ.md5(usuario.getSenha()));
		
		if (usuario != null) {
			// Guardar sessao o objeto usuario
			session.setAttribute("usuarioLogado", usuario);
			return "redirect:/home";
		} else {
			// Enviar uma mensagem 
			ra.addFlashAttribute("mensagem", "Login/senha inválidos");
			return "redirect:/";
		}
	}
	
	@GetMapping("/home")
	public String exibirHome(Model model, HttpSession session) {
		model.addAttribute("listaContas", this.contaService.listarTodasContas(
				(Usuario) session.getAttribute("usuarioLogado")));
		return "home";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/acessoNegado")
	public String acessoNegado(RedirectAttributes ra, HttpSession session) {
		ra.addFlashAttribute("mensagem", "Acesso não-autorizado. Efetue login novamente");		
		session.invalidate();
		return "index";
	}
	
}
