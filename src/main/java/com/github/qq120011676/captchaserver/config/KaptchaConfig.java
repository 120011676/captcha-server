package com.github.qq120011676.captchaserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@Data
@ConfigurationProperties(prefix = "chang-hong.pay")
public class KaptchaConfig {
    private Properties properties;
}
