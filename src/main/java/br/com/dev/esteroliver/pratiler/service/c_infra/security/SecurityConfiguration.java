package br.com.dev.esteroliver.pratiler.service.c_infra.security;

import java.util.List;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    UserAuthFilter userAuthFilter;

    public static final String[] ENDPOINTS_PUBLICOS = {
        "/auth/**",
        "/usuarios/cadastro/leitor",
        "/swagger-ui/**"
    };

    public static final String[] ENDPOINTS_USUARIOS_LEITOR_ADM = {
    };

    public static final String[] ENDPOINTS_USUARIO_LEITOR = {
    };

    public static final String[] ENDPOINTS_USUARIO_ADM = {
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf((csrf) -> csrf.disable())
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        (auth) -> auth
                            .requestMatchers(ENDPOINTS_USUARIOS_LEITOR_ADM).authenticated()
                            .requestMatchers(ENDPOINTS_USUARIO_LEITOR).hasRole("LEITOR")
                            .requestMatchers(ENDPOINTS_USUARIO_ADM).hasRole("ADMINISTRADOR")
                            .requestMatchers(ENDPOINTS_PUBLICOS).permitAll()
                            .anyRequest().permitAll()
                )
                .addFilterBefore(userAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .cors((cors) -> cors.configurationSource(corsConfigurationSource()))
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration conf = new CorsConfiguration();

        conf.setAllowedOrigins(List.of("http://localhost:4200"));
        conf.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        conf.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        conf.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", conf);

        return source;  
    }
}
