package com.zerobase.domain.security.jwt;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider provider;

  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain)
      throws IOException, ServletException {
    String token = getJwtFromRequest(request);

    try {
      if (token != null && provider.validateToken(token)) {

        // SecurityContext에 인증 정보 설정
        String email = provider.getEmailFromToken(token); // 토큰에서 이메일을 추출
        Authentication authentication = provider.getAuthentication(email); // 인증 객체 생성
        SecurityContextHolder.getContext().setAuthentication(authentication); // 인증 정보를 설정
      }

    } catch (JwtException ex) {
      // JWT가 유효하지 않으면 에러 처리
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }

    chain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
