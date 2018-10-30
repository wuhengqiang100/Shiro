package com.xiaoshu.admin.entity;

import com.xiaoshu.common.base.DataEntity;

/**
* @Description:   属性认证实体类
* @Author:         wuhq
* @CreateDate:     2018/10/30 13:43
* @UpdateUser:     wuhq
* @UpdateDate:     2018/10/30 13:43
* @UpdateRemark:   
* @Version:        
*/

public class LoginUser extends DataEntity<LoginUser> {
    private String username;

    private String password;

    private String tel;

    private String  email;

    private String identity;

    private String sex;

    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "LoginUser{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", tel='" + tel + '\'' + ", email='" + email + '\'' + ", identity='" + identity + '\'' + ", sex='" + sex + '\'' + ", code='" + code + '\'' + '}';
    }
}
