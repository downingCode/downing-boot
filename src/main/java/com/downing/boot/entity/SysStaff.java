package com.downing.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author downing
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysStaff implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer depId;

    private String fullName;

    private String nickName;

    private Integer gender;

    private String phone;

    private String email;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;


}
