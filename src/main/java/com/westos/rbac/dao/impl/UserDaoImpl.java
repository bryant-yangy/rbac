package com.westos.rbac.dao.impl;

import com.westos.rbac.dao.UserDao;
import com.westos.rbac.domain.User;
import com.westos.rbac.util.JdbcUtil;
import com.westos.rbac.util.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yihang
 */

public class UserDaoImpl implements UserDao {
    private User getUser(ResultSet rs) throws SQLException {
        RoleDaoImpl roleDao=new RoleDaoImpl();
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setOrgId(rs.getInt("org_id"));
        String str = rs.getString("org_ids");
        user.setRoles(roleDao.findByUserId(rs.getInt("id")));
        Integer[] orgIds = StringUtil.split(str);
        user.setOrgIds(orgIds);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        try (Connection co = JdbcUtil.getConnection()) {
            try (PreparedStatement ps = co.prepareStatement("select * from rbac_user where username=?;")) {
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    User user = getUser(rs);
                    return user;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public User findById(int userId) {
        try (Connection co = JdbcUtil.getConnection()) {
            try (PreparedStatement ps = co.prepareStatement("select * from rbac_user where id=?;")) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    User user = getUser(rs);
                    return user;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        try (Connection co = JdbcUtil.getConnection()) {
            List<User> list = new ArrayList<>();
            User user;
            try (PreparedStatement ps = co.prepareStatement("select * from rbac_user")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    user = getUser(rs);
                    list.add(user);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findByPage(int page, int rows) {
        try (Connection co = JdbcUtil.getConnection()) {
            List<User> list = new ArrayList<>();
            User user;
            try (PreparedStatement ps = co.prepareStatement("select * from rbac_user limit ?,?;")) {
               int a=(page-1)*rows;
                ps.setInt(1,a);
                ps.setInt(2,rows);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    user = getUser(rs);
                    list.add(user);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int findCount() {
        try (Connection co = JdbcUtil.getConnection()) {

            User user;
            try (PreparedStatement ps = co.prepareStatement("select count(*) from rbac_user;")) {
                ResultSet rs = ps.executeQuery();
                rs.next();
                return rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void insert(User user) {
        try (Connection co = JdbcUtil.getConnection()) {
            try (PreparedStatement psmt = co.prepareStatement("insert into rbac_user(username,password,org_id,org_ids) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                psmt.setString(1, user.getUsername());
                psmt.setString(2, user.getPassword());
                psmt.setInt(3, user.getOrgId());
                String orgIds = StringUtil.join(user.getOrgIds());
                psmt.setString(4, orgIds);
                psmt.executeUpdate();
                ResultSet generatedKeys = psmt.getGeneratedKeys();
                generatedKeys.next();
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try (Connection co = JdbcUtil.getConnection()) {
            try (PreparedStatement psmt = co.prepareStatement("update rbac_user set password=?, org_id=?, org_ids=? where id=?")) {
                psmt.setString(1, user.getPassword());
                psmt.setInt(2, user.getOrgId());
                String orgIds = StringUtil.join(user.getOrgIds());
                psmt.setString(3, orgIds);
                psmt.setInt(4, user.getId());
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int userId) {
        try (Connection co = JdbcUtil.getConnection()) {
            try (PreparedStatement psmt = co.prepareStatement("delete from rbac_user where id=?")) {
                psmt.setInt(1, userId);
                psmt.executeUpdate();
                deleteRole_User(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteRole_User(int userId){
        try (Connection co = JdbcUtil.getConnection()) {
            try (PreparedStatement psmt = co.prepareStatement("delete from rbac_user_role where user_id=?")) {
                psmt.setInt(1, userId);
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteUserRole(int userId) {
        String sql = "DELETE FROM RBAC_USER_ROLE WHERE USER_ID=?";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, userId);
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertUserRole(int userId, int roleId) {
        String sql = "INSERT INTO RBAC_USER_ROLE (USER_ID, ROLE_ID) VALUES (?,?)";
        try(Connection conn = JdbcUtil.getConnection()) {
            try(PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setInt(1, userId);
                psmt.setInt(2, roleId);
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(String username) {
        String sql = "select count(*) from rbac_user where username=?";
        try (Connection conn = JdbcUtil.getConnection()) {
            try (PreparedStatement psmt = conn.prepareStatement(sql)) {
                psmt.setString(1, username);
                ResultSet rs = psmt.executeQuery();
                rs.next();
                return rs.getInt(1) == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
