package com.downing.boot.admin.controller;

import com.downing.boot.admin.service.ISysUserService;
import com.downing.boot.common.DowningResult;
import com.downing.boot.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    /**
     * 获取资源
     * @return
     */
    @GetMapping("/getResource")
    public DowningResult getUserResource(){
        SysUser loginUser = userService.getLoginUser();
        return new DowningResult("获取成功",userService.getUserResource(loginUser.getId()));
    }

    @GetMapping("/getName")
    public String getCurrentLoginName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
