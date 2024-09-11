package com.zerobase.domain.domain.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenDto {
  private String accesstoken;
  private String refreshtoken;
}
