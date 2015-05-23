drop table if exists SysmanUserRoleRel;

drop table if exists SysmanRoleResourceRel;

drop table if exists SysmanResource;

drop table if exists SysmanRole;

drop table if exists SysmanUser;



/*==============================================================*/
/* Table: SysmanResource                                        */
/*==============================================================*/
create table SysmanResource
(
   id                   int(32) not null auto_increment,
   deleteFlag           int(1),
   createTime           date,
   name                 varchar(32),
   description          varchar(100),
   href                 varchar(100),
   resourceType         int(1),
   parentId             int(32),
   primary key (id)
);

alter table SysmanResource comment '后台资源权限表';

/*==============================================================*/
/* Table: SysmanRole                                            */
/*==============================================================*/
create table SysmanRole
(
   id                   int(32) not null auto_increment,
   deleteFlag           int(1),
   createTime           date,
   name                 varchar(32),
   description          varchar(100),
   primary key (id)
);

alter table SysmanRole comment '后台用户角色表';

/*==============================================================*/
/* Table: SysmanRoleResourceRel                                 */
/*==============================================================*/
create table SysmanRoleResourceRel
(
   sysmanRoleId         int(32) not null,
   sysmanResourceId     int(32) not null,
   primary key (sysmanRoleId, sysmanResourceId)
);

alter table SysmanRoleResourceRel comment '后台管理用户角色-资源-关系表';

/*==============================================================*/
/* Table: SysmanUser                                            */
/*==============================================================*/
create table SysmanUser
(
   id                   int(32) not null auto_increment,
   deleteFlag           int(1),
   createTime           date,
   name                 varchar(32),
   password             varchar(32),
   primary key (id)
);

alter table SysmanUser comment '后台用户表结构';

/*==============================================================*/
/* Table: SysmanUserRoleRel                                     */
/*==============================================================*/
create table SysmanUserRoleRel
(
   sysmanUserId         int(32) not null,
   sysmanRoleId         int(32) not null,
   primary key (sysmanUserId, sysmanRoleId)
);

alter table SysmanUserRoleRel comment '后台用户-角色-关联表';

alter table SysmanRoleResourceRel add constraint FK_Reference_sysmanRoleResourceRel_sysmanResource foreign key (sysmanResourceId)
      references SysmanResource (id) on delete restrict on update restrict;

alter table SysmanRoleResourceRel add constraint FK_Reference_sysmanRoleResourceRel_sysmanRole foreign key (sysmanRoleId)
      references SysmanRole (id) on delete restrict on update restrict;

alter table SysmanUserRoleRel add constraint FK_Reference_sysmanUserRoleRel_sysmanRole foreign key (sysmanRoleId)
      references SysmanRole (id) on delete restrict on update restrict;

alter table SysmanUserRoleRel add constraint FK_Reference_sysmanUserRoleRel_sysmanUser foreign key (sysmanUserId)
      references SysmanUser (id) on delete restrict on update restrict;
