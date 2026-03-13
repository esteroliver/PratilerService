package br.com.dev.esteroliver.pratiler.service.c_infra.security;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {

    private static final String SECRET_KEY = "3c86d3471ee61f25fa3e20ca1acadeff";

    private static final String ISSUER = "pratiler-api";

    public String gerarToken(UserDetailsImpl userDetails){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(userDetails.getUsername())
                    .withIssuedAt(dataCriacao())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        }
        catch (JWTCreationException exception){
            throw new JWTCreationException("Erro ao gerar o token.", exception);
        }
    }

    public String verificarTokenUsuario(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

    private Instant dataCriacao(){
        return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
    }

    private Instant dataExpiracao(){
        return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(5).toInstant();
    }
}
