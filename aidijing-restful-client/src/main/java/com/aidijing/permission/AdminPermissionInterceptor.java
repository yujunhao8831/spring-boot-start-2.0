package com.aidijing.permission;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 *
 * @author : 披荆斩棘
 * @date : 2017/6/14
 */
public class AdminPermissionInterceptor extends HandlerInterceptorAdapter {

    
    
    @Override
    public boolean preHandle ( HttpServletRequest request, HttpServletResponse response, Object handler ) throws
                                                                                                          Exception {

        
        if ( ! ( handler instanceof HandlerMethod ) ) {
            return false;
        }
        final HandlerMethod handlerMethod = ( HandlerMethod ) handler;
        // TODO: 2017/6/22  
        return true;


    }
}
