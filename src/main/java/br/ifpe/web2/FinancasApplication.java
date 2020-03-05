package br.ifpe.web2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.ifpe.web2.acesso.UsuarioService;
import br.ifpe.web2.service.ContaService;

@SpringBootApplication
public class FinancasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FinancasApplication.class, args);
	}

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ContaService contaService;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Inicializando dados.. ");
		System.out.println("Qtd usuários: " + this.usuarioService.obterQuantidade());
		System.out.println("Criando usuário admin");
		this.usuarioService.criarUsuarioAdmin();
		System.out.println("Qtd usuários: " + this.usuarioService.obterQuantidade());
		
	}

}
