/*
 * Copyright 2015-2020 uuzu.com All right reserved.
 */
package com.msun.jrest.jpa;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author zxc Apr 1, 2017 4:51:02 PM
 */
@Entity
@ApiModel
public class User {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "The database generated user ID")
    private Long     id;
    @ApiModelProperty(notes = "The user name")
    private String   name;
    @JsonIgnore
    private String   password;
    private String[] roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String... roles) {
        this.roles = roles;
    }
}
