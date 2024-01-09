package co.com.cetus.learning.springjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.cetus.learning.springjpa.domain.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

}
