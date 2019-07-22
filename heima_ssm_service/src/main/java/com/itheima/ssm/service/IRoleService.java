package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

/**
 * @Auther:smallPebble
 * @Date:2019/7/20
 * @Description:com.itheima.ssm.service
 * @version:1.0
 **/

public interface IRoleService {

    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(String roleid) throws Exception;

    List<Permission> findOtherPermission(String roleid) throws Exception;

    void addPermissionToRole(String roleid, String[] ids) throws Exception;
}
