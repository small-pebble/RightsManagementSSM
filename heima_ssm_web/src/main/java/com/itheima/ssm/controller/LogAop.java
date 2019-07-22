package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Auther:smallPebble
 * @Date:2019/7/21
 * @Description:com.itheima.ssm.controller
 * @version:1.0
 **/
@Component
@Aspect
public class LogAop {

    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    private Date visitTime;//开始访问的时间
    private Class clazz;//访问的类
    private Method method;//访问的方法


    //前置通知
    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        //获取方法的名称
        String methodName = jp.getSignature().getName();
        //获取方法参数
        Object[] args = jp.getArgs();
        //获取Method方法对象
        if(args==null || args.length==0){
            method = clazz.getMethod(methodName);
        }else{
            Class[] methodArgs = new Class[args.length];
            for(int i=0;i<args.length;i++){
                methodArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,methodArgs);
        }
    }

    //后置通知
    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //获取访问时长
        long time = new Date().getTime()-visitTime.getTime();
        //获取url
        String url = "";
        if(clazz!=null && method!=null && clazz!=LogAop.class){
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                String[] classValue = classAnnotation.value();
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0]+methodValue[0];
                }
            }
        }
        //获取访问地址
        String ip = request.getRemoteAddr();
        //获取访问用户名
        SecurityContext context = SecurityContextHolder.getContext();//获取Security上下文对象
        User principal = (User) context.getAuthentication().getPrincipal();
        String username = principal.getUsername();

        //封装日志对象
        SysLog sysLog = new SysLog();
        sysLog.setVisitTime(visitTime);
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名 ]"+clazz.getName()+"[方法名 ]"+method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);

        sysLogService.save(sysLog);
    }
}
