drop table if exists t_goodsevaluation;
create table t_goodsevaluation(
	goodsevaluation_id int primary key auto_increment,
	user_id varchar(100) not null comment '��Ӧ���û�ID',
	goods_id varchar(50) not null comment '��ƷID',
	evaluation_details text comment '��Ʒ���۾�������',
	evaluation_createtime varchar(50) not null default '' comment '��������ʱ��'
);
