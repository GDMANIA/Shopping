drop table if exists t_category;
create table t_category(
	cat_id int primary key auto_increment comment '������ID����������',
	cat_name varchar(20) not null default '' comment '���������ƣ�Ψһ',
	administrator_name varchar(100) NOT NULL DEFAULT '' COMMENT '����Ա��',
	cat_createtime varchar(50) not null default '' comment '����������ʱ��'
);

insert into t_category(cat_name,administrator_name,cat_createtime) values('��װ','realcjames','2016-07-07 18:09:08');
insert into t_category(cat_name,administrator_name,cat_createtime) values('ʳƷ','realcjames','2016-07-07 18:10:08');
insert into t_category(cat_name,administrator_name,cat_createtime) values('����','realcjames','2016-07-07 18:11:08');
insert into t_category(cat_name,administrator_name,cat_createtime) values('����Ʒ','realcjames','2016-07-07 18:12:08');
insert into t_category(cat_name,administrator_name,cat_createtime) values('�칫','realcjames','2016-07-07 18:13:08');
insert into t_category(cat_name,administrator_name,cat_createtime) values('����','realcjames','2016-07-07 18:14:08');
