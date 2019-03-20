package com.westos.rbac.controller.system;

import com.westos.rbac.dao.impl.UserDaoImpl;
import com.westos.rbac.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yihang
 */
@WebServlet("/system/user/toupdate")
public class UserToUpdateServlet extends HttpServlet {
    UserDaoImpl userDao =new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 补充跳至修改用户表单的代码
        String idStr = req.getParameter("id");
        User user = userDao.findById(Integer.parseInt(idStr));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/jsp/system/user/toupdate.jsp").forward(req,resp);
    }
}
