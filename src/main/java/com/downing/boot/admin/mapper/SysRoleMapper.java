package com.downing.boot.admin.mapper;

import com.downing.boot.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author downing
 * @since 2020-09-03
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getUserRoles(Integer userId);
}
