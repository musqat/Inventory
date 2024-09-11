package com.zerobase.domain.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.zerobase.domain.domain.entity.User;
import com.zerobase.domain.domain.entity.dto.LoginDto;
import com.zerobase.domain.domain.entity.dto.TokenDto;
import com.zerobase.domain.repository.UserRepository;
import com.zerobase.domain.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserController userController;


  @Test
  void register() throws Exception {
    // given
    // 만약 register() 메소드가 어떤 반환 타입이 있다면 thenReturn을 사용해야 합니다.
    given(userService.register(any())).willReturn(null);

    // when
    ResultActions resultActions = mockMvc.perform(post("/user/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\": \"hjs13@naver.com\", \"name\": \"han\", \"password\": \"1234\"}"));

    // then
    resultActions.andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void login() throws Exception {
    // given
    User testUser = new User();
    testUser.setEmail("hjs13@naver.com");
    testUser.setPassword("encodedPassword");  // 패스워드는 암호화된 상태로 사용해야 함

    given(userRepository.findByEmail(any())).willReturn(Optional.of(testUser));

    TokenDto tokenDto = new TokenDto("accessToken", "refreshToken");
    given(userService.login(any(LoginDto.class))).willReturn(tokenDto);

    // when
    ResultActions resultActions = mockMvc.perform(post("/user/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\": \"hjs13@naver.com\", \"password\": \"1234\"}"));

    // then
    resultActions.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.accessToken").value("accessToken"))
        .andExpect(jsonPath("$.refreshToken").value("refreshToken"));
  }

  @Test
  void refreshAccessToken() throws Exception {
    // given
    given(userService.refreshAccessToken(any())).willReturn("newAccessToken");

    // when
    ResultActions resultActions = mockMvc.perform(post("/user/refresh")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"refreshToken\": \"validRefreshToken\"}"));

    // then
    resultActions.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("newAccessToken"));
  }

}
