package com.downing.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
public class SysResource implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer pid;

    /**
     * 资源名
     */
    private String name;

    /**
     * 资源路径
     */
    private String url;

    private String logo;

    /**
     * 资源层级
     */
    private Integer level;

    /**
     * 是否显示
     */
    private Boolean showFlag;

    private Integer createUser;

    private LocalDateTime createTime;

    private Integer updateUser;

    private LocalDateTime updateTime;


}
