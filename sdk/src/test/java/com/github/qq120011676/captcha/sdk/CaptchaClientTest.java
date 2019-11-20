package com.github.qq120011676.captcha.sdk;

import java.io.IOException;

public class CaptchaClientTest {
    public static void main(String[] args) throws IOException {
        String baseUrl = "http://localhost:8080";
        String key = "1";
        String pathnameBytes = "/tmp/test.gif";
        String text = "ed5c";
        CaptchaClient captchaClient = new CaptchaClient(baseUrl);
//        CaptchaResultBytes captchaResultBytes = captchaClient.imageKaptcha(key);
//        if (captchaResultBytes.isStatus()) {
//            FileUtils.writeByteArrayToFile(new File(pathnameBytes), captchaResultBytes.getBody());
//        } else {
//            System.out.println(captchaResultBytes + "--" + captchaResultBytes.getCode() + "--" + captchaResultBytes.getMessage());
//        }
        System.out.println(captchaClient.testKaptcha(key, text));
        System.out.println(captchaClient.verifyKaptcha(key, text));
    }
}
