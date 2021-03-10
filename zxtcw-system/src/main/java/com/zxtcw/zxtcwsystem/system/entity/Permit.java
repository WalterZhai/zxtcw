package com.zxtcw.zxtcwsystem.system.entity;


import com.zxtcw.starter.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @comment 权限实体
 * @author Walter(翟笑天)
 * @date 2021/3/10
 */
@Entity
@Table(name="SYS_PERMIT")
public class Permit extends BaseEntity {


    @Column(name = "CODE",columnDefinition = "VARCHAR(300) unique COMMENT '权限编码' ")
    private String code;
    @Column(name = "NAME",columnDefinition = "VARCHAR(30) COMMENT '权限名称' ")
    private String name;
    @Column(name = "DESCRIPTION",columnDefinition = "VARCHAR(200) COMMENT '权限用途说明' ")
    private String description;
    @ManyToMany(mappedBy = "permits")
    private List<Role> roles = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
