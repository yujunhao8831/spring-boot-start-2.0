/**
 * Copyright (c) 2011-2016, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.aidijing.generator;

import com.aidijing.generator.config.MyAutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlGenerator {

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main ( String[] args ) {
        MyAutoGenerator mpg = new MyAutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir( "/Users/pijingzhanji/Desktop" );
        gc.setFileOverride( true );
        gc.setActiveRecord( true );// 开启 activeRecord 模式
        gc.setEnableCache( false );// XML 二级缓存
        gc.setBaseResultMap( true );// XML ResultMap
        gc.setBaseColumnList( false );// XML columList
        gc.setAuthor( "披荆斩棘" );

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
         gc.setServiceName("%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        mpg.setGlobalConfig( gc );

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType( DbType.MYSQL );
        dsc.setTypeConvert( new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert ( String fieldType ) {
                System.out.println( "转换类型：" + fieldType );
                if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                    return DbColumnType.BOOLEAN;    
                    
                }
                return super.processTypeConvert( fieldType );
            }
        } );
        dsc.setDriverName( "com.mysql.jdbc.Driver" );
        dsc.setUsername( "root" );
        dsc.setPassword( "root" );
        dsc.setUrl( "jdbc:mysql://127.0.0.1:3306/blog?characterEncoding=utf8" );
        mpg.setDataSource( dsc );
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名
        // strategy.setDbColumnUnderline(true);//全局下划线命名
         strategy.setTablePrefix( new String[]{ "manager_" } );// 此处可以修改为您的表前缀
        strategy.setNaming( NamingStrategy.underline_to_camel );// 表名生成策略
        // strategy.setInclude(new String[] { "user" }); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuilderModel(true);
        // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
        // strategy.setEntityLombokModel(true);
        // Boolean类型字段是否移除is前缀处理
        // strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setEntity( "domain" );
        pc.setParent( "com.aidijing" );// 自定义包路径
        pc.setController( "controller" );// 这里是控制器包名，默认 web
        mpg.setPackageInfo( pc );

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap () {
                Map< String, Object > map = new HashMap<>();
                map.put( "abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp" );
                this.setMap( map );
            }
        };
        List< FileOutConfig > focList = new ArrayList<>();
        focList.add( new FileOutConfig( "/templates/mapper.xml.vm" ) {
            @Override
            public String outputFile ( TableInfo tableInfo ) {
                return "/develop/code/xml/" + tableInfo.getEntityName() + ".xml";
            }
        } );
        cfg.setFileOutConfigList( focList );
        mpg.setCfg( cfg );

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
//        tc.setXml( null );
        mpg.setTemplate( tc );
         tc.setEntity( "templates/entity.java.vm" );
         tc.setController( "templates/controller.java.vm" );
         tc.setService( "templates/service.java.vm" );
         tc.setServiceImpl( "templates/serviceImpl.java.vm" );
        
        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
        // TemplateConfig tc = new TemplateConfig();
        // tc.setController("...");
        // tc.setEntity("...");
        // tc.setMapper("...");
        // tc.setXml("...");
        // tc.setService("...");
        // tc.setServiceImpl("...");
        // mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

        // 打印注入设置
        System.err.println( mpg.getCfg().getMap().get( "abc" ) );
    }

}
