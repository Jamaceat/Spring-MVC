package co.com.cetus.learning.springjpa.domain;

import lombok.Data;

@Data
public class PersonaFormated {

    private Long id_persona;

    private String nombre;

    private String apellido;

    private String email;

    private String telefono;

    private String saldo;

    public PersonaFormated(Persona persona) {
        this.setId_persona(persona.getId_persona());
        this.setNombre(persona.getNombre());
        this.setApellido(persona.getApellido());
        this.setEmail(persona.getEmail());
        this.setTelefono(persona.getTelefono());
    }

    public PersonaFormated setSaldoWithPersona(String value) {

        setSaldo(value);
        return this;
    }

}
