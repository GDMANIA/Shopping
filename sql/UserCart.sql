drop table if exists t_usercart;
create table t_usercart(
	usercart_id int primary key auto_increment,
	user_id varchar(100) not null comment '��Ӧ���û�ID',
	user_name varchar(100) not null comment '��Ӧ���û���',
	ischosen int not null default 1 comment '�Ƿ�ѡ�У�ѡ��Ϊ1����ѡ��Ϊ0',
	goods_id varchar(50) not null comment '��ƷID',
	goods_num int not null default 1 comment '��Ҫ���������'
);

