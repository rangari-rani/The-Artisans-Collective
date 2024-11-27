package com.art.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtProvider {

    SecretKey key = Keys.hmacShaKeyFor(JWT_CONSTANT.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth){
        Collection<? extends GrantedAuthority> authorities=auth.getAuthorities();
        String roles=populateAuthorities(authorities);

        return  Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email",auth.getName())
                .claim("authorities",roles)
                .signWith(key)
                .compact();

    }

//    public String getEmailFromJwtToken(String jwt){
//        jwt=jwt.substring(7);
//        Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
//
//        return String.valueOf(claims.get("email"));
//    }
public String getEmailFromJwtToken(String jwt) {
    // Validate and remove the "Bearer " prefix if present
    if (jwt.startsWith("Bearer ")) {
        jwt = jwt.substring(7); // Remove "Bearer " (7 characters)
    }

    // Parse the JWT to extract claims
    Claims claims = Jwts.parserBuilder()
            .setSigningKey(key) // Use the signing key to validate and parse the token
            .build()
            .parseClaimsJws(jwt)
            .getBody();

    // Retrieve and return the "email" claim
    return String.valueOf(claims.get("email"));
}


    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();

        for(GrantedAuthority authority:authorities){
            auths.add(authority.getAuthority());
        }

        return String.join(",",auths);
    }
}
