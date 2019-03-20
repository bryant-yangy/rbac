package com.westos.rbac.controller.system;

import com.westos.rbac.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yihang
 */
@WebServlet("/system/user/delete")
public class UserDeleteServlet extends HttpServlet {
    UserDaoImpl userDao = new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 补充删除用户的代码
        String idStr = req.getParameter("id");
        userDao.delete(Integer.parseInt(idStr));
        resp.sendRedirect("page");
    }
}
