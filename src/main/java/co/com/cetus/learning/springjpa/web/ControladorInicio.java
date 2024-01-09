package co.com.cetus.learning.springjpa.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

import co.com.cetus.learning.springjpa.domain.Persona;
import co.com.cetus.learning.springjpa.service.PersonaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * ''
 * ControladorInicio
 */
@Controller
@Slf4j
public class ControladorInicio {

    private final PersonaService personaService;

    ControladorInicio(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {

        List<Persona> personas = personaService.listarPersonas();
        List<String> ls = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String saldoTotal = Persona.formater.format(personas.stream().mapToDouble(Persona::getSaldo).sum());
        int contadorPersonas = personas.size();

        model.addAttribute("contadorPersonas", contadorPersonas);
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("authorities", ls);
        model.addAttribute("usuario", user);
        model.addAttribute("personas", personas);

        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) {

        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors error) {
        log.info("llego a guardar");
        if (error.hasErrors()) {
            log.debug("ERROOOOOOOOOOOOOOOOOOOOOOOR");
            log.info("Error");
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{id_persona}")
    public String editar(Persona persona, Model model) {
        Persona persona2 = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona2);
        return "modificar";
    }

    @GetMapping("/eliminar")
    public String eliminar(Persona persona) {

        personaService.eliminar(persona);
        return "redirect:/";
    }

}