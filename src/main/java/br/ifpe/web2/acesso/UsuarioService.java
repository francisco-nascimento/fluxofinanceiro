package br.ifpe.web2.acesso;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifpe.web2.util.ServiceException;
import br.ifpe.web2.util.UtilFNJ;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	public void criarUsuarioAdmin() {
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setLogin("gilson");
		usuario.setSenha("Gil@45");
		usuario.setPerfil(Perfil.ADMINISTRADOR);
		usuario.setNome("Gilson");
		usuario.setSituacaoUsuario(SituacaoUsuario.ATIVO);

		try {
			inserirUsuario(usuario);
		} catch (ServiceException e) {
			System.out.println("Usuário " +
			  usuario.getLogin() + " já existe");
		}
		
		usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setLogin("jlins");
		usuario.setSenha("34%lins");
		usuario.setPerfil(Perfil.ADMINISTRADOR);
		usuario.setNome("Lins");
		usuario.setSituacaoUsuario(SituacaoUsuario.ATIVO);

		try {
			inserirUsuario(usuario);
		} catch (ServiceException e) {
			System.out.println("Usuário " +
					  usuario.getLogin() + " já existe");
		}

	}

	public void inserirUsuario(Usuario usuario) throws ServiceException {
		usuario.setSenha(UtilFNJ.md5(usuario.getSenha()));
		usuario.setDataCriacao(new Date());
		if (this.usuarioDAO.existsByLogin(usuario.getLogin()) == false) {
			this.usuarioDAO.save(usuario);
		} else {
			throw new ServiceException("Já existe usuário com este login");
		}
	}

	public long obterQuantidade() {
		return this.usuarioDAO.count();
	}
}
