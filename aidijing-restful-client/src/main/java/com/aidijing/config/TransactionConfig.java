package com.aidijing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author : 披荆斩棘
 * @date : 2017/5/10
 */
@Configuration
@ImportResource("classpath:applicationContext-tx.xml")
public class TransactionConfig {
    // 声明式事务未转化为JavaConfig配置方式,后续需要修改 
    
    /*@Bean
    public TransactionProxyFactoryBean transactionProxyFactoryBean ( PlatformTransactionManager platformTransactionManager ) {
        TransactionProxyFactoryBean transactionProxyFactoryBean = new TransactionProxyFactoryBean();
        transactionProxyFactoryBean.setTransactionManager( platformTransactionManager );
        transactionProxyFactoryBean.setTarget( UserServiceImpl.class );
        transactionProxyFactoryBean.setPointcut(  );
        return transactionProxyFactoryBean;
    }*/


   /* @Bean( "transactionInterceptor" )
    public TransactionInterceptor transactionInterceptor ( @Qualifier( "transactionManager" ) PlatformTransactionManager platformTransactionManager ) {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionManager( platformTransactionManager );
        Properties transactionAttributes = new Properties();
        transactionAttributes.setProperty( "save*", "PROPAGATION_REQUIRED,-Throwable" );
        transactionAttributes.setProperty( "insert*", "PROPAGATION_REQUIRED,-Throwable" );
        transactionAttributes.setProperty( "update*", "PROPAGATION_REQUIRED,-Throwable" );
        transactionAttributes.setProperty( "delete*", "PROPAGATION_REQUIRED,-Throwable" );
        transactionAttributes.setProperty( "select*", "PROPAGATION_REQUIRED,-Throwable,readOnly" );
        transactionInterceptor.setTransactionAttributes( transactionAttributes );
        return transactionInterceptor;
    }

    @Bean
    public BeanNameAutoProxyCreator transactionAutoProxy () {
        BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
        transactionAutoProxy.setProxyTargetClass( true );
        transactionAutoProxy.setBeanNames( "*ServiceImpl" );
        transactionAutoProxy.setInterceptorNames( "transactionInterceptor" );
        return transactionAutoProxy;
    }*/
/*
    @Bean
    public PlatformTransactionManager txManager( @Qualifier( "dataSource" ) DataSource dataSource) {
        return new DataSourceTransactionManager( dataSource);
    }


    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager () {
        return txManager(  );
    }*/
}
