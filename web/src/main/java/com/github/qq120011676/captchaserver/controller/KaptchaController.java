package com.github.qq120011676.captchaserver.controller;

import com.github.qq120011676.captcha.properties.CaptchaProperties;
import com.github.qq120011676.captchaserver.entity.CaptchaEntity;
import com.github.qq120011676.captchaserver.service.CaptchaService;
import com.github.qq120011676.kaptcha.properties.KaptchaProperties;
import com.github.qq120011676.ladybird.web.exception.RestfulExceptionHelper;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.convert.DurationStyle;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("kaptcha")
public class KaptchaController {
    @Resource
    private Producer producer;
    @Resource
    private KaptchaProperties kaptchaProperties;
    @Resource
    private CaptchaService captchaService;
    @Resource
    private CaptchaProperties captchaProperties;
    @Resource
    private RestfulExceptionHelper restfulExceptionHelper;

    @RequestMapping("image")
    public void image(String key, String timeout, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!StringUtils.hasText(key)) {
            throw this.restfulExceptionHelper.getRestfulRuntimeException("key_null_error");
        }
        Producer producerCustom = this.producer;
        Set<String> names = request.getParameterMap().keySet().stream().filter(s -> !("key".equals(s) || "timeout".equals(s))).collect(Collectors.toSet());
        if (!names.isEmpty()) {
            Properties properties = new Properties();
            kaptchaProperties.getKaptcha().forEach(properties::put);
            names.forEach(s -> properties.put(s, request.getParameter(s)));
            DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
            defaultKaptcha.setConfig(new Config(properties));
            producerCustom = defaultKaptcha;
        }
        String text = producerCustom.createText();
        CaptchaEntity captcha = new CaptchaEntity();
        captcha.setText(text);
        captcha.setTimeout(StringUtils.hasText(timeout) ? DurationStyle.SIMPLE.parse(timeout) : this.captchaProperties.getTimeout());
        this.captchaService.setCaptcha(key, captcha);
        BufferedImage bi = producerCustom.createImage(text);
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(bi, "gif", out);
            out.flush();
        }
    }

    @RequestMapping("test")
    public boolean test(String key, String text) {
        if (!StringUtils.hasText(key)) {
            throw this.restfulExceptionHelper.getRestfulRuntimeException("key_null_error");
        }
        if (!StringUtils.hasText(text)) {
            throw this.restfulExceptionHelper.getRestfulRuntimeException("text_null_error");
        }
        CaptchaEntity captcha = this.captchaService.getCaptcha(key);
        if (captcha == null) {
            throw this.restfulExceptionHelper.getRestfulRuntimeException("text_not_found");
        }
        if (ZonedDateTime.now().isAfter(captcha.getZonedDateTime().plusSeconds(captcha.getTimeout().toSeconds()))) {
            throw this.restfulExceptionHelper.getRestfulRuntimeException("text_timeout_error");
        }
        if (!text.equals(captcha.getText())) {
            throw this.restfulExceptionHelper.getRestfulRuntimeException("text_error");
        }
        return true;
    }

    @RequestMapping("verify")
    public boolean verify(String key, String text) {
        boolean bol = test(key, text);
        if (bol) {
            this.captchaService.cleanCaptcha(key);
        }
        return bol;
    }
}
