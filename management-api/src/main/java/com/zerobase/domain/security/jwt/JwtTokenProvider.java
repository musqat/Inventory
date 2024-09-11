package com.zerobase.domain.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  @Value("${jwt.secret-key}")
  private String secretKey;

  @Value("${jwt.token.access-expire}")
  private Long accessTokenValidity;

  @Value("${jwt.token.refresh-expire}")
  private Long refreshTokenValidity;

  // Base64 인코딩된 secretKey를 디코딩하여 Key 객체 생성
  private Key getSigningKey() {
    byte[] keyBytes = Base64.getDecoder().decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  // AccessToken 생성
  public String createAccessToken(String email) {
    Date now = new Date();
    return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + accessTokenValidity))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  // RefreshToken 생성
  public String createRefreshToken() {
    Date now = new Date();
    return Jwts.builder()
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + refreshTokenValidity))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  // 토큰에서 이메일 추출
  public String getEmailFromToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  // RefreshToken 유효성 검사
  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(getSigningKey())
          .build()
          .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  // JWT 토큰으로부터 Authentication 객체 반환
  public Authentication getAuthentication(String token) {
    String email = getEmailFromToken(token);
    return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
  }

  // RefreshToken 유효 기간 반환
  public long getRefreshTokenValidityInMillis() {
    return refreshTokenValidity;
  }

}

