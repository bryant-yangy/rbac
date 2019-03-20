package com.westos.rbac.controller.system;

import com.westos.rbac.dao.impl.RoleDaoImpl;
import com.westos.rbac.domain.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yihang
 */
@WebServlet("/system/user/tomodifyrole")
public class UserToModifyRoleServlet extends HttpServlet {
    RoleDaoImpl roleDao =new RoleDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> roles = roleDao.findAll();
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        List<Role> userRoles = roleDao.findByUserId(userId);

        req.setAttribute("roles", roles);
        req.setAttribute("userRoles", userRoles);
        req.setAttribute("userId", userId);
        req.getRequestDispatcher("/jsp/system/user/tomodifyrole.jsp")
                .forward(req, resp);
    }
}
