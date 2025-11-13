package com.example.springsecurity.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests)

                // -> requests.anyRequest().authenticated())  // autenticare ogni richiesta
                // -> requests.anyRequest().permitAll() // accesso a tutte le richieste
                // -> requests.anyRequest().denyAll()); // accesso negato
                // Accesso selettivo
                -> requests
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                        .requestMatchers("/notice", "/contact", "/error").permitAll());

        // http.formLogin(withDefaults());
        // http.formLogin().disable(); // Vecchio metodo deprecato per disattivare il form login
        http.formLogin(flc -> flc.disable());

        http.httpBasic(withDefaults());

        // http.httpBasic(htb -> htb.disable()); // Disattivazione
        return http.build();
    }
}
