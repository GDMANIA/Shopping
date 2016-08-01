drop table if exists t_useraddress;
create table t_useraddress(
	useraddress_id int primary key auto_increment,
	user_id varchar(100) not null comment '��Ӧ���û�ID',
	user_name varchar(100) not null comment '��Ӧ���û���',
	consignee_name varchar(20) not null comment '�ջ�����',
	consignee_province varchar(100) not null comment '�ջ�ʡ',
	consignee_city varchar(100) not null comment '�ջ���',
	consignee_area varchar(100) not null comment '�ջ�����',
	consignee_address varchar(200) not null comment '�ջ��˾����ַ',
	consignee_telnum varchar(20) not null comment '�ջ��˾���绰',
	isdefault_address int not null default 0 comment '�ж��Ƿ�ΪĬ�ϵ�ַ��1ΪĬ�ϣ�0Ϊ��Ĭ��'
);

insert into t_useraddress values(
1,
'6268d8e492ba4ceca1aa3b487fd5ee70',
'18005198598',
'���',
'����ʡ',
'�Ͼ���',
'������',
'�к�·69��',
'18005198598',
0
);

insert into t_useraddress values(
2,
'6268d8e492ba4ceca1aa3b487fd5ee70',
'18005198598',
'Ԫ��',
'����ʡ',
'�Ͼ���',
'������',
'�к�·69��',
'18994132194',
1
);

insert into t_useraddress values(
3,
'6268d8e492ba4ceca1aa3b487fd5ee70',
'18005198598',
'�캣��',
'����ʡ',
'�Ͼ���',
'��ˮ��',
'ˮ������',
'13952087100',
0
);
