package com.mobile.integration.grandstores.Utils.JwtWebSecure;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String SECRET_KEY = "4i_Mobile_Apps";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getIssuer);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    // 3 extract All Claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    // 1 Generate Token
    public String generateToken(UserDetails userDetails, String userName, String personId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", userName);
        claims.put("PersonId", personId);
        return createToken(claims, userDetails.getUsername());
    }
    // 2 create token
    private String createToken(Map<String, Object> claims, String userid) {

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(userid)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 150))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}




