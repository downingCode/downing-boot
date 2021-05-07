package com.downing.boot;

import com.downing.boot.admin.service.ISysUserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author downing
 * @desc
 * @date 2020/7/21 10:09
 */
@SpringBootApplication
@MapperScan("com.downing.boot.mapper,com.downing.boot.admin.mapper")
@RestController
public class MainApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        int[] a = {1, 3, 5, 2};
        bubble(a);
    }

    public static void bubble(int[] datas) {
        for (int i = 0; i < datas.length; i++) {
            for (int j = 1; j < datas.length - 1; j++) {
                if (datas[i] < datas[j]) {
                    int temp = datas[i];
                    datas[i] = datas[j];
                    datas[j] = temp;
                }
            }
        }
        System.out.println(datas);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.printf("-------------MainApplication应用启动完成,开始执行初始化方法---------------------");
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
