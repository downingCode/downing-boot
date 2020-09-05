package com.downing.boot.admin.config;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.downing.boot.admin.mapper.SysRoleMapper;
import com.downing.boot.admin.mapper.SysUserMapper;
import com.downing.boot.admin.service.ISysRoleService;
import com.downing.boot.admin.service.ISysUserService;
import com.downing.boot.admin.service.impl.SysRoleServiceImpl;
import com.downing.boot.entity.SysResource;
import com.downing.boot.entity.SysRole;
import com.downing.boot.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 需要自定义UserDetailsService实现spring security的UserDetailsService接口
 *
 * @author downing
 * @date 2020年9月4日18:01:56
 */
@Service
public class CustomerDetailService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_account", username);
        SysUser user = sysUserMapper.selectOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SysRole> roles = sysRoleMapper.getUserRoles(user.getId());

        //将所有的角色全部放入user对应的grantedAuthority集合中
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (SysRole role : roles) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleCode());
            grantedAuthorities.add(grantedAuthority);
        }

        return new User(username, user.getPassword(), true, true, true, true, grantedAuthorities);
    }
}