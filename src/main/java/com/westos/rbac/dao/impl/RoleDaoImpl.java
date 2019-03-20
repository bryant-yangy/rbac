package com.westos.rbac.dao.impl;

import com.westos.rbac.dao.RoleDao;
import com.westos.rbac.domain.Role;
import com.westos.rbac.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yihang
 */
public class RoleDaoImpl implements RoleDao {
    private Role getRole(ResultSet rs) throws SQLException {
        ModuleDaoImpl moduleDao = new ModuleDaoImpl();
        Role role;
        role = new Role();
        role.setId(rs.getInt("id"));
        role.setName(rs.getString("name"));
        role.setModules(moduleDao.findByRoleId(rs.getInt("id")));
        return role;
    }
    @Override
    public List<Role> findAll() {
        try (Connection co = JdbcUtil.getConnection()) {
            List<Role> list = new ArrayList<>();
            Role role;
            try (PreparedStatement ps = co.prepareStatement("select * from rbac_role;")){
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    role = getRole(rs);
                    list.add(role);
                }
                return list;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }



    @Override
    public List<Role> findByUserId(int userId) {
        try (Connection co = JdbcUtil.getConnection()) {
            List<Role> list = new ArrayList<>();
            Role role;
            try (PreparedStatement ps = co.prepareStatement("select * from rbac_role where id in(select role_id from rbac_user_role where user_id=?);")){
                ps.setInt(1,userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    role = getRole(rs);
                    list.add(role);
                }
                return list;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteRoleModule(int roleId) {
        String sql = "DELETE FROM RBAC_ROLE_MODULE WHERE ROLE_ID=?";
        try (Connection co = JdbcUtil.getConnection()) {
            try (PreparedStatement psmt = co.prepareStatement(sql)) {
                psmt.setInt(1, roleId);
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertRoleModule(int roleId, int moduleId) {
        String sql = "INSERT INTO RBAC_ROLE_MODULE (ROLE_ID, MODULE_ID) VALUES (?,?)";
        try (Connection co = JdbcUtil.getConnection()) {
            try (PreparedStatement psmt = co.prepareStatement(sql)) {
                psmt.setInt(1, roleId);
                psmt.setInt(2, moduleId);
                psmt.executeUpdate(); } } catch (SQLException e) {
            e.printStackTrace();
            }
    }
}
