package com.aidijing;

import com.aidijing.domain.User;
import com.aidijing.model.JwtUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 上下文
 *
 * @author : 披荆斩棘
 * @date : 2017/6/20
 */
public abstract class ContextUtils {


    /**
     * 是否登录
     * {@link Authentication#isAuthenticated()}
     *
     * @return <code>true</code> , 只要授权后,不管成功或失败,都会返回<code>true</code>. 但是如果授权失败,这次请求直接会被拒绝
     */
    public boolean isLogin () {
        return getAuthentication().isAuthenticated();
    }

    /**
     * 得到jwt对象
     *
     * @return
     */
    public JwtUser getJwtUser () {
        return ( JwtUser ) getAuthentication().getPrincipal();
    }


    /**
     * 得到User对象
     *
     * @return
     */
    public User getUser () {
        final JwtUser jwtUser = getJwtUser();
        User          user    = new User();
        BeanUtils.copyProperties( jwtUser, user );
        return user;
    }

    /**
     * 得到用户id
     *
     * @return {@link User#id}
     */
    public Long getUserId () {
        return getJwtUser().getId();
    }

    /**
     * 得到用户详细信息
     *
     * @return {@link UserDetails}
     */
    public UserDetails getUserDetails () {
        return ( UserDetails ) getAuthentication().getPrincipal();
    }


    /**
     * 得到凭证
     */
    private Authentication getAuthentication () {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( null == authentication ) {
            throw new AuthenticationCredentialsNotFoundException( "未授权" );
        }
        return authentication;
    }

}
