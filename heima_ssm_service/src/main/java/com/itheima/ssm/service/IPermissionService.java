package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;

import java.util.List;

/**
 * @Auther:smallPebble
 * @Date:2019/7/20
 * @Description:com.itheima.ssm.service
 * @version:1.0
 **/
public interface IPermissionService {

    List<Permission> findAll() throws Exception;

    void save(Permission permission)throws Exception;
}
