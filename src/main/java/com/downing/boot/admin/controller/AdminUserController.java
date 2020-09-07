package com.downing.boot.admin.controller;

import com.downing.boot.admin.service.ISysUserService;
import com.downing.boot.common.DowningResult;
import com.downing.boot.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author downing
 * @desc
 * @date 2020/9/5 17:38
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private ISysUserService userService;

    @GetMapping("/getResource")
    public DowningResult getUserResource(){
        SysUser loginUser = userService.getLoginUser();
        return new DowningResult("获取成功",userService.getUserResource(loginUser.getId()));
    }
}
