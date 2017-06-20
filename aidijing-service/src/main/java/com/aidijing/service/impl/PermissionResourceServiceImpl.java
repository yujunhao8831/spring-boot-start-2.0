package com.aidijing.service.impl;

import com.aidijing.domain.PermissionResource;
import com.aidijing.mapper.PermissionResourceMapper;
import com.aidijing.service.PermissionResourceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;

/**
 * <p>
 * 后台管理权限资源表 服务实现类
 * </p>
 *
 * @author 披荆斩棘
 * @since 2017-06-19
 */
@Service
public class PermissionResourceServiceImpl extends ServiceImpl<PermissionResourceMapper, PermissionResource> implements PermissionResourceService {


    @Override
    public PageInfo listPage( PageRowBounds pageRowBounds ) {
        PageHelper.startPage( pageRowBounds.getOffset(), pageRowBounds.getLimit() );
        return new PageInfo( super.selectList( null ) );
    }
    
    
}
