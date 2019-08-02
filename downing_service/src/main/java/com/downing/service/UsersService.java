package com.downing.service;

import com.downing.dao.UsersMapper;
import com.downing.entity.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author downing
 * @descript
 */
@Service
public class UsersService {

    @Resource
    private UsersMapper usersMapper;

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    public Users findById(int id) {
        return usersMapper.selectById(id);
    }
}
