# Spring Boot 基础骨架
https://yujunhao8831.github.io/

算是个人的记录,几年来做项目是会遇到的问题,这里会得到解决.当然还是有很多不足

没有整合消息队列,不想东西太多了,想要整合可以看 https://github.com/spring-projects/spring-amqp-samples

分布式服务之间调用,这里没有用到,后续会用Spring Cloud解决.

项目可以打成jar部署,也可以打成war部署,默认使用jar

``` xml
<groupId>com.aidijing</groupId>
    <artifactId>aidijing-restful-client</artifactId>
    <!--<packaging>war</packaging>-->
<packaging>jar</packaging>
```

也可以用Docker部署,要求
 + 本地含有Docker,启动状态
 + Docker包含JDK1.8,如没有安装即可
 ``` shell
 docker pull java
 ```
 
maven打包命令 : mvn clean package docker:build
 
docker运行命令 : docker run -p 8080:8080 -t com.aidijing/aidijing-restful-client # (默认情况打包后的docker镜像名称是这样)
 


项目所用技术
 + Spring 
 + Spring MVC 
 + Spring Session
 + Spring Cache
 + Mybatis Plus 
 + Redis 
 + Swagger
 + Pagehelper
 + Druid
 + Log4j2

项目包含了
 + 分布式锁
 + 分布式唯一code构建
 + 分布式session共享
 + 异步处理
 + Aop缓存
 + 基础CRUD
 + 物理分页
 + 异步日志,多线程下用户的会话跟踪
 + Swagger restful api
 + 异常统一处理
 + Cors解决跨域
 + 注入攻击过滤器
 + 多环境配置
 + 基本工具类
 
要求 : 
 + JDK 1.8 
 + redis 默认使用 127.0.0.1:6379
 + mysql root/root 127.0.0.1:3306 数据库名称 : blog,基础sql见init.sql
 + lombok 插件(eclipse IntelliJ IDEA),不然项目可能会报错,但是不影响运行
 
## aidijing-parent
+ 版本管理

## aidijing-common 
+ 工具

## aidijing-service
**pom.xml**中所有的依赖都是
``` xml
<scope>provided</scope>
```


+ 服务接口


## aidijing-dao
**pom.xml**中所有的依赖都是
``` xml
<scope>provided</scope>
```
+ dao与数据库交互

## aidijing-basic-config
+ 基础配置(暂时未用到)

## aidijing-restful-client
+ 控制器,发布http restful接口


#### 后续完成安全控制