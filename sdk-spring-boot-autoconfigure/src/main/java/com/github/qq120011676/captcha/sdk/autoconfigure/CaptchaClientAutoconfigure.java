package com.github.qq120011676.captcha.sdk.autoconfigure;

import com.github.qq120011676.captcha.sdk.CaptchaClient;
import com.github.qq120011676.captcha.sdk.properties.CaptchaClientProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnClass(CaptchaClient.class)
@EnableConfigurationProperties(CaptchaClientProperties.class)
public class CaptchaClientAutoconfigure {
    @Resource
    private CaptchaClientProperties captchaClientProperties;

    @Bean
    @ConditionalOnMissingBean(CaptchaClient.class)
    public CaptchaClient captchaClient() {
        CaptchaClient captchaClient = new CaptchaClient(this.captchaClientProperties.getBaseUrl());
        captchaClient.setImageKaptchaUri(this.captchaClientProperties.getImageKaptchaUri());
        captchaClient.setCheckKaptchaUri(this.captchaClientProperties.getCheckKaptchaUri());
        captchaClient.setVerifyKaptchaUri(this.captchaClientProperties.getVerifyKaptchaUri());
        return captchaClient;
    }
}
