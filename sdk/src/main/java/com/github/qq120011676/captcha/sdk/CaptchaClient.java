package com.github.qq120011676.captcha.sdk;

import com.github.qq120011676.captcha.sdk.entity.CaptchaResult;
import com.github.qq120011676.captcha.sdk.entity.CaptchaResultBytes;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

@Data
@AllArgsConstructor
public class CaptchaClient {
    private String baseUrl;
    private String imageKaptchaUri = "/kaptcha/image";
    private String checkKaptchaUri = "/kaptcha/check";
    private String verifyKaptchaUri = "/kaptcha/verify";

    public CaptchaClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public CaptchaResultBytes imageKaptcha(String key) throws IOException {
        return this.imageKaptcha(key, null, null);
    }

    public CaptchaResultBytes imageKaptcha(String key, String timeout) throws IOException {
        return this.imageKaptcha(key, timeout, null);
    }

    public CaptchaResultBytes imageKaptcha(String key, Map<String, String> config) throws IOException {
        return this.imageKaptcha(key, null, config);
    }

    public CaptchaResultBytes imageKaptcha(String key, String timeout, Map<String, String> config) throws IOException {
        Connection connect = Jsoup.connect(MessageFormat.format("{0}{1}", this.baseUrl, this.imageKaptchaUri));
        if (!StringUtil.isBlank(key)) {
            connect = connect.data("key", key);
        }
        if (!StringUtil.isBlank(timeout)) {
            connect = connect.data("timeout", timeout);
        }
        if (config != null && !config.isEmpty()) {
            connect = connect.data(config);
        }
        Connection.Response response = connect.method(Connection.Method.POST).ignoreHttpErrors(true).ignoreContentType(true).execute();
        CaptchaResultBytes captchaResultBytes = new CaptchaResultBytes();
        if (200 == response.statusCode()) {
            captchaResultBytes.setStatus(true);
            captchaResultBytes.setBody(response.bodyAsBytes());
            return captchaResultBytes;
        }
        return new Gson().fromJson(response.body(), CaptchaResultBytes.class);
    }

    public CaptchaResult checkKaptcha(String key, String text) throws IOException {
        return this.testOrVerify(this.checkKaptchaUri, key, text);
    }

    public CaptchaResult verifyKaptcha(String key, String text) throws IOException {
        return this.testOrVerify(this.verifyKaptchaUri, key, text);
    }

    private CaptchaResult testOrVerify(String uri, String key, String text) throws IOException {
        Connection.Response response = Jsoup.connect(MessageFormat.format("{0}{1}", this.baseUrl, uri)).data("key", key).data("text", text).method(Connection.Method.POST).ignoreHttpErrors(true).ignoreContentType(true).execute();
        if (200 == response.statusCode() && "true".equals(response.body())) {
            CaptchaResult captchaResult = new CaptchaResult();
            captchaResult.setStatus(true);
            return captchaResult;
        }
        return new Gson().fromJson(response.body(), CaptchaResult.class);
    }
}
