package com.westos.rbac.controller.system;

import com.westos.rbac.dao.UserDao;
import com.westos.rbac.dao.impl.UserDaoImpl;
import com.westos.rbac.domain.User;
import com.westos.rbac.util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author yihang
 */
@WebServlet("/system/user/add")
public class UserAddServlet extends HttpServlet {
    UserDao userDao = new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 补充新增用户的代码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String orgIdStr = req.getParameter("orgId");
        User user = new User();
        user.setUsername(username);
        user.setPassword(Md5Util.md5(password));
        user.setOrgId(Integer.parseInt(orgIdStr));
        userDao.insert(user);
        resp.sendRedirect("page");
    }
}
