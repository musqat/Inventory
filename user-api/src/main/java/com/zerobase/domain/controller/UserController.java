package com.zerobase.domain.controller;

import com.zerobase.domain.domain.entity.dto.LoginDto;
import com.zerobase.domain.domain.entity.dto.SignupDto;
import com.zerobase.domain.domain.entity.dto.TokenDto;
import com.zerobase.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  // 회원가입
  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody SignupDto signupDto) {
    userService.register(signupDto);
    return ResponseEntity.ok("회원가입이 완료되었습니다.");
  }

  //로그인
  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
    TokenDto tokens = userService.login(loginDto);
    return ResponseEntity.ok(tokens);
  }

  // 로그아웃
  @PostMapping("/logout")
  public ResponseEntity<String> logout(@RequestBody String email) {
    userService.logout(email);
    return ResponseEntity.ok("로그아웃 성공");
  }

  // 토큰 재발행
  @PostMapping("/refresh-token")
  public ResponseEntity<String> refreshAccessToken(@RequestBody String refreshToken) {
    String newAccessToken = userService.refreshAccessToken(refreshToken);
    return ResponseEntity.ok(newAccessToken);
  }


}
