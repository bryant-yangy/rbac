package com.westos.rbac.test;

import com.westos.rbac.domain.Module;
import com.westos.rbac.dao.ModuleDao;
import com.westos.rbac.dao.impl.ModuleDaoImpl;

import java.util.List;

/**
 * @author yihang
 */
public class TestModuleDao {

    public static void main(String[] args) {
        ModuleDao dao = new ModuleDaoImpl();
        System.out.println("测试查询所有的模块...");
        List<Module> all = dao.findAll();
       System.out.println("模块总数：" + all.size());
        for (Module module : all) {
            System.out.println(module.getName());
            List<Module> children = module.getChildren();
            for(Module module1: children){
                System.out.println("    "+module1.getName());
            }
        }
        System.out.println("测试根据角色查询所有模块...");
        List<Module> list = dao.findByRoleId(1);
        System.out.println("系统管理员的模块有：");
        for (Module module : list) {
            System.out.println(module.getName());
        }

    }
}
