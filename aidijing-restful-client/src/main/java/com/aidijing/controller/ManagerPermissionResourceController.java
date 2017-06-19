package com.aidijing.controller;

import com.aidijing.common.ResponseEntity;
import com.aidijing.domain.ManagerPermissionResource;
import com.aidijing.domain.enums.ManagerPermissionResourceEnum;
import com.aidijing.service.ManagerPermissionResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台管理权限资源表 前端控制器
 * </p>
 *
 * @author 披荆斩棘
 * @since 2017-06-15
 */
@RestController
public class ManagerPermissionResourceController {

    @Autowired
    private ManagerPermissionResourceService managerPermissionResourceService;

    @GetMapping( "ManagerPermissionResource/test" )
    public ResponseEntity< ManagerPermissionResource > get () {
        ManagerPermissionResource resource = new ManagerPermissionResource();

        resource.setParentId( 0L )
                .setPermissionName( "test" )
                .setResourceType( ManagerPermissionResourceEnum.ResourceType.API );
        managerPermissionResourceService.insert( resource );
        return ResponseEntity.ok( "success" );
    }

    @GetMapping( "ManagerPermissionResource/test2" )
    public ResponseEntity< ManagerPermissionResource > get2 () {
        return ResponseEntity.ok( "success" ).setResponseContent( managerPermissionResourceService.selectById( 1L ) );
    }

    @GetMapping( "ManagerPermissionResource/test3" )
    public ResponseEntity< ManagerPermissionResource > get3 () {
        return ResponseEntity.ok( "success" )
                             .setResponseContent( managerPermissionResourceService
                                                          .updateById( new ManagerPermissionResource().setId( 1L )
                                                          .setResourceType( ManagerPermissionResourceEnum.ResourceType.BUTTON )) );
    }

}
