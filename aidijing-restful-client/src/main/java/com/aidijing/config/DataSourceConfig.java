package com.aidijing.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @author : 披荆斩棘
 * @date : 2017/5/10
 */
@Configuration
@EnableConfigurationProperties( MybatisProperties.class )
@MapperScan( basePackages = "com.aidijing.mapper" )
public class DataSourceConfig {

    @Autowired
    private MybatisProperties properties;
    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    @Autowired( required = false )
    private Interceptor[]  interceptors   = new Interceptor[]{
            new PerformanceInterceptor()
    };
    @Autowired( required = false )
    private DatabaseIdProvider databaseIdProvider;



    @Bean
    @ConfigurationProperties( prefix = "druid" )
    public DataSource dataSource () {
        return DataSourceBuilder.create().type( DruidDataSource.class ).build();
    }


    /**
     * 这里全部使用mybatis-autoconfigure 已经自动加载的资源。不手动指定
     * 配置文件和mybatis-boot的配置文件同步
     *
     * @return
     */
    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean () {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource( dataSource() );
        mybatisPlus.setVfs( SpringBootVFS.class );
        if ( StringUtils.hasText( this.properties.getConfigLocation() ) ) {
            mybatisPlus.setConfigLocation( this.resourceLoader.getResource( this.properties.getConfigLocation() ) );
        }
        mybatisPlus.setConfiguration( properties.getConfiguration() );
        if ( ! ObjectUtils.isEmpty( this.interceptors ) ) {
            mybatisPlus.setPlugins( this.interceptors );
        }
        // MP 全局配置，更多内容进入类看注释
        GlobalConfiguration globalConfig = new GlobalConfiguration();
        globalConfig.setDbType( DBType.MYSQL.name() );//数据库类型
        // ID 策略 AUTO->`0`("数据库ID自增") INPUT->`1`(用户输入ID") ID_WORKER->`2`("全局唯一ID") UUID->`3`("全局唯一ID")
        globalConfig.setIdType( 0 );
        //MP 属性下划线 转 驼峰 , 如果原生配置 mc.setMapUnderscoreToCamelCase(true) 开启，该配置可以无。
        // globalConfig.setDbColumnUnderline(true);
        mybatisPlus.setGlobalConfig( globalConfig );
        MybatisConfiguration mc = new MybatisConfiguration();
        // 对于完全自定义的mapper需要加此项配置，才能实现下划线转驼峰
        mc.setMapUnderscoreToCamelCase( true );
        mc.setDefaultScriptingLanguage( MybatisXMLLanguageDriver.class );
        mybatisPlus.setConfiguration( mc );
        if ( this.databaseIdProvider != null ) {
            mybatisPlus.setDatabaseIdProvider( this.databaseIdProvider );
        }
        if ( StringUtils.hasLength( this.properties.getTypeAliasesPackage() ) ) {
            mybatisPlus.setTypeAliasesPackage( this.properties.getTypeAliasesPackage() );
        }
        if ( StringUtils.hasLength( this.properties.getTypeHandlersPackage() ) ) {
            mybatisPlus.setTypeHandlersPackage( this.properties.getTypeHandlersPackage() );
        }
        if ( ! ObjectUtils.isEmpty( this.properties.resolveMapperLocations() ) ) {
            mybatisPlus.setMapperLocations( this.properties.resolveMapperLocations() );
        }
        return mybatisPlus;
    }
    
    
    
    
    
    
}
