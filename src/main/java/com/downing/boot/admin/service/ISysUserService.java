package com.downing.boot.admin.service;

import com.downing.boot.entity.SysResource;
import com.downing.boot.entity.SysRole;
import com.downing.boot.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author downing
 * @since 2020-09-03
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser getLoginUser();

    Set<SysResource> getUserResource(Integer userId);
}
