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
public class SysUser implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer staffId;

    private String loginAccount;

    private String password;

    private Integer phone;

    private String email;

    private String remark;

    private Boolean forbitFlag;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;


}
