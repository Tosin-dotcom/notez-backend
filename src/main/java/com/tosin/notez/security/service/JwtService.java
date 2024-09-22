package com.tosin.notez.security.service;


import com.tosin.notez.exception.NotezException;
import com.tosin.notez.user.dto.Role;
import com.tosin.notez.user.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final HttpServletRequest request;

    @Value("${notez.secret-key}")
    public String secretKey;


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
        claims.put(TokenClaim.FIRST_NAME.name(), userDto.getFirstName());
        claims.put(TokenClaim.LAST_NAME.name(), userDto.getLastName());
        claims.put(TokenClaim.ROLE.name(), userDto.getRole().name());
        claims.put(TokenClaim.EMAIL.name(), userDto.getEmail());
        return createToken(claims, userDto.getEmail());
    }

    private String createToken(Map<String, Object> claims, String email) {


        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(Date.from(Instant.now().plus(40, ChronoUnit.HOURS)))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {

        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public TokenDetail getUserDetails() {

        String jwtString = extractJwtFromRequest();
        return extractTokenDetail(jwtString);
    }

    public TokenDetail extractTokenDetail(String token) {

        Claims claims = extractAllClaims(token);
        if (Boolean.TRUE.equals(isTokenExpired(token))) {

            throw new NotezException("Token Expired", HttpStatus.UNAUTHORIZED);
        }
        return TokenDetail
                .builder()
                .id(UUID.fromString(getClaim(claims, TokenClaim.ID)))
                .email(getClaim(claims, TokenClaim.EMAIL))
                .firstName(getClaim(claims, TokenClaim.FIRST_NAME))
                .lastName(getClaim(claims, TokenClaim.LAST_NAME))
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

        try {
            return
                    (Claims) Jwts.parser()
                            .verifyWith(getSignKey())
                            .build()
                            .parse(token)
                            .getPayload();
        } catch (Exception exception) {

            throw new NotezException("Invalid Token", HttpStatus.UNAUTHORIZED);
        }
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {

        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
