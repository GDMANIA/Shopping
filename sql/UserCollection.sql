drop table if exists t_usercollection;
create table t_usercollection(
	usercollection_id int primary key auto_increment,
	user_id varchar(100) not null comment '��Ӧ���û�ID',
	user_name varchar(100) not null comment '��Ӧ���û���',
	goods_id text not null comment '��Ӧ���ղص���ƷID' 
);

