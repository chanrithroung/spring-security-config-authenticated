package com.example.spring_Security.service.TokenService;
import com.example.spring_Security.utils.KeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JWTServices {

    private final KeyUtils keyUtils;
    private final String key;

    @Autowired
    public JWTServices(KeyUtils keyUtils) {
        this.keyUtils = keyUtils;
        this.key = keyUtils.generateSecretKey();
    }

    public String generateToken(String username) {
        HashMap<String, Object> claims = new HashMap<>();
        int day = 1;
        long expiredTime = day * (24 * 60 * 60 * 1000);
        claims.put("role", "USER");
        System.out.println();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(SignatureAlgorithm.HS256,key)
                .compact(); // build the token.
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        return (userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        // extract username
        return extractClaims(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        // Extract specific claims using claims resolver.
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new RuntimeException("Invalid jwt token: "+ e.getMessage());
        }
    }

}
