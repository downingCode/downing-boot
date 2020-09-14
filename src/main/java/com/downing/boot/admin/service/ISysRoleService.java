package com.downing.boot.admin.service;

import com.downing.boot.entity.SysResource;
import com.downing.boot.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author downing
 * @since 2020-09-03
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 分配角色
     *
     * @param uid
     * @param roleIds
     */
    void grantUserRole(Integer uid, String roleIds);

}
