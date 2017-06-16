package com.aidijing.service;

import com.aidijing.domain.ManagerRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 后台管理角色表 服务类
 * </p>
 *
 * @author 披荆斩棘
 * @since 2017-06-15
 */
public interface ManagerRoleService extends IService< ManagerRole > {
    List< ManagerRole > getByUserId ( Long userId );
}
