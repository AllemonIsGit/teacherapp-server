package com.example.TeacherAppServer.utill.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JWTTokenUtil {
    private final Algorithm algorithm = Algorithm.HMAC512(System.getenv("ALGORITHM_KEY"));
    private final JWTVerifier verifier = JWT.require(algorithm).build();

    public String createToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(Date.from(Instant.now()))
                .withIssuer("TeacherApp")
                .withExpiresAt(Date.from(Instant.now().plus(1440, ChronoUnit.MINUTES)))
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token) {
        return verifier.verify(token);
    }
}
