package com.capgemini.market.web.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private static final String KEY = "capgemini"; //esta debe ser compleja y cifrada
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())// se setea el usuario
                .setIssuedAt(new Date()) // fecha de inicio
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))//le damos 10 horas de vida al token
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        //aqui se evalua lo que esta llegando vs los metodos de abajo
        return userDetails.getUsername().equals(extractUserName(token)) && !isTokenExpired(token);
    }

    public String extractUserName(String token){
        return getClaims(token).getSubject();
        //obtiiene el usuario y lo guarda en subject
    }
    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        //lado  izqueirod valida y el lado derecho trae lo que tiene el cuerpo
    }

}
