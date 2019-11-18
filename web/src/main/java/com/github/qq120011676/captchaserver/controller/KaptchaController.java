package com.github.qq120011676.captchaserver.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("kaptcha")
public class KaptchaController {
    @Resource
    private Producer producer;

    @GetMapping("image")
    public void image(HttpServletResponse response) throws IOException {
        String capText = producer.createText();
        BufferedImage bi = producer.createImage(capText);
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(bi, "gif", out);
            out.flush();
        }
    }
}
