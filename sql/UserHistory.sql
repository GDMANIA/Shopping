drop table if exists t_userhistory;
create table t_userhistory(
	userhistory_id int primary key auto_increment,
	user_id varchar(100) not null default '' comment '�û�ID',
	user_name varchar(50) not null default '' comment '�û���',
	goods_ids text not null comment '���������ƷID��ֻ���ĸ�'
);

insert into t_userhistory values(1,'6268d8e492ba4ceca1aa3b487fd5ee70','18005198598','0007,0006,0001,0005,');
