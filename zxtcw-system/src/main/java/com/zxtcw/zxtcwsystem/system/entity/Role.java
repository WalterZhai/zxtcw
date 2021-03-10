package com.zxtcw.zxtcwsystem.system.entity;


import com.zxtcw.starter.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @comment 角色实体
 * @author Walter(翟笑天)
 * @date 2021/3/10
 */
@Entity
@Table(name="SYS_ROLE")
public class Role extends BaseEntity {

    @Column(name = "CODE",columnDefinition = "VARCHAR(30) unique COMMENT '角色编码，必须以ROLE_开头' ")
    private String code;
    @Column(name = "NAME",columnDefinition = "VARCHAR(30) COMMENT '角色名称' ")
    private String name;
    @Column(name = "DESCRIPTION",columnDefinition = "VARCHAR(200) COMMENT '角色用途说明' ")
    private String description;
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();
    @ManyToMany(targetEntity = Permit.class, cascade = CascadeType.ALL)
    @JoinTable(name = "SYS_ROLE_PERMIT",joinColumns = @JoinColumn(name = "ROLE_ID"),inverseJoinColumns = @JoinColumn(name = "PERMIT_ID"))
    private List<Permit> permits = new ArrayList<>();

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Permit> getPermits() {
        return permits;
    }

    public void setPermits(List<Permit> permits) {
        this.permits = permits;
    }
}
