package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther:smallPebble
 * @Date:2019/7/20
 * @Description:com.itheima.ssm.dao
 * @version:1.0
 **/
@Repository
public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column="roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions", column = "id",javaType = java.util.List.class,many = @Many(select="com.itheima.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id=#{roleid}")
    Role findById(String roleid) throws Exception;

    @Select("select * from Permission where id not in(select permissionid from role_permission where roleid=#{roleid})")
    List<Permission> findOtherPermission(String roleid) throws Exception;

    @Insert("insert into role_permission(roleid,permissionid) values(#{roleid},#{permissionid})")
    void addPermissionToRole(@Param("roleid") String roleid, @Param("permissionid") String permissionid) throws Exception;
}
