package com.github.qq120011676.captcha.sdk.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CaptchaResultBytes extends CaptchaResult {
    private byte[] body;
}
