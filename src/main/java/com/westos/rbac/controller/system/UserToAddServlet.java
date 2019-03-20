package com.westos.rbac.controller.system;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yihang
 *
 */
@WebServlet("/system/user/toadd")
public class UserToAddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 补充跳至新增用户表单的代码
        req.getRequestDispatcher("/jsp/system/user/toadd.jsp").forward(req,resp);
    }
}
