package com.aidijing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * jwt对象
 */
public class JwtUser implements UserDetails {

    private final Long                                     id;
    private final String                                   username;
    private final String                                   password;
    private final String                                   nickName;
    private final String                                   realName;
    private final String                                   email;
    private final String                                   phone;
    private final Date                                     lastPasswordResetDate;
    private final Long                                     createManagerAdminUserId;
    private final Date                                     createTime;
    private final Date                                     updateTime;
    private final String                                   remark;
    private final boolean                                  enabled;
    private final Collection< ? extends GrantedAuthority > authorities;


    public JwtUser ( Long id,
                     String username,
                     String password,
                     String nickName,
                     String realName,
                     String email,
                     String phone,
                     Date lastPasswordResetDate,
                     Long createManagerAdminUserId,
                     Date createTime,
                     Date updateTime,
                     String remark,
                     boolean enabled,
                     Date lastPasswordResetDate1,
                     Collection< ? extends GrantedAuthority > authorities ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.realName = realName;
        this.email = email;
        this.phone = phone;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.createManagerAdminUserId = createManagerAdminUserId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.remark = remark;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @JsonIgnore
    public Long getId () {
        return id;
    }

    public String getNickName () {
        return nickName;
    }


    public String getEmail () {
        return email;
    }

    public String getRealName () {
        return realName;
    }

    public String getPhone () {
        return phone;
    }

    @Override
    public String getUsername () {
        return username;
    }

    @Override
    public boolean isEnabled () {
        return enabled;
    }

    @Override
    public Collection< ? extends GrantedAuthority > getAuthorities () {
        return authorities;
    }

    @JsonIgnore
    public Long getCreateManagerAdminUserId () {
        return createManagerAdminUserId;
    }

    @JsonIgnore
    public Date getCreateTime () {
        return createTime;
    }

    @JsonIgnore
    public Date getUpdateTime () {
        return updateTime;
    }

    @JsonIgnore
    public String getRemark () {
        return remark;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired () {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked () {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword () {
        return password;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate () {
        return lastPasswordResetDate;
    }
}
