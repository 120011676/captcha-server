package com.github.qq120011676.captchaserver.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

public class KaptchaBean {
    @Resource
    private KaptchaConfig kaptchaConfig;

    @Bean
    public Producer producer() {
        return new DefaultKaptcha();
    }

    @Bean
    public Producer producerProperties() {
        DefaultKaptcha producer = new DefaultKaptcha();
        producer.setConfig(new Config(this.kaptchaConfig.getProperties()));
        return producer;
    }
}
