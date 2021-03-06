package com.downing.boot.admin.service;

import com.downing.boot.entity.SysResource;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author downing
 * @since 2020-09-03
 */
public interface ISysResourceService extends IService<SysResource> {

    /**
     * 分配角色资源
     *
     * @param roleId
     * @param resourceIds
     */
    void grantRoleResource(Integer roleId, String resourceIds);
}
