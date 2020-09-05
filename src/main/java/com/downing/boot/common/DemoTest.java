package com.downing.boot.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author downing
 * @desc 公共测试类
 * @date 2020/9/5 10:06
 */
public class DemoTest {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");
        System.out.println(encode);
    }
}
