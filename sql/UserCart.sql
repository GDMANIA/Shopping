drop table if exists t_usercart;
create table t_usercart(
	usercart_id int primary key auto_increment,
	user_id varchar(100) not null comment '对应的用户ID',
	user_name varchar(100) not null comment '对应的用户名',
	ischosen int not null default 1 comment '是否选中，选中为1，不选中为0',
	goods_id varchar(50) not null comment '商品ID',
	goods_num int not null default 1 comment '需要购买的数量'
);

