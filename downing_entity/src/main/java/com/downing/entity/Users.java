package com.downing.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author downing
 * @descript
 */
@Data
@ToString
public class Users {

    private Integer id;
    private String username;
    private String password;
    private Date createTime;
}
