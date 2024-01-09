package co.com.cetus.learning.springjpa.service;

import java.util.List;

import co.com.cetus.learning.springjpa.domain.Persona;

/**
 * PersonaService
 */

public interface PersonaService {

    public List<Persona> listarPersonas();

    public void guardar(Persona persona);

    public void eliminar(Persona persona);

    public Persona encontrarPersona(Persona persona);

}