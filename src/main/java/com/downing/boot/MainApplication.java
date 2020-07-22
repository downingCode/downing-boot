package com.downing.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author downing
 * @desc
 * @date 2020/7/21 10:09
 */
@SpringBootApplication
@MapperScan("com.downing.boot.mapper")
public class MainApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.printf("-------------MainApplication应用启动完成,开始执行初始化方法---------------------");
    }
}
