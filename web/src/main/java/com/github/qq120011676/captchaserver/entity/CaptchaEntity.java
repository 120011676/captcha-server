package com.github.qq120011676.captchaserver.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.Duration;
import java.time.ZonedDateTime;

@Data
public class CaptchaEntity implements Serializable {
    private String text;
    private ZonedDateTime zonedDateTime = ZonedDateTime.now();
    private Duration timeout;
}
