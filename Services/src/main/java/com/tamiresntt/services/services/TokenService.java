package com.tamiresntt.services.services;

import com.tamiresntt.services.domain.UserRegister;
import com.tamiresntt.services.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class TokenService {

    @Autowired
    private UserRepository repository;

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret = "827ccb0eea8a706c4c34a16891f84e7b";

    public String generateToken(Authentication authentication) {

        UserRegister user = (UserRegister) authentication.getPrincipal();

        Date now = new Date();
        Date exp = new Date(System.currentTimeMillis() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("IRS")
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(exp).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("erro do token valid " + e);
            return false;
        }
    }

    public String getTokenId(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        return String.valueOf(body.getSubject());
    }
}
