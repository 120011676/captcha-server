package com.github.qq120011676.kaptcha.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@Data
@ConfigurationProperties(prefix = "captcha")
public class KaptchaProperties {
    private Properties kaptcha;
}
