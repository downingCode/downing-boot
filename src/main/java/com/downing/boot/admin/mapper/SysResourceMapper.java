package com.downing.boot.admin.mapper;

import com.downing.boot.entity.SysResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.downing.boot.entity.SysRole;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author downing
 * @since 2020-09-03
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    List<SysResource> getRoleResources(Integer roleId);
}
