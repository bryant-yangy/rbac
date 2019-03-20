package com.westos.rbac;

import com.westos.rbac.dao.impl.ModuleDaoImpl;
import com.westos.rbac.dao.impl.RoleDaoImpl;
import com.westos.rbac.dao.impl.UserDaoImpl;
import com.westos.rbac.domain.Module;
import com.westos.rbac.domain.Role;
import com.westos.rbac.domain.User;
import com.westos.rbac.util.JdbcUtil;
import com.westos.rbac.util.Md5Util;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yihang
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserDaoImpl userDao = new UserDaoImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username==null){
            resp.sendRedirect(req.getContextPath()+"/login.jsp?error=1");
            return;
        }
        if(password==null){
            resp.sendRedirect(req.getContextPath()+"/login.jsp?error=1");
            return;
        }
        User user = userDao.findByUsername(username);
        if(user == null) {
            resp.sendRedirect(req.getContextPath()+"/login.jsp?error=1");
            return;
        }
        if(!user.getPassword().equals(Md5Util.md5(password))){
            resp.sendRedirect(req.getContextPath()+"/login.jsp?error=1");
            return;
        }
        req.getSession().setAttribute("user", user);
        int id = user.getId();
        JdbcUtil.setAttributeModuleDao(req, id);
        resp.sendRedirect(req.getContextPath()+"/index.jsp");
    }
}
