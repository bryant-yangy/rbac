package com.westos.rbac;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkCaptcha")
public class CheckCaptchaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 参数中用户输入的验证码
        String captcha = req.getParameter("captcha");

        // session 中的验证码正确答案
        Object result = req.getSession().getAttribute("result");

        resp.setContentType("application/json;charset=utf-8");
        if(captcha != null && captcha.equalsIgnoreCase(result.toString())) { // 验证通过
            resp.getWriter().print(true);
        } else { // 验证不通过
            resp.getWriter().print(false);
        }
    }
}
