package com.zerobase.domain.service;

import com.zerobase.domain.domain.entity.User;
import com.zerobase.domain.domain.entity.dto.LoginDto;
import com.zerobase.domain.domain.entity.dto.SignupDto;
import com.zerobase.domain.domain.entity.dto.TokenDto;
import com.zerobase.domain.exception.CustomException;
import com.zerobase.domain.exception.ErrorCode;
import com.zerobase.domain.repository.UserRepository;
import com.zerobase.domain.security.jwt.JwtTokenProvider;
import com.zerobase.domain.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtProvider;
  private final RedisUtil redisUtil;


  // 회원가입
  public User register(SignupDto signupDto) {
    // 이메일 중복 체크
    if (userRepository.findByEmail(signupDto.getEmail()).isPresent()) {
      throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
    }

    // 유저 등록
    User user = User.builder()
        .email(signupDto.getEmail())
        .name(signupDto.getName())
        .password(passwordEncoder.encode(signupDto.getPassword()))
        .build();

    // 유저 저장
    return userRepository.save(user);
  }

  // 로그인 시 AccessToken과 RefreshToken 발급
  public TokenDto login(LoginDto dto) {
    User user = userRepository.findByEmail(dto.getEmail())
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

    if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
      throw new CustomException(ErrorCode.LOGIN_CHECK_FAIL);
    }

    // AccessToken과 RefreshToken 생성
    String accessToken = jwtProvider.createAccessToken(user.getEmail());
    String refreshToken = jwtProvider.createRefreshToken();

    // RefreshToken을 Redis에 저장 (email을 key로 사용)
    redisUtil.setDataExpire(user.getEmail(), refreshToken,
        jwtProvider.getRefreshTokenValidityInMillis());

    return new TokenDto(accessToken, refreshToken);
  }

  // RefreshToken을 사용하여 새로운 AccessToken 발급
  public String refreshAccessToken(String refreshToken) {
    if (!jwtProvider.validateToken(refreshToken)) {
      throw new CustomException(ErrorCode.TOKEN_EXPIRE);
    }

    String email = jwtProvider.getEmailFromToken(refreshToken);
    return jwtProvider.createAccessToken(email);
  }

  // 로그아웃: RefreshToken을 Redis에서 삭제
  public void logout(String email) {
    if (redisUtil.getData(email) != null) {
      redisUtil.deleteData(email);
    }
  }

}
