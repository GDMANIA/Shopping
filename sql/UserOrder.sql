drop table if exists t_userorder;
create table t_userorder(
	userorder_id varchar(100) primary key comment '订单ID，系统生成ABC+用户名+时间+时间戳后八位',
	user_id varchar(100) not null comment '对应的用户ID',
	user_name varchar(100) not null comment '对应的用户名',
	order_createtime varchar(50) not null default '' comment '订单生成时间',
	goods_id_num text not null comment '商品ID:件数,json格式',
	order_ttprice double not null comment '订单总价',
	order_address varchar(200) not null default '' comment '订单收货人信息+地址',
	order_state int not null default 0 comment '订单状态，0为待支付，1为已支付待发货，2为已发货待签收，3为待评价，4为已评价，5为无效（即24小时之内未支付的）',
	order_isdeleted int not null default 0 comment '订单是否被删除，0为未删除，1为已删除，2为删除并且不再显示',
	order_createtimestamp int not null comment '订单生成的时间戳，用来自动将超时订单转化为无效订单等'
);

--下面是创建userorder存储过程以及事件的过程，作用就是每隔一秒自动检查一次尚未支付的userorder的生成事件戳与当前事件戳的差值，如果超过了
--一天，也就是86400秒，那么就把该尚未支付的订单设为无效订单，在这中间要开启事务，判断是否出错，然后要判断是否回滚还是提交

delimiter$$
drop procedure if exists check_order$$
create procedure check_order()
begin
--下面开启了一个事务，判断如果出现sqlexception的话，就回滚，否则提交
declare t_error int default 0;
declare continue handler for sqlexception set t_error=1;

start transaction;
	update t_userorder set order_state=5 where (unix_timestamp()-order_createtimestamp>86400) and order_state=0;

if t_error=1 then
	rollback;
else
	commit;
end if;
end$$
delimiter;

create event if not exists check_order_event 
on schedule every 1 second
on completion preserve
do call check_order();
