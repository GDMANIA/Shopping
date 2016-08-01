drop table if exists t_logoandcopyright;
create table t_logoandcopyright(
	logoandcopyright_id int primary key auto_increment comment '主分类ID，主键自增',
	logo_img varchar(100) not null default '' comment '网站logo图片名',
	copyright_detail varchar(200) not null default '' comment '网站版权信息',
	logoandcopyright_isvalid int not null default 1 comment '网站Logo和版权信息是否有效，默认1为有效，0为无效',
	administrator_name varchar(100) NOT NULL DEFAULT '' COMMENT '管理员名',
	logoandcopyright_createtime varchar(50) not null default '' comment '信息生成时间'
);

insert into t_logoandcopyright(logo_img,copyright_detail,logoandcopyright_isvalid,administrator_name,logoandcopyright_createtime) values('shopping_logo.png','(c) Copyright 2016 new. All Rights Reserved.',1,'realcjames','2016-07-08 18:14:08');
