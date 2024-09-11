package com.zerobase.domain.util;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RedisUtil {

  private final StringRedisTemplate template;

  // RefreshToken을 Redis에 저장 (email을 key로 사용)
  public void setDataExpire(String key, String value, long durationInSeconds) {
    template.opsForValue().set(key, value, Duration.ofSeconds(durationInSeconds));
  }

  public String getData(String key) {
    return template.opsForValue().get(key);
  }

  public void deleteData(String key) {
    template.delete(key);
  }
}
