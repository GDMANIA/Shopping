drop table if exists t_keyword;
create table t_keyword(
	key_id int primary key auto_increment comment '�ؼ���ID����������',
	key_name varchar(20) not null default '' comment '�ؼ������ƣ�����ÿ��������˵Ψһ',
	cat_id int not null comment '������ID��ÿ���ؼ�������һ��������',
	administrator_name varchar(100) NOT NULL DEFAULT '' COMMENT '����Ա��',
	key_createtime varchar(50) not null default '' comment '�ؼ�������ʱ��'
);

insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('Ůװ',1,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('��װ',1,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('ͯװ',1,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('����',1,'realcjames','2016-07-07 18:09:08');

insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('����',2,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('��ʳ',2,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('����',2,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('����',2,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('����',2,'realcjames','2016-07-07 18:09:08');

insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('����',3,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('�ֻ�',3,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('���',3,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('����',3,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('����',3,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('�յ�',3,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('ϴ�»�',3,'realcjames','2016-07-07 18:09:08');

insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('��ױ',4,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('ϴ��',4,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('����Ʒ',4,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('�ٻ�',4,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('����',4,'realcjames','2016-07-07 18:09:08');

insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('�ľ�',5,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('��ӡ��',5,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('ͶӰ��',5,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('������',5,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('�㳮��',5,'realcjames','2016-07-07 18:09:08');

insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('����',6,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('Ӱ��',6,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('��Ϸ',6,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('����',6,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('�˶�',6,'realcjames','2016-07-07 18:09:08');
