package com.shivamingale.backend.service;

import com.shivamingale.backend.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;


@Service
@EnableWebSecurity
public class JwtService {
    /*
        private final String SECRET_KEY = "b1c6c612c7f77173ff29ed32f62e23a86e2ef8040e350d99497f948789d73d3e";
    */
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user) {
        String emailFromToken = extractUsername(token);

        if (user instanceof User) { // Check if UserDetails is an instance of Employee
            User activeUser = (User) user; // Cast UserDetails to Employee
            String emailFromUserDetails = activeUser.getEmail(); // Get mobile number from UserDetails (Employee)
            return (emailFromToken.equals(emailFromUserDetails)) && !isTokenExpired(token);
        }
        return false;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(User activeUser) {
        String token = Jwts
                .builder()
                .claim("role", activeUser.getRole())
                .subject(activeUser.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(getSignInKey())
                .compact();

        return token;
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
