package com.westos.rbac.controller.system;

import com.westos.rbac.dao.impl.RoleDaoImpl;
import com.westos.rbac.domain.User;
import com.westos.rbac.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author yihang
 */
@WebServlet("/system/role/modifymodule")
public class RoleModifyModuleServlet extends HttpServlet {
    RoleDaoImpl roleDao = new RoleDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleIdStr = req.getParameter("roleId");
        String[] modules = req.getParameterValues("moduleId");
        roleDao.deleteRoleModule(Integer.valueOf(roleIdStr));
        for(String module :modules){
            roleDao.insertRoleModule(Integer.valueOf(roleIdStr),Integer.valueOf(module));
        }
       User user = (User)req.getSession().getAttribute("user");
        JdbcUtil.setAttributeModuleDao(req, user.getId());
        resp.sendRedirect("/system/role/all");
    }
}
