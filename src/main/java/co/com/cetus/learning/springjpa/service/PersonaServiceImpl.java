package co.com.cetus.learning.springjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.cetus.learning.springjpa.dao.IPersonaDAO;
import co.com.cetus.learning.springjpa.domain.Persona;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private IPersonaDAO personaDAO;

    @Override
    @Transactional(readOnly = true) // Transaccional para hacer rollback pero solo se leera / Es de spring este
                                    // framework
    public List<Persona> listarPersonas() {

        return (List<Persona>) personaDAO.findAll();
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        personaDAO.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        personaDAO.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return personaDAO.findById(persona.getId_persona()).orElse(null);
    }

}
