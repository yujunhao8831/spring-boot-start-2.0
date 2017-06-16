package com.aidijing.service.impl;

import com.aidijing.domain.ManagerRole;
import com.aidijing.mapper.ManagerRoleMapper;
import com.aidijing.service.ManagerRoleService;
import com.aidijing.service.ManagerUserRoleService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台管理角色表 服务实现类
 * </p>
 *
 * @author 披荆斩棘
 * @since 2017-06-15
 */
@Service
public class ManagerRoleServiceImpl extends ServiceImpl< ManagerRoleMapper, ManagerRole > implements ManagerRoleService {
    @Autowired
    private ManagerUserRoleService managerUserRoleService;

    @Override
    public List< ManagerRole > getByUserId ( Long userId ) {
        final List< Long > roleIds = managerUserRoleService
                .selectObjs( new Condition().eq( "manager_admin_user_id", userId ).setSqlSelect( "id" ) );

        if ( CollectionUtils.isEmpty( roleIds ) ) {
            return null;
        }
        return this.selectBatchIds( roleIds );
    }
}
