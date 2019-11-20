package com.github.qq120011676.captcha.sdk.entity;

import lombok.Data;

@Data
public class CaptchaResult {
    private boolean status;
    private String code;
    private String message;
}
