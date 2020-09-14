package com.downing.boot.admin.service.impl;

import com.downing.boot.admin.mapper.SysResourceMapper;
import com.downing.boot.entity.SysResource;
import com.downing.boot.admin.service.ISysResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author downing
 * @since 2020-09-03
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {

    @Override
    public void grantRoleResource(Integer roleId, String resourceIds) {

    }
}
