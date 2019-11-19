package com.github.qq120011676.captchaserver.service;

import com.github.qq120011676.captchaserver.entity.CaptchaEntity;

public interface CaptchaService {
    CaptchaEntity setCaptcha(String key, CaptchaEntity captcha);

    CaptchaEntity getCaptcha(String key);

    CaptchaEntity cleanCaptcha(String key);
}
