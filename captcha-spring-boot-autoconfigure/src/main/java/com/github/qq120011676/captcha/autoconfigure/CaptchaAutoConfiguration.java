package com.github.qq120011676.captcha.autoconfigure;

import com.github.qq120011676.captcha.properties.CaptchaProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(CaptchaProperties.class)
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaAutoConfiguration {
}
