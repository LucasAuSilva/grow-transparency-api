package com.growtransparency.services;

import com.growtransparency.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
  @Value("${growtransparency.jwt.expiration}")
  private String expiration;

  @Value("${growtransparency.jwt.secret}")
  private String secret;

  public String generateToken(Authentication authentication) {
    var user = (User) authentication.getPrincipal();
    var today = new Date();
    var expirationDate = new Date(today.getTime() + Long.parseLong(expiration));

    return Jwts.builder()
            .setIssuer(user.getId().toString())
            .setIssuedAt(today)
            .setExpiration(expirationDate)
            .claim("email", user.getEmail())
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
  }

  public boolean isTokenValid(String token) {
    try {
      Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public Long getUserId(String token) {
    var claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

    return Long.parseLong(claims.getIssuer());
  }

  public Long getExpired(String token) {
    var claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

    return claims.getExpiration().getTime();
  }

  public Long getIssued(String token) {
    var claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

    return claims.getIssuedAt().getTime();
  }
}
