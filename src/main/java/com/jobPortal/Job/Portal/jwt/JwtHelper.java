package com.jobPortal.Job.Portal.jwt;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtHelper {

    private static final String SECRET_KEY = "YourSecretKeyForJwtAuthYourSecretKeyForJwtAuth"; // Must be at least 32 characters
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hour

    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * Generates a JWT token with a given subject and custom claims.
     *
     * @param subject the subject (usually user ID or username)
     * @return the generated JWT token
     */
    public  String generateToken(UserDetails subject) {
        Map<String,Object> claims= new HashMap<>();
        CustomUserDetails userDetails= (CustomUserDetails)subject;
        claims.put("id",userDetails.getId());
        claims.put("name",userDetails.getName());
        claims.put("accountType",userDetails.getAccountType());
        return Jwts.builder()
                .setSubject(subject.getUsername())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validates the given JWT token and returns the claims if valid.
     *
     * @param token the JWT token
     * @return the claims from the token
     * @throws JwtException if the token is invalid or expired
     */
    public  Boolean validateToken(String token, String username) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject().equals(username);
        } catch (JwtException e) {
            throw new IllegalArgumentException("Invalid or expired token");
        }
    }

    /**
     * Decodes a JWT token without verifying its signature (for debugging).
     *
     * @param token the JWT token
     * @return the claims from the token
     */
    public  Claims decodeToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public  String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (JwtException e) {
            throw new IllegalArgumentException("Invalid or expired token");
        }// The subject typically represents the username
    }

    /**
     * Refreshes a given token by extending its expiration time.
     *
     * @param token the original JWT token
     * @return a new JWT token with extended expiration time
     */

}
