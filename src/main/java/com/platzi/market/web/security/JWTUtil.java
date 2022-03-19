package com.platzi.market.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

//para poder inyectarlo y que no salga error
//Consider defining a bean of type 'com.platzi.market.web.security.JWTUtil' in your configuration.
@Component
public class JWTUtil {

    private static final String KEY = "pl4tz1";

    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())  //usuario
                .setIssuedAt(new Date())        //fecha creación del JWT
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //el JWT expira a las 10 horas
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(extractUserName(token)) && !isTokenExpired(token);
    }

    public String extractUserName(String token ){
        return getClaims(token).getSubject(); //el usuario
    }

    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(KEY). //valida la firma. Si firma errónea no continúa y devuelve Forbidden
                parseClaimsJws(token).getBody();
    }


}
