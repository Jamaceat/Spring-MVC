package co.com.cetus.learning.springjpa.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class EncriptarPassword {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {

        log.info("Bcryp llegue 023");
        return new BCryptPasswordEncoder(4);

    }
}
