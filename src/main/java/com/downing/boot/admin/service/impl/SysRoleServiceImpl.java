package com.downing.boot.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.downing.boot.admin.mapper.SysRoleMapper;
import com.downing.boot.admin.mapper.SysUserRoleMapper;
import com.downing.boot.admin.service.ISysUserService;
import com.downing.boot.entity.SysResource;
import com.downing.boot.entity.SysRole;
import com.downing.boot.admin.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.downing.boot.entity.SysUser;
import com.downing.boot.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author downing
 * @since 2020-09-03
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private ISysUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantUserRole(Integer uid, String roleIds) {
        String[] roleIdList = roleIds.split(",");
        if (roleIdList.length <= 0) {
            return;
        }
        QueryWrapper<SysUserRole> userRoleWrapper = new QueryWrapper<>();
        userRoleWrapper.eq("user_id", uid);
        userRoleMapper.delete(userRoleWrapper);
        SysUser loginUser = userService.getLoginUser();
        for (String rid : roleIdList) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(uid);
            userRole.setRoleId(Integer.valueOf(rid));
            userRole.setCreateTime(new Date());
            userRole.setCreateUser(loginUser.getId());
            userRoleMapper.insert(userRole);
        }
    }
}
