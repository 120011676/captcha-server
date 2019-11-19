package com.github.qq120011676.captchaserver.service.impl;

import com.github.qq120011676.captchaserver.entity.CaptchaEntity;
import com.github.qq120011676.captchaserver.service.CaptchaService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "captcha")
public class CaptchaServiceImpl implements CaptchaService {
    @CachePut(key = "#key", unless = "#result == null")
    @Override
    public CaptchaEntity setCaptcha(String key, CaptchaEntity captcha) {
        return captcha;
    }

    @Cacheable(key = "#key", unless = "#result == null")
    @Override
    public CaptchaEntity getCaptcha(String key) {
        return null;
    }

    @CacheEvict(key = "#key")
    @Override
    public CaptchaEntity cleanCaptcha(String key) {
        return null;
    }

}
