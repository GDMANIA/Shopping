drop table if exists t_usercollection;
create table t_usercollection(
	usercollection_id int primary key auto_increment,
	user_id varchar(100) not null comment '对应的用户ID',
	user_name varchar(100) not null comment '对应的用户名',
	goods_id text not null comment '对应的收藏的商品ID' 
);

