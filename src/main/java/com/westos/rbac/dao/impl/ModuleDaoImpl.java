package com.westos.rbac.dao.impl;

import com.westos.rbac.dao.ModuleDao;
import com.westos.rbac.domain.Module;
import com.westos.rbac.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yihang
 */
public class ModuleDaoImpl implements ModuleDao {
    @Override
    public List<Module> findAll() {
        try(Connection co=JdbcUtil.getConnection()) {
            List<Module> listAll = new ArrayList<>();
            List<Module> list = new ArrayList<>();
            try(PreparedStatement statement =co.prepareStatement("select * from rbac_module;")){
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    Module module = getModule(rs);
                    listAll.add(module);
                }
                for(Module module:listAll){
                    int i=module.getId()/10;
                    if(i==0){
                        list.add(module);
                    }else{
                        for(Module module1:listAll){
                            if(module1.getId()==i){
                                module1.getChildren().add(module);
                            }
                        }
                    }

                }

                return  list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Module> findByRoleId(int roleId) {
        try(Connection co=JdbcUtil.getConnection()) {
            List<Module> list = new ArrayList<>();
            try(PreparedStatement statement =co.prepareStatement(" select distinct id ,name,pid,code from  rbac_role_module,rbac_module where role_id=? and id=module_id;")){
                statement.setInt(1,roleId);
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    Module module = getModule(rs);
                    list.add(module);
                }
                return  list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    private Module getModule(ResultSet rs) throws SQLException {
        Module module = new Module();
        module.setId(rs.getInt("ID"));
        module.setName(rs.getString("NAME"));
        module.setCode(rs.getString("CODE"));
        module.setPid(rs.getInt("PID"));
        return module;
    }

}
