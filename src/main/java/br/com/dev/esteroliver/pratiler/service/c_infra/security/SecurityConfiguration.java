package br.com.dev.esteroliver.pratiler.service.c_infra.security;

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
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    UserAuthFilter userAuthFilter;

    public static final String[] ENDPOINTS_PUBLICOS = {
        "/auth/login",
        "/usuarios/cadastro/leitor"
    };

    public static final String[] ENDPOINTS_USUARIOS_LEITOR_ADM = {
        "/usuarios/meus_dados"
    };

    public static final String[] ENDPOINTS_USUARIO_LEITOR = {
        "/usuarios/teste_leitor"
    };

    public static final String[] ENDPOINTS_USUARIO_ADM = {
        "/usuarios/teste_adm"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf((csrf) -> csrf.disable())
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        (auth) -> auth
                            .requestMatchers(ENDPOINTS_PUBLICOS).permitAll()
                            .requestMatchers(ENDPOINTS_USUARIOS_LEITOR_ADM).authenticated()
                            .requestMatchers(ENDPOINTS_USUARIO_LEITOR).hasRole("LEITOR")
                            .requestMatchers(ENDPOINTS_USUARIO_ADM).hasRole("ADMINISTRADOR")
                            .anyRequest().denyAll()
                )
                .addFilterBefore(userAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
