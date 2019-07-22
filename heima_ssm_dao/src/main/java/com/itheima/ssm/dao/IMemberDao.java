package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther:smallPebble
 * @Date:2019/7/19
 * @Description:com.itheima.ssm.dao
 * @version:1.0
 **/
public interface IMemberDao {
    @Select("select * from member where id=#{id}")
    Member findById(String id) throws Exception;
}
