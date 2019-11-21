package com.github.qq120011676.captcha.sdk.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "captcha.client")
public class CaptchaClientProperties {
    /**
     * 验证码服务地址
     */
    private String baseUrl;
    /**
     * kaptcha图片验证码图片uri
     */
    private String imageKaptchaUri = "/kaptcha/image";
    /**
     * kaptcha图片验证码检查uri
     */
    private String checkKaptchaUri = "/kaptcha/check";
    /**
     * kaptcha图片验证码验证uri
     */
    private String verifyKaptchaUri = "/kaptcha/verify";
}
