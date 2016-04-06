/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2000                    */
/* Created on:     2016/2/25 星期四 20:08:17                       */
/*==============================================================*/


if exists (select 1
            from  sysobjects
           where  id = object_id('t_account')
            and   type = 'U')
   drop table t_account
go

if exists (select 1
            from  sysobjects
           where  id = object_id('t_item')
            and   type = 'U')
   drop table t_item
go

if exists (select 1
            from  sysobjects
           where  id = object_id('t_user')
            and   type = 'U')
   drop table t_user
go

if exists (select 1
            from  sysobjects
           where  id = object_id('UtiCode')
            and   type = 'U')
   drop table UtiCode
go

/*==============================================================*/
/* Table: t_account                                             */
/*==============================================================*/
create table t_account (
   id                   int                  identity(1,1),
   type                 int                  not null,
   item_code            varchar(255)         not null,
   item_name            varchar(255)         null,
   item_model           varchar(255)         null,
   unit						varchar(4)         null,
   number               int             not  null,
   department           varchar(255)         null,
   operator             varchar(255)         null,
   handler              varchar(255)         null,
   reason               varchar(255)         null,
   opt_time             datetime             null,
   create_time          datetime             null,
   update_time          datetime             null,
   updater              varchar(255)         null
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '流水账',
   'user', @CurrentUser, 'table', 't_account'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '账目编号',
   'user', @CurrentUser, 'table', 't_account', 'column', 'id'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0,出库;1 入库',
   'user', @CurrentUser, 'table', 't_account', 'column', 'type'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '设备编码',
   'user', @CurrentUser, 'table', 't_account', 'column', 'item_code'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '设备名称',
   'user', @CurrentUser, 'table', 't_account', 'column', 'item_name'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '设备型号',
   'user', @CurrentUser, 'table', 't_account', 'column', 'item_model'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '数量',
   'user', @CurrentUser, 'table', 't_account', 'column', 'number'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '归属单位',
   'user', @CurrentUser, 'table', 't_account', 'column', 'department'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作员',
   'user', @CurrentUser, 'table', 't_account', 'column', 'operator'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '经办人',
   'user', @CurrentUser, 'table', 't_account', 'column', 'handler'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '出入库原因',
   'user', @CurrentUser, 'table', 't_account', 'column', 'reason'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '出入库时间',
   'user', @CurrentUser, 'table', 't_account', 'column', 'opt_time'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '录入时间',
   'user', @CurrentUser, 'table', 't_account', 'column', 'create_time'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '最后修改时间',
   'user', @CurrentUser, 'table', 't_account', 'column', 'update_time'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '最后修改人',
   'user', @CurrentUser, 'table', 't_account', 'column', 'updater'
go

alter table t_account
   add constraint PK_T_ACCOUNT primary key (id)
go

/*==============================================================*/
/* Table: t_item                                                */
/*==============================================================*/
create table t_item (
   code                 varchar(255)         not null,
   name                 varchar(255)         not null,
   model                varchar(255)         null,
   price                numeric(10,2)        null,
   unit                 varchar(255)         null,
   amount               float                null
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '物料编码表',
   'user', @CurrentUser, 'table', 't_item'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '唯一性编码',
   'user', @CurrentUser, 'table', 't_item', 'column', 'code'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '设备材料名称',
   'user', @CurrentUser, 'table', 't_item', 'column', 'name'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '设备材料型号',
   'user', @CurrentUser, 'table', 't_item', 'column', 'model'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '单价',
   'user', @CurrentUser, 'table', 't_item', 'column', 'price'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '计量单位',
   'user', @CurrentUser, 'table', 't_item', 'column', 'unit'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '库存',
   'user', @CurrentUser, 'table', 't_item', 'column', 'amount'
go

alter table t_item
   add constraint PK_T_ITEM primary key (code)
go

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user (
   usercode             varchar(255)         not null,
   username             varchar(255)         null,
   password             varchar(255)        not  null,
   department           varchar(255)        not null,
   usertype             int                 not null,
   status				int					not null
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户',
   'user', @CurrentUser, 'table', 't_user'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户代码',
   'user', @CurrentUser, 'table', 't_user', 'column', 'usercode'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户名',
   'user', @CurrentUser, 'table', 't_user', 'column', 'username'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '密码',
   'user', @CurrentUser, 'table', 't_user', 'column', 'password'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '归属部门',
   'user', @CurrentUser, 'table', 't_user', 'column', 'department'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '管理员:0,普通用户:1',
   'user', @CurrentUser, 'table', 't_user', 'column', 'usertype'
go

alter table t_user
   add constraint PK_T_USER primary key (usercode)
go

alter table t_account
   add constraint FK_T_ACCOUN_REFERENCE_T_ITEM foreign key (item_code)
      references t_item (code)
go




/*==============================================================*/
/* Table: UtiCode                                               */
/*==============================================================*/
create table UtiCode (
   codetype             varchar(255)         not null,
   codetypedesc         varchar(255)         null,
   codecode             varchar(255)         not null,
   codename             varchar(255)         not null,
   codecode1            varchar(255)         null,
   codecode2            varchar(255)         null,
   codecode3            varchar(255)         null
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '通用代码表',
   'user', @CurrentUser, 'table', 'UtiCode'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '代码类型',
   'user', @CurrentUser, 'table', 'UtiCode', 'column', 'codetype'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '代码类型描述',
   'user', @CurrentUser, 'table', 'UtiCode', 'column', 'codetypedesc'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '代码',
   'user', @CurrentUser, 'table', 'UtiCode', 'column', 'codecode'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '代码名称',
   'user', @CurrentUser, 'table', 'UtiCode', 'column', 'codename'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '一级代码',
   'user', @CurrentUser, 'table', 'UtiCode', 'column', 'codecode1'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '二级代码',
   'user', @CurrentUser, 'table', 'UtiCode', 'column', 'codecode2'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '三级代码',
   'user', @CurrentUser, 'table', 'UtiCode', 'column', 'codecode3'
go

alter table UtiCode
   add constraint PK_UTICODE primary key (codetype, codecode)
go
