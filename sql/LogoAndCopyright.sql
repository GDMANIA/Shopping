drop table if exists t_logoandcopyright;
create table t_logoandcopyright(
	logoandcopyright_id int primary key auto_increment comment '������ID����������',
	logo_img varchar(100) not null default '' comment '��վlogoͼƬ��',
	copyright_detail varchar(200) not null default '' comment '��վ��Ȩ��Ϣ',
	logoandcopyright_isvalid int not null default 1 comment '��վLogo�Ͱ�Ȩ��Ϣ�Ƿ���Ч��Ĭ��1Ϊ��Ч��0Ϊ��Ч',
	administrator_name varchar(100) NOT NULL DEFAULT '' COMMENT '����Ա��',
	logoandcopyright_createtime varchar(50) not null default '' comment '��Ϣ����ʱ��'
);

insert into t_logoandcopyright(logo_img,copyright_detail,logoandcopyright_isvalid,administrator_name,logoandcopyright_createtime) values('shopping_logo.png','(c) Copyright 2016 new. All Rights Reserved.',1,'realcjames','2016-07-08 18:14:08');
