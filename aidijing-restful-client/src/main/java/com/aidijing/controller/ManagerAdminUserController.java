package com.aidijing.controller;

import com.aidijing.common.ResponseEntity;
import com.aidijing.common.util.LogUtils;
import com.aidijing.domain.ManagerAdminUser;
import com.aidijing.service.ManagerAdminUserService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 后台管理用户 前端控制器
 * </p>
 *
 * @author 披荆斩棘
 * @since 2017-06-15
 */
@RestController
@RequestMapping("/manager-admin-user")
public class ManagerAdminUserController {
    @Autowired
    private ManagerAdminUserService managerAdminUserService;
    @Autowired
    private RedissonClient          redissonClient;


    @GetMapping
    public ResponseEntity< PageInfo > listPage ( PageRowBounds pageRowBounds ) {
        return ResponseEntity.ok().setResponseContent( managerAdminUserService.listPage( pageRowBounds ) );
    }


    @GetMapping( "distributed-lock" )
    public ResponseEntity distributedLock () throws InterruptedException {
        int timeout = 10;
        // 分布式锁
        final RLock lock = redissonClient.getLock( this.getClass().getSimpleName() + "LOCK" );
        lock.lock();

        for ( int i = 0 ; i < timeout ; i++ ) {
            TimeUnit.SECONDS.sleep( 1 );
            LogUtils.getLogger().warn( "lock ing ... ... ... " );
        }
        // N 时间后后解锁
        lock.unlock();
        LogUtils.getLogger().warn( "unlock" );
        return ResponseEntity.ok( "success" );
    }


    @GetMapping( "async" )
    public ResponseEntity asyncUpdate () {
        // 异步操作  
        managerAdminUserService.asyncUpdate();
        return ResponseEntity.ok( "success" );
    }


    @GetMapping( "list" )
    public ResponseEntity< List< ManagerAdminUser > > listPageCache () {
        return ResponseEntity.ok().setResponseContent( managerAdminUserService.list() );
    }


    @GetMapping( "{id}" )
    public ResponseEntity< ManagerAdminUser > get ( @PathVariable Long id ) {
        return ResponseEntity.ok().setResponseContent( managerAdminUserService.get( id ) );
    }

    @PutMapping( "{id}" )
    public ResponseEntity update ( @PathVariable Long id,
                                   @RequestBody ManagerAdminUser user ) {
        new ManagerAdminUser();
          
        managerAdminUserService.update( user.setId( id ) );
        return ResponseEntity.ok();
    }

    @PostMapping
    public ResponseEntity save ( @RequestBody ManagerAdminUser user ) {
        managerAdminUserService.save( user );
        return ResponseEntity.ok();
    }

    @DeleteMapping( "{id}" )
    public ResponseEntity delete ( @PathVariable Long id ) {
        managerAdminUserService.delete( id );
        return ResponseEntity.ok();
    }

   

    
}
