package co.com.cetus.learning.springjpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

        private UserDetailsService userDetailsService;

        SecurityConfig(UserDetailsService userDetailsService) {
                this.userDetailsService = userDetailsService;
        }

        @Autowired
        public void configurerGlobal(AuthenticationManagerBuilder build, BCryptPasswordEncoder passwordEncoder)
                        throws Exception {
                log.info("Apunto de usar el build");
                build.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder);

        }

        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(
                                                requests -> requests.requestMatchers("/").authenticated())
                                .authorizeHttpRequests(
                                                requests -> requests
                                                                .requestMatchers("/eliminar", "/editar/**", "/agregar",
                                                                                "/guardar")
                                                                .hasRole("ADMIN"))
                                .formLogin(t -> t.permitAll().loginPage("/login"))
                                .authorizeHttpRequests(t -> t.requestMatchers("/login*").permitAll())
                                .authorizeHttpRequests(t -> t.requestMatchers("/errors/**").permitAll())
                                .exceptionHandling(t -> t.accessDeniedPage("/errors/403"))
                                .authorizeHttpRequests(t -> t.requestMatchers("/webjars/**").permitAll());
                // Todas las paginas que se vayan a acceder debe declararse el permiso con
                // anterioridad
                return http.build();
        }

}
