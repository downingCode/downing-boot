package com.downing.web;

import com.downing.common.DowningResult;
import com.downing.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author downing
 * @descript
 */
@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DowningResult findById(@PathVariable("id") Integer id) {
        return new DowningResult(200, "获取成功", usersService.findById(id));
    }
}
