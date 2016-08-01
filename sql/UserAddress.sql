drop table if exists t_useraddress;
create table t_useraddress(
	useraddress_id int primary key auto_increment,
	user_id varchar(100) not null comment '对应的用户ID',
	user_name varchar(100) not null comment '对应的用户名',
	consignee_name varchar(20) not null comment '收货人名',
	consignee_province varchar(100) not null comment '收货省',
	consignee_city varchar(100) not null comment '收货市',
	consignee_area varchar(100) not null comment '收货区县',
	consignee_address varchar(200) not null comment '收货人具体地址',
	consignee_telnum varchar(20) not null comment '收货人具体电话',
	isdefault_address int not null default 0 comment '判断是否为默认地址，1为默认，0为非默认'
);

insert into t_useraddress values(
1,
'6268d8e492ba4ceca1aa3b487fd5ee70',
'18005198598',
'陈宸',
'江苏省',
'南京市',
'建邺区',
'中和路69号',
'18005198598',
0
);

insert into t_useraddress values(
2,
'6268d8e492ba4ceca1aa3b487fd5ee70',
'18005198598',
'元琳',
'江苏省',
'南京市',
'建邺区',
'中和路69号',
'18994132194',
1
);

insert into t_useraddress values(
3,
'6268d8e492ba4ceca1aa3b487fd5ee70',
'18005198598',
'朱海娟',
'江苏省',
'南京市',
'溧水区',
'水岸康城',
'13952087100',
0
);
