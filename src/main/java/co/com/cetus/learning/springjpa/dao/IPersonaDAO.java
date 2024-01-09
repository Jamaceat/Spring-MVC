package co.com.cetus.learning.springjpa.dao;

import org.springframework.data.repository.CrudRepository;

import co.com.cetus.learning.springjpa.domain.Persona;

/**
 * IPersonaDAO
 */
public interface IPersonaDAO extends CrudRepository<Persona, Long> {

}