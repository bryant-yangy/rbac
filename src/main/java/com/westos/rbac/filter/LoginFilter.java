package com.westos.rbac.filter;

import com.westos.rbac.dao.impl.ModuleDaoImpl;
import com.westos.rbac.domain.Module;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yihang
 */
@WebFilter({"/order/*", "/product/*", "/system/*", "/jsp/*"})
public class LoginFilter implements Filter {
    ModuleDaoImpl moduleDao = new ModuleDaoImpl();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String addr = req.getRemoteAddr();
        if(!addr.equals("0:0:0:0:0:0:0:1")){
            resp.sendRedirect( "/filter.html");
            return;
        }


        Object user = req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect( "/login.jsp?error=1");
            return;
        }

        HttpSession session = req.getSession();
        Set<Integer> userModules=(HashSet<Integer>)session.getAttribute("userModules");
        List<Module> allModules = (List<Module>)session.getAttribute("allModules");
        List<String> list = new ArrayList<>();
        for (Module modules : allModules) {
            for (Module module : modules.getChildren()) {
                if(!userModules.contains(module.getId())){
                    list.add(module.getCode());
                }
            }
        }
        System.out.println("-------------------------------");
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        System.out.println(list);
        System.out.println((list.contains(requestURI)));
        if(!(list.contains(requestURI))){
            System.out.println("放行");
            chain.doFilter(req, response);
        }else {
            System.out.println("拒绝访问");
            resp.sendRedirect("/index.jsp");
        }
    }
    @Override
    public void destroy() {
    }
}
