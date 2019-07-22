package com.itheima.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Auther:smallPebble
 * @Date:2019/7/20
 * @Description:com.itheima.ssm.utils
 * @version:1.0
 **/
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "123";
        System.out.println(BCryptPasswordEncoderUtils.encodePassword(password));
    }
}
