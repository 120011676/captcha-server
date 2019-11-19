package com.github.qq120011676.captcha.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;

import java.io.Serializable;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Data
@ConfigurationProperties(prefix = "captcha")
public class CaptchaProperties implements Serializable {
    /**
     * captcha timeout
     */
    @DurationUnit(ChronoUnit.MINUTES)
    private Duration timeout = Duration.ofMinutes(5);
}
