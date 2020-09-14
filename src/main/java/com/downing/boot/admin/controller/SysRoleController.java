package com.downing.boot.admin.controller;


import com.downing.boot.admin.service.ISysRoleService;
import com.downing.boot.common.DowningResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author downing
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/admin/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService roleService;

    public DowningResult grantRole(){
        return new DowningResult();
    }
}

