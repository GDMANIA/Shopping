drop table if exists t_goodsevaluation;
create table t_goodsevaluation(
	goodsevaluation_id int primary key auto_increment,
	user_id varchar(100) not null comment '对应的用户ID',
	goods_id varchar(50) not null comment '商品ID',
	evaluation_details text comment '商品评价具体内容',
	evaluation_createtime varchar(50) not null default '' comment '评价生成时间'
);
