package com.downing.boot.admin.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * @author downing
 * @desc
 * @date 2020/9/4 10:23
 */
@Data
public class AddOrUpdateDeptDTO implements Serializable {

    private Integer id;

    private Integer pid;

    private String name;

    private String desc;

}
