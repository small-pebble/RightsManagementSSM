package com.itheima.ssm.service;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

/**
 * @Auther:smallPebble
 * @Date:2019/7/21
 * @Description:com.itheima.ssm.service
 * @version:1.0
 **/

public interface ISysLogService {

    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll() throws  Exception;
}
