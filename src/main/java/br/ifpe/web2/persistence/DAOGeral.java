package br.ifpe.web2.persistence;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.ifpe.web2.acesso.Usuario;

@NoRepositoryBean
public interface DAOGeral<T,ID extends Serializable> extends JpaRepository<T, ID> {

	public List<T> findByCriadoPor(Usuario usuario, Sort sort);

}
