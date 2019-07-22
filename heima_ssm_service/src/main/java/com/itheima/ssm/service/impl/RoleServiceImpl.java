package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:smallPebble
 * @Date:2019/7/20
 * @Description:com.itheima.ssm.service.impl
 * @version:1.0
 **/
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(String roleid) throws Exception {
        return roleDao.findById(roleid);
    }

    @Override
    public List<Permission> findOtherPermission(String roleid) throws Exception {
        return roleDao.findOtherPermission(roleid);
    }

    @Override
    public void addPermissionToRole(String roleid, String[] ids) throws Exception {
        for(String permissionid:ids){
            roleDao.addPermissionToRole(roleid,permissionid);
        }
    }
}
