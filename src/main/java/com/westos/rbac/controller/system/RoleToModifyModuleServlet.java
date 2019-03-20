package com.westos.rbac.controller.system;

import com.westos.rbac.dao.impl.ModuleDaoImpl;
import com.westos.rbac.domain.Module;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yihang
 */
@WebServlet("/system/role/tomodifymodule")
public class RoleToModifyModuleServlet extends HttpServlet {
    ModuleDaoImpl moduleDao = new ModuleDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleId = req.getParameter("id");

        List<Module> modules = moduleDao.findAll();
        List<Module> roleModules = moduleDao.findByRoleId(Integer.parseInt(roleId));
        List<Integer> ids = new ArrayList<>();
        for (Module roleModule : roleModules) {
            ids.add(roleModule.getId());
        }


        req.setAttribute("modules", modules);
        req.setAttribute("ids", ids);
        req.setAttribute("roleModules",roleModules);

        req.getRequestDispatcher("/jsp/system/role/tomodifymodule.jsp").forward(req,resp);

    }
}
