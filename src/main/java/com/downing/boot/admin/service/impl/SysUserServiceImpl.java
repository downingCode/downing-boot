package com.downing.boot.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.downing.boot.admin.mapper.SysResourceMapper;
import com.downing.boot.admin.mapper.SysRoleMapper;
import com.downing.boot.admin.mapper.SysUserMapper;
import com.downing.boot.admin.service.ISysRoleService;
import com.downing.boot.entity.SysResource;
import com.downing.boot.entity.SysRole;
import com.downing.boot.entity.SysUser;
import com.downing.boot.admin.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author downing
 * @since 2020-09-03
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysResourceMapper resourceMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUser getLoginUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_account", name);
        //缓存中获取
        return userMapper.selectOne(wrapper);
    }

    @Override
    public Set<SysResource> getUserResource(Integer userId) {
        Set<SysResource> result = new HashSet<>();
        List<SysRole> userRoles = roleMapper.getUserRoles(userId);
        for (SysRole sysRole : userRoles) {
            List<SysResource> roleResources = resourceMapper.getRoleResources(sysRole.getId());
            result.addAll(roleResources);
        }
        return result;
    }
}
