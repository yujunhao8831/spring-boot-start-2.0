package com.aidijing.service.impl;

import com.aidijing.domain.ManagerAdminUser;
import com.aidijing.mapper.ManagerAdminUserMapper;
import com.aidijing.service.ManagerAdminUserService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 后台管理用户 服务实现类
 * </p>
 *
 * @author 披荆斩棘
 * @since 2017-06-15
 */
@Service
public class ManagerAdminUserServiceImpl extends ServiceImpl< ManagerAdminUserMapper, ManagerAdminUser > implements ManagerAdminUserService {
    /**
     * 用户分页在缓存中存储名称key的前缀
     */
    private static final String CACHE_ManagerAdminUser_LIST_PAGE_NAME_PREFIX = "ManagerAdminUserService.ManagerAdminUser.list.namespace";


    /**
     * SpEL表达式 : T(完整的类名),因为key只能是String类型,下面的key使用SpEL表达式进行了转换
     * 这里
     *
     * @param pageRowBounds
     * @return
     */
    @Cacheable( value = CACHE_ManagerAdminUser_LIST_PAGE_NAME_PREFIX, key = "#pageRowBounds.offset + '.' + #pageRowBounds.getLimit()" )
    @Override
    public PageInfo listPage ( PageRowBounds pageRowBounds ) {
        PageHelper.startPage( pageRowBounds.getOffset(), pageRowBounds.getLimit() );
        return new PageInfo( super.selectList( null ) );
    }

    @Cacheable( value = CACHE_ManagerAdminUser_LIST_PAGE_NAME_PREFIX, key = "#root.methodName" )
    @Override
    public List< ManagerAdminUser > list () {
        return super.selectList( null );
    }

    @Cacheable( key = "T(java.lang.String).valueOf(#id)" )
    @Override
    public ManagerAdminUser get ( Long id ) {
        return super.selectById( id );
    }


    @Caching(
            evict = {
                    @CacheEvict( value = CACHE_ManagerAdminUser_LIST_PAGE_NAME_PREFIX, allEntries = true )
            },
            put = {
                    @CachePut( key = "T(java.lang.String).valueOf(#ManagerAdminUser.id)", condition = "#result != null" )
            }
    )
    @Override
    public ManagerAdminUser update ( ManagerAdminUser ManagerAdminUser ) {
        if ( ! super.updateById( ManagerAdminUser ) ) {
            return null;
        }
        return super.selectById( ManagerAdminUser.getId() );
    }


    @Caching(
            evict = {
                    @CacheEvict( value = CACHE_ManagerAdminUser_LIST_PAGE_NAME_PREFIX, allEntries = true )
            },
            put = {
                    @CachePut( key = "T(java.lang.String).valueOf(#ManagerAdminUser.id)", condition = "#result != null" )
            }
    )
    @Override
    public ManagerAdminUser save ( ManagerAdminUser ManagerAdminUser ) {
        if ( ! super.insert( ManagerAdminUser ) ) {
            return null;
        }
        return ManagerAdminUser;
    }

    @Caching( evict = {
            @CacheEvict( value = CACHE_ManagerAdminUser_LIST_PAGE_NAME_PREFIX, allEntries = true ) ,
            @CacheEvict( key = "T(java.lang.String).valueOf(#id)" )

    } )
    @Override
    public boolean delete ( Long id ) {
        return super.deleteById( id );
    }

    @Async
    @Override
    public ListenableFuture< Boolean > asyncUpdate () {
        return new AsyncResult( this.sleepUpdate( 3 ) );
    }

    private Boolean sleepUpdate ( int second ) {
        try {
            TimeUnit.SECONDS.sleep( second );
        } catch ( InterruptedException e ) {
        }
        final List< ManagerAdminUser > ManagerAdminUsers = super.selectList( null );
        ManagerAdminUsers.forEach( ManagerAdminUser -> ManagerAdminUser.setUpdateTime( Calendar.getInstance()
                                                                                               .getTime() ) );
        return super.updateBatchById( ManagerAdminUsers );
    }

    @Override
    public ManagerAdminUser findByUsername ( String ManagerAdminUsername ) {
        return this.selectOne( new Condition().eq( "username", ManagerAdminUsername ) );
    }

}
