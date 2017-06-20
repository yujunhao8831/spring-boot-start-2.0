drop table if exists manager_permission_resource;

drop table if exists manager_role;

drop table if exists manager_role_permission_resource;

drop index manager_admin_user_username_uk_index on manager_user;

drop table if exists manager_user;

drop table if exists manager_user_action_history;

drop table if exists manager_user_role;

drop table if exists system_config;

/*==============================================================*/
/* Table: manager_permission_resource                           */
/*==============================================================*/
create table manager_permission_resource
(
   id                   bigint not null auto_increment comment '主键',
   parent_id            bigint not null comment '父权限资源ID(0:表示root级)',
   resource_depth       int comment '资源深度',
   permission_sort      int comment '排序字段',
   permission_name      varchar(128) not null comment '权限名称',
   resource_class       varchar(128) comment '资源样式class(前端class属性)',
   resource_style       varchar(128) comment '资源样式style(前端style属性)',
   resource_router_url  varchar(128) comment '资源路由URL(前端使用)',
   resource_type        varchar(8) not null comment '资源类型(API:接口,MENU:菜单,BUTTON:按钮)',
   resource_api_uri     varchar(128) comment '资源API URI(非必须,api才有)',
   resource_api_uri_methods varchar(128) comment '资源API URI方法methods(GET,POST,DELETE,PUT,以'',''分割)',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
   remark               varchar(128) comment '备注',
   category_code        varchar(8) comment '分类(C,R,U,D)冗余字段',
   primary key (id)
)
charset = UTF8;

alter table manager_permission_resource comment '后台管理权限资源表';

/*==============================================================*/
/* Table: manager_role                                          */
/*==============================================================*/
create table manager_role
(
   id                   bigint not null auto_increment comment '主键',
   role_name            varchar(32) comment '角色名称',
   role_name_code       varchar(32) not null comment '角色名称core',
   description          varchar(128) comment '描述',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
   remark               varchar(128) comment '备注',
   primary key (id),
   unique key AK_role_role_name_code_uk (role_name_code)
)
charset = UTF8;

alter table manager_role comment '后台管理角色表';

/*==============================================================*/
/* Table: manager_role_permission_resource                      */
/*==============================================================*/
create table manager_role_permission_resource
(
   id                   bigint not null auto_increment comment '主键',
   role_id              bigint not null comment '后台管理角色_id',
   permission_resource_id bigint not null comment '后台管理权限资源_id',
   primary key (id)
)
charset = UTF8;

alter table manager_role_permission_resource comment '后台管理角色资源中间表';

/*==============================================================*/
/* Table: manager_user                                          */
/*==============================================================*/
create table manager_user
(
   id                   bigint not null auto_increment comment '主键',
   username             varchar(32) not null comment '用户名(登录名称)',
   password             varchar(128) not null comment '密码',
   nick_name            varchar(128) comment '昵称',
   real_name            varchar(128) comment '真实姓名',
   email                varchar(128) comment '电子邮箱',
   phone                varchar(18) comment '手机号码',
   last_password_reset_date datetime comment '密码最后重置(修改)日期',
   create_manager_admin_user_id bigint comment '创建人',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
   is_enabled           tinyint(1) not null comment '账户状态(1:激活,0:锁定)',
   remark               varchar(128) comment '备注',
   primary key (id)
)
charset = UTF8;

alter table manager_user comment '后台管理用户';

/*==============================================================*/
/* Index: manager_admin_user_username_uk_index                  */
/*==============================================================*/
create unique index manager_admin_user_username_uk_index on manager_user
(
   username
);

/*==============================================================*/
/* Table: manager_user_action_history                           */
/*==============================================================*/
create table manager_user_action_history
(
   id                   bigint not null auto_increment comment '主键',
   user_id              bigint not null comment '后台管理用户ID',
   user_real_name       varchar(128) comment '后台管理用户真实姓名',
   action_level         varchar(128) comment '操作级别(FATAL_1 : 致命,能影响到应用 , ERROR_2 : 错误,会影响正常功能, WARN_3 : 日常警告 ,INFO_4 : 日常记录)',
   action_type          varchar(12) comment '操作类型',
   action_log           varchar(1024) comment '操作日志(也用于可以存储异常栈信息,或者运行的sql)',
   action_ip_address     varchar(64) comment '操作ip地址',
   action_description   varchar(128) comment '操作描述',
   is_warn              tinyint(1) not null default 0 comment '是否警报(注意【强制】POJO 类的 Boolean 属性不能加 is，而数据库字段必须加 is_，要求在 resultMap 中 进行字段与属性之间的映射。)',
   action_start_time    datetime comment '动作开始时间',
   action_end_time      datetime comment '动作结束时间',
   action_total_time    datetime comment '总执行时间',
   action_class         VARCHAR(128) comment '操作类',
   action_method        VARCHAR(128) comment '操作方法',
   action_args          VARCHAR(2048) comment '方法参数',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
)
ENGINE = MyISAM
charset = UTF8;

alter table manager_user_action_history comment '后台管理用户历史记录操作表(MYISAM引擎)';

/*==============================================================*/
/* Table: manager_user_role                                     */
/*==============================================================*/
create table manager_user_role
(
   id                   bigint not null auto_increment comment '主键',
   user_id              bigint not null comment '后台管理用户_id',
   role_id              bigint not null comment '后台管理角色_id',
   primary key (id)
)
charset = UTF8;

alter table manager_user_role comment '后台管理用户角色中间表';

/*==============================================================*/
/* Table: system_config                                         */
/*==============================================================*/
create table system_config
(
   id                   bigint not null auto_increment comment '主键',
   config_key           varchar(64) not null comment 'key',
   config_value         varchar(1024) not null comment 'value',
   config_description   varchar(256) comment '说明',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
   remark               varchar(128) comment '备注',
   primary key (id)
)
ENGINE = MyISAM
charset = UTF8;

alter table system_config comment '系统配置表(MYISAM引擎)';
