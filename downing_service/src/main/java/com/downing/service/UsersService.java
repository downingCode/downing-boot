package com.downing.service;

import com.downing.common.AuthorizedException;
import com.downing.dao.UsersMapper;
import com.downing.entity.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据用户查找用户
     *
     * @return
     */
    public Users findUserByUsername(String username) {
        Map<String, Object> param = new HashMap<>(1);
        param.put("username", username);
        List<Users> usersList = usersMapper.selectByMap(param);
        if (usersList.size() == 0) {
            throw new AuthorizedException("用户名或密码错误");
        }
        return usersList.get(0);
    }
}
