package com.westos.rbac.test;

import com.westos.rbac.domain.Role;
import com.westos.rbac.domain.User;
import com.westos.rbac.dao.UserDao;
import com.westos.rbac.dao.impl.UserDaoImpl;

import java.util.List;

/**
 * @author yihang
 */
public class TestUserDao {

    public static void main(String[] args) {
        UserDao dao = new UserDaoImpl();
        System.out.println("测试查询所有的用户...");
        List<User> all = dao.findAll();
        System.out.println("用户总数为：" + all.size());

        System.out.println("测试新增用户...");
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123");
        user.setOrgId(113);
        user.setOrgIds(new Integer[]{0,1,11,113});
        dao.insert(user);
        System.out.println("新增用户的id是："+user.getId());

        System.out.println("测试删除用户...id="+user.getId());
        dao.delete(user.getId());

        System.out.println("测试修改用户...");
        User u = dao.findById(1);
        u.setPassword("456");
        dao.update(u);

        System.out.println("测试按用户名查询用户...");
        User admin = dao.findByUsername("admin");
        System.out.println(admin);
        List<Role> roles = admin.getRoles();
        for(Role role :roles){
            System.out.println("   "+role.getName());
        }

        System.out.println("测试分页查询用户...");
        List<User> page1 = dao.findByPage(1, 3);
        System.out.println("第1页：");
        for (User u1 : page1) {
            System.out.println(u1.getId()+" " +u1.getUsername());
        }
        List<User> page2 = dao.findByPage(2, 3);
        System.out.println("第2页：");
        for (User u2 : page2) {
            System.out.println(u2.getId()+" " +u2.getUsername());
        }
        List<User> page3 = dao.findByPage(3, 3);
        System.out.println("第3页：");
        for (User u3 : page3) {
            System.out.println(u3.getId()+" " +u3.getUsername());
        }
    }
}
