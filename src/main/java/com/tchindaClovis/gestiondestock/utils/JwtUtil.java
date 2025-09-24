package com.tchindaClovis.gestiondestock.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractIdEntreprise(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.get("idEntreprise", String.class);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("idEntreprise", String.class);
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}






//package com.tchindaClovis.gestiondestock.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//public class JwtUtil {
//
//    // Clé secrète plus robuste (à mettre dans application.properties)
//    private final SecretKey SECRET_KEY = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
//    private final long JWT_EXPIRATION = 1000 * 60 * 60 * 10; // 10 heures
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public String extractIdEntreprise(String token) {
//        final Claims claims = extractAllClaims(token);
//        return claims.get("idEntreprise", String.class);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser()
//                .verifyWith(SECRET_KEY)
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
//
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, userDetails.getUsername());
//    }
//
//    public String generateToken(UserDetails userDetails, Integer idEntreprise) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("idEntreprise", idEntreprise.toString());
//        return createToken(claims, userDetails.getUsername());
//    }
//
//    private String createToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder()
//                .claims(claims)
//                .subject(subject)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
//                .signWith(SECRET_KEY)
//                .compact();
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//}



//package com.tchindaClovis.gestiondestock.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//    @Service
//    public class JwtUtil {
//
//        private String SECRET_KEY = "secret";
//
//        public String extractUsername(String token) {
//            return extractClaim(token, Claims::getSubject);
//        }
//
//        public Date extractExpiration(String token) {
//            return extractClaim(token, Claims::getExpiration);
//        }
//
//        public String extractIdEntreprise(String token) {
//            final Claims claims = extractAllClaims(token);
//
//            return claims.get("idEntreprise", String.class);
//        }
//
//        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//            final Claims claims = extractAllClaims(token);
//            return claimsResolver.apply(claims);
//        }
//
//        private Claims extractAllClaims(String token) {
//            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//        }
//
//        private Boolean isTokenExpired(String token) {
//            return extractExpiration(token).before(new Date());
//        }
//
//        public String generateToken(ExtendedUser userDetails) {
//            Map<String, Object> claims = new HashMap<>();
//            return createToken(claims, userDetails);
//        }
//
//        private String createToken(Map<String, Object> claims, ExtendedUser userDetails) {
//
//            return Jwts.builder().setClaims(claims)
//                    .setSubject(userDetails.getUsername())
//                    .setIssuedAt(new Date(System.currentTimeMillis()))
//                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                    .claim("idEntreprise", userDetails.getIdEntreprise().toString())
//                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
//        }
//
//        public Boolean validateToken(String token, UserDetails userDetails) {
//            final String username = extractUsername(token);
//            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//        }
//
//    }
