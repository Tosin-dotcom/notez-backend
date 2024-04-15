package com.tosin.notez.security.service;


import com.tosin.notez.user.dto.Role;
import com.tosin.notez.user.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final HttpServletRequest request;
    private static final String SECRET_KEY = "a432rsKciXBO7aJm9jQ5laB75JcA/jjQsf90QFyrG8AUvnOzWKMULWPgAunmGhiGdv+qKUI+WOA/jSbCxJ48inHnLFnFa5z2RDYofN3IspncgBRztJDfODWet2VIZYYsrcHZ8v8xfcExtb2bxGfSrCs5Qneh5+zKFwkstwhfGIHPqum2EOoTDD8K4Iw1YmNvUh9SRG+t0zmV68jCFp0+nAFc1lcMp+GqSN7MabsieatBR5+/kZS8Q5WvamzhM1v9RLZ5RvHtkb1plkx5L3fPI3hYB5fOF50u4DvOQjx4dNBTSGSOx75/lmu8ffwlvSjFg/QYw5nROfWJ1dh0ar/krQXWXo6f0zMEWaA4Xu4bnk8=";


    public String extractJwtFromRequest() {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring(7);
    }


    public String generateToken(UserDto userDto) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(TokenClaim.ID.name(), userDto.getId());
        claims.put(TokenClaim.FULL_NAME.name(), userDto.getFullName());
        claims.put(TokenClaim.ROLE.name(), userDto.getRole().name());
        claims.put(TokenClaim.EMAIL.name(), userDto.getEmail());
        return createToken(claims, userDto.getEmail());
    }

    private String createToken(Map<String, Object> claims, String email) {

        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {

        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public TokenDetail extractTokenDetail(String token) {

        Claims claims = extractAllClaims(token);
        return TokenDetail
                .builder()
                .id(UUID.fromString(getClaim(claims, TokenClaim.ID)))
                .email(getClaim(claims, TokenClaim.EMAIL))
                .fullName(getClaim(claims, TokenClaim.FULL_NAME))
                .role(Role.valueOf(getClaim(claims, TokenClaim.ROLE)))
                .build();
    }

    private String getClaim(Claims claims, TokenClaim tokenClaim) {

        Object claim = claims.getOrDefault(tokenClaim.name(), null);
        return claim.toString();
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {

        return
                (Claims) Jwts.parser()
                        .verifyWith(getSignKey())
                        .build()
                        .parse(token)
                        .getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {

        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
