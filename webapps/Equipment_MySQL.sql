/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/4/20 20:48:35                           */
/*==============================================================*/


drop table if exists UtiCode;

drop table if exists t_account;

drop table if exists t_item;

drop table if exists t_itemplan;

drop table if exists t_user;

drop table if exists t_document;

/*==============================================================*/
/* Table: UtiCode                                               */
/*==============================================================*/
create table UtiCode
(
   codetype             varchar(255) not null,
   codetypedesc         varchar(255) comment '代码类型描述',
   codecode             varchar(255) not null,
   codename             varchar(255) not null,
   codecode1            varchar(255),
   codecode2            varchar(255),
   codecode3            varchar(255),
   primary key (codetype, codecode)
);

/*==============================================================*/
/* Table: t_account                                             */
/*==============================================================*/
create table t_account
(
   id                   int not null auto_increment,
   type                 int not null comment '0,出库;1 入库;9,默认编码（异常编码）',
   item_code            varchar(255),
   item_name            varchar(255),
   item_model           varchar(255),
   unit                 varchar(4),
   price 					decimal(8,2) comment '单价',
   amount				decimal(8,2) comment '金额',
   number               float not null,
   costtype               varchar(255),
   department           varchar(255),
   operator             varchar(255),
   handler              varchar(255),
   reason               varchar(255),
   opt_time             datetime,
   create_time          datetime,
   update_time          datetime,
   updater              varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: t_item                                                */
/*==============================================================*/
create table t_item
(
   code                 varchar(255) not null comment '唯一性编码',
   name                 varchar(255) not null,
   model                varchar(255),
   price                decimal(10,2),
   unit                 varchar(255),
   amount               float comment '全矿库存',
   primary key (code)
);

/*==============================================================*/
/* Table: t_itemplan                                            */
/*==============================================================*/
create table t_itemplan
(
   id                   int not null auto_increment,
   itemcode             varchar(255) not null,
   planmonth            char(7) not null comment '格式yyyy-mm',
   department           varchar(255) not null,
   plannumber           float not null,
   costtype				varchar(255),
   price 					decimal(8,2) comment '单价',
   amount				decimal(8,2) comment '金额',
   purpose              varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   usercode             varchar(255) not null,
   username             varchar(255),
   password             varchar(255),
   department           varchar(255),
   usertype             int comment '管理员:0,普通用户:1',
   status               int comment '0:停用,1:启用',
   primary key (usercode)
);


/*==============================================================*/
/* Table: t_document                                            */
/*==============================================================*/
create table t_document
(
   id                   int not null auto_increment,
   title                varchar(255),
   filetype             varchar(255) comment 'doc,ppt,video',
   category             varchar(255),
   uploader             varchar(255),
   uploadtime           datetime,
   filename            varchar(255),
   path                 varchar(1000),
   primary key (id)
);