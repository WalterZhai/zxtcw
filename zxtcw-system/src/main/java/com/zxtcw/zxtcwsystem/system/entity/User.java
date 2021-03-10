package com.zxtcw.zxtcwsystem.system.entity;


import com.zxtcw.starter.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @comment 用户实体
 * @author Walter(翟笑天)
 * @date 2021/3/10
 */
@Entity
@Table(name="SYS_USER")
public class User extends BaseEntity {

    @Column(name = "LOGIN_NAME",columnDefinition = "VARCHAR(30) COMMENT '登录名' ")
    private String loginName;
    @Column(name = "PASSWORD",columnDefinition = "VARCHAR(30) COMMENT '密码' ")
    private String password;
    @Column(name = "IS_LOCKED",columnDefinition = "decimal(1) default 0 COMMENT '锁定状态：0-未锁定；1-已锁定' ")
    private Integer isLocked;
    @Column(name = "NAME",columnDefinition = "VARCHAR(30) COMMENT '用户名' ")
    private String name;
    @Column(name = "EMAIL",columnDefinition = "VARCHAR(30) COMMENT '电子邮箱' ")
    private String email;
    @Column(name = "MOBILE",columnDefinition = "VARCHAR(20) COMMENT '手机' ")
    private String mobile;
    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL)
    @JoinTable(name = "SYS_USER_ROLE",joinColumns = @JoinColumn(name = "USER_ID"),inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private List<Role> roles = new ArrayList<>();


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
