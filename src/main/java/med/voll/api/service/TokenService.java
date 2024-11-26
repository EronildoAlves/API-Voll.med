package med.voll.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.domain.entities.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

//Classe de validação e geração do token
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    //converter essa String do issuer em uma constante da classe:
    private static final String ISSUER = "API Voll.med";

    public String gerarToken(Usuario usuario) {

        try {
            var algoritmo  = Algorithm.HMAC256(secret);
            return JWT.create()
                    //issuer -> o "nome" da assinatura do token
                    .withIssuer("ISSUER")
                    //subject -> usuário que é responsável pelo token
                    .withSubject(usuario.getLogin())
                    //expiresAt -> método para expiração do token
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo  = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("ISSUER")
                    .build()
                    //Verifica se o token está válido de acordo com o algoritmo e o issuer
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
           throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusYears(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
