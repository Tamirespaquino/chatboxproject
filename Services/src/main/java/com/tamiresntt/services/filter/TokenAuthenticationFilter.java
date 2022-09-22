package com.tamiresntt.services.filter;

import com.tamiresntt.services.domain.UserRegister;
import com.tamiresntt.services.repository.UserRepository;
import com.tamiresntt.services.services.TokenService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository repository;

    @Value("${jwt.secret}")
    private String secret;

    public TokenAuthenticationFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            token = null;
        } else {
            token = token.substring(7, token.length());
        }

        if(token.isValid)
        filterChain.doFilter(request, response);
    }

    public boolean isValid(String token) {

        System.out.println(token);

        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    /*private void autenticateUser(String token) {

        String idUser = tokenService.getTokenId(token);

        UserRegister user = repository.findById(idUser).get();
        System.out.println(user.getUsername());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }*/
    private void authenticate(String tokenFromHeader) {
        String id = tokenService.getTokenId(tokenFromHeader);

        Optional<UserRegister> optionalUser = repository.findById(id);

        if(optionalUser.isPresent()) {

            UserRegister user = optionalUser.get();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
