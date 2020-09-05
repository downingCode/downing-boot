package com.downing.boot.admin.service.impl;

import com.downing.boot.admin.dto.AddOrUpdateDeptDTO;
import com.downing.boot.admin.mapper.SysDepartmentMapper;
import com.downing.boot.entity.SysDepartment;
import com.downing.boot.admin.service.ISysDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author downing
 * @since 2020-09-03
 */
@Service
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentMapper, SysDepartment> implements ISysDepartmentService {

    @Autowired
    private SysDepartmentMapper departmentMapper;

    public void addOrUpdate(AddOrUpdateDeptDTO dto) {
        Date currentDate = Calendar.getInstance().getTime();
        SysDepartment sysDepartment = new SysDepartment();
        sysDepartment.setId(dto.getId());
        sysDepartment.setPid(dto.getPid());
        sysDepartment.setName(dto.getName());
        sysDepartment.setDesc(dto.getDesc());
        sysDepartment.setUpdateTime(currentDate);
        //todo 用户信息
        if (dto.getId() != null) {
            departmentMapper.updateById(sysDepartment);
        }else {
            sysDepartment.setCreateTime(currentDate);
            departmentMapper.insert(sysDepartment);
        }
    }
}
