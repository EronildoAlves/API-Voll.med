package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //personalizar as config de segurança
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean //devolve um objeto para o spring
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //stateless é um tipo de estado do servidor, ele não armazena nenhuma memória no programa
       return http.csrf(csrf -> csrf.disable())
               .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authorizeHttpRequests(req -> {req.requestMatchers("/login").permitAll()
                       .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
                   req.anyRequest().authenticated();
               })
               .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
    }

    @Bean //serve para ensinar o spring a injetar um objeto (authenticationManager) que vamos utilizar internamente
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean //config para hash de senha
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
