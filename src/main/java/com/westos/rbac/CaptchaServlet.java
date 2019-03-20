package com.westos.rbac;

import org.orclight.java.util.captha.CaptchaClient;
import org.orclight.java.util.captha.bean.CaptchaBean;
import org.orclight.java.util.captha.strategy.SimpleCaptchaStrategy;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/captcha")
public class CaptchaServlet extends HttpServlet {
    private CaptchaClient captcha = CaptchaClient.create()
            .width(196)
            .height(39)
            .captchaStrategy(new SimpleCaptchaStrategy())
            .build();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 返回的 CaptcheBean 中包含了图片验证码和答案
        CaptchaBean bean = captcha.generate();
        // 将答案存入 session
        String result = bean.getResult();
        req.getSession().setAttribute("result", result);
        System.out.println("答案：" + result);

        // 图片作为响应返回
        resp.setContentType("image/png");
        ImageIO.write(bean.getBufferedImage(), "png", resp.getOutputStream());
    }
}
