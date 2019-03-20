package com.westos.rbac.util;

import com.westos.rbac.dao.impl.ModuleDaoImpl;
import com.westos.rbac.dao.impl.RoleDaoImpl;
import com.westos.rbac.domain.Module;
import com.westos.rbac.domain.Role;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * jdbc工具类
 * @author yihang
 */
public class JdbcUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
        }
    }

    static ThreadLocal<Connection> tl = new ThreadLocal<>();

    /**
     * 获取数据库连接
     * @return 连接对象
     */
    public static Connection getConnection() {
        try {
               return  DriverManager.getConnection("jdbc:mysql://localhost:3306/rbac", "root", "");
        } catch (SQLException e) {
            System.out.println("获取连接失败" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static  void setAttributeModuleDao(HttpServletRequest req, int id) {
        ModuleDaoImpl moduleDao = new ModuleDaoImpl();
        RoleDaoImpl roleDao= new RoleDaoImpl();
        //查询所有权限
        List<Module> allModules = moduleDao.findAll();
        //存放用户的拥有的所有权限id
        Set<Integer> userModules= new LinkedHashSet<>();
        //存放用户的所有父权限id(一级权限)
        Set<Integer> parentModules= new LinkedHashSet<>();
        //查询用户所有角色
        List<Role> roles = roleDao.findByUserId(id);
        for (Role role : roles) {
            //该角色对应的权限
            List<Module> modules = moduleDao.findByRoleId(role.getId());
            for (Module module : modules) {
                userModules.add(module.getId());
                parentModules.add(module.getPid());
            }
        }
//        for (Module allModule : allModules) {
//            System.out.println(allModule.getId());
//            List<Module> children = allModule.getChildren();
//            for (Module child : children) {
//                System.out.println("    "+child.getId());
//            }
//        }
//        System.out.println(userModules);
//        System.out.println(parentModules);
        req.getSession().setAttribute("allModules", allModules);
        req.getSession().setAttribute("userModules", userModules);
        req.getSession().setAttribute("parentModules", parentModules);
    }

}
