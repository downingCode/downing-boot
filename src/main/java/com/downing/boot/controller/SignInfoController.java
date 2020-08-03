package com.downing.boot.controller;

import com.downing.boot.common.DowningResult;
import com.downing.boot.service.activity.SignInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author downing
 * @desc
 * @date 2020/7/22 11:05
 */
@RestController
@RequestMapping("api/sign")
public class SignInfoController {

    @Autowired
    private SignInfoService signInfoService;

    @PostMapping("/")
    public DowningResult sign() throws Exception {
        return new DowningResult("操作成功",signInfoService.signIn());
    }
}
