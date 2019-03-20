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
@WebServlet("/system/user/modifyrole")
public class UserModifyRoleServlet extends HttpServlet {
    UserDaoImpl userDao=new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        String[] roles = req.getParameterValues("roles");
        int[] roleIds = new int[roles.length];
        for (int i = 0; i < roles.length; i++) {
            roleIds[i] = Integer.valueOf(roles[i]);
        }
        userDao.deleteUserRole(userId);
        for (int roleId : roleIds) {
            userDao.insertUserRole(userId, roleId);
        }

        resp.sendRedirect("/system/user/page");
    }
}
