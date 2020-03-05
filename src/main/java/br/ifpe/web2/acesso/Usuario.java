                                              package br.ifpe.web2.acesso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.ifpe.web2.util.ObjetoGeral;

@Entity
public class Usuario extends ObjetoGeral{

	@Column(length = 70, nullable = false)
	private String nome;
	@Column(length = 40, nullable = false, unique = true)
	private String login;
	@Column(length = 40, nullable = false)
	private String senha;
	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	private Perfil perfil;
	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	private SituacaoUsuario situacaoUsuario;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public SituacaoUsuario getSituacaoUsuario() {
		return situacaoUsuario;
	}
	public void setSituacaoUsuario(SituacaoUsuario situacaoUsuario) {
		this.situacaoUsuario = situacaoUsuario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	
	
}
