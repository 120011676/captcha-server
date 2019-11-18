package com.github.qq120011676.kaptcha.autoconfigure;

import com.github.qq120011676.kaptcha.properties.KaptchaProperties;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnClass(Producer.class)
@EnableConfigurationProperties(KaptchaProperties.class)
public class KaptchaAutoConfiguration {
    @Resource
    private KaptchaProperties kaptchaProperties;

    @Bean
    @ConditionalOnMissingBean(Producer.class)
    public Producer producer() {
        DefaultKaptcha producer = new DefaultKaptcha();
        if (this.kaptchaProperties != null) {
            producer.setConfig(new Config(this.kaptchaProperties.getKaptcha()));
        }
        return producer;
    }

}
