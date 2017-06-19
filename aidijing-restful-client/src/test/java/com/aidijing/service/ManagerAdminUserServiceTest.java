package com.aidijing.service;

import com.aidijing.domain.ManagerAdminUser;
import com.aidijing.domain.ManagerPermissionResource;
import com.aidijing.domain.enums.ManagerPermissionResourceEnum;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RunWith( SpringRunner.class )
@SpringBootTest
public class ManagerAdminUserServiceTest {

    @Autowired
    private ManagerAdminUserService          managerAdminUserService;
    @Autowired
    private PasswordEncoder                  passwordEncoder;
    @Autowired
    private ManagerPermissionResourceService managerPermissionResourceService;


    @Test
    public void managerAdminUserServiceTest () throws Exception {
        // # insert 
        // ## 1. 选择插入,如果某个字段为空,那么这个字段则不会进插入
        managerAdminUserService.insert(
                new ManagerAdminUser()
                        .setEnabled( true )
                        .setPassword( passwordEncoder.encode( "123456" ) )
                        .setNickName( "披荆斩棘" )
                        .setLastPasswordResetDate( Calendar.getInstance().getTime() )
                        .setUsername( "yujunhao" )
        );
        
        // ## 2. 
        

        List< ManagerAdminUser > users = new ArrayList<>( 50 );
        for ( int i = 0 ; i < 50 ; i++ ) {
            users.add( new ManagerAdminUser()
                               .setEnabled( true )
                               .setPassword( passwordEncoder.encode( "123456" ) )
                               .setNickName( "披荆斩棘" + i )
                               .setLastPasswordResetDate( Calendar.getInstance().getTime() )
                               .setUsername( "yujunhao" + i ) );
        }

        managerAdminUserService.insertBatch( users );
        users.clear();
        users = managerAdminUserService.selectList( null );

        users.forEach( user -> user.setRemark( "update" ) );

        managerAdminUserService.updateBatchById( users );


        final Page< ManagerAdminUser > page = managerAdminUserService.selectPage( new Page<>( 1, 20 ) );

        System.err.println( "page = " + page );


        PageHelper.startPage( 1, 20 );
        final PageInfo< ManagerAdminUser > pageInfo = new PageInfo<>( managerAdminUserService.selectList( null ) );

        System.err.println( "pageInfo = " + pageInfo );

        final ManagerAdminUser user = managerAdminUserService.selectOne( new Condition().eq( "id", 1L ) );

        System.err.println( "user = " + user );

        final List queryResult = managerAdminUserService.selectList( new Condition().like( "username", "披荆斩棘" ) );

        System.err.println( "queryResult = " + queryResult );

        managerAdminUserService.update( user, new Condition().eq( "id", 1L ).and( "username = {0}", "披荆斩棘" ) );


        final List< Long > ids = users.parallelStream()
                                      .map( ManagerAdminUser::getId )
                                      .collect( Collectors.toList() );

        managerAdminUserService.deleteBatchIds( ids );


        final List< ManagerAdminUser > all = managerAdminUserService.selectList( null );

        System.err.println( "all = " + all );


    }


    @Test
    public void name2 () throws Exception {
        ManagerPermissionResource resource = new ManagerPermissionResource();

        resource.setParentId( 0L )
                .setPermissionName( "test" )
                .setResourceType( ManagerPermissionResourceEnum.ResourceType.API );
        managerPermissionResourceService.insert( resource );

        resource = managerPermissionResourceService.selectById( 1L );
        
        System.err.println( "resource = " + resource );

        managerPermissionResourceService.updateById( resource.setResourceType( ManagerPermissionResourceEnum.ResourceType.BUTTON ) );

        resource = managerPermissionResourceService.selectById( 1L );

        System.err.println( "resource = " + resource );
        

    }
}








