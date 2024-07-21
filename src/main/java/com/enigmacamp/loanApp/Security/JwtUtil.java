package com.enigmacamp.loanApp.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enigmacamp.loanApp.model.entity.AppUser;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtUtil {
    private String jwtSecret = "mirzarajaiblis";
    private String appName = "Loan App";
    private Long jwtExpired = 3600L;

    private Algorithm algorithm;

    @PostConstruct
    private void initAlgorithm() {
        this.algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(AppUser appUser) {
        try {
            return JWT.create()
                    .withIssuer(appName)
                    .withSubject(appUser.getId())
                    .withExpiresAt(Instant.now().plusSeconds(jwtExpired))
                    .withIssuedAt(Instant.now())
                    .withClaim("role", appUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"error while creating jwt token");
        }
    }
    public boolean verifyJwtToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getIssuer().equals(appName);
        } catch (JWTCreationException e) {
            log.error("Invalid JWT Signature/Claims : {}", e.getMessage());
            return false;
        }
    }
    
    public Map<String, String> getUserInfoByToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("userId", decodedJWT.getSubject());
            userInfo.put("role", decodedJWT.getClaim("role").asString());
            return userInfo;
        } catch (JWTCreationException e) {
            log.error("Invalid JWT Signature/Claims : {}", e.getMessage());
            return null;
        }
    }
    
}
