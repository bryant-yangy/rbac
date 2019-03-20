package com.westos.rbac.controller.system;

import com.westos.rbac.dao.impl.UserDaoImpl;
import com.westos.rbac.domain.User;

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
@WebServlet("/system/user/page")
public class UserPageServlet extends HttpServlet {
    UserDaoImpl userDao = new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 补充分页查询用户的代码
        int page=1;
        int rows=5;
        String page1 = req.getParameter("page");
        if(page1!=null){
            page=new Integer(page1);
        }
        List<User> list = userDao.findByPage(page, rows);
        req.setAttribute("list",list);
        req.setAttribute("page",page);
        //总页数
        int total=(userDao.findCount()-1)/rows+1;
        req.setAttribute("total",total);
        req.getRequestDispatcher("/jsp/system/user/page.jsp").forward(req,resp);
    }
}
