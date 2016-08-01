drop table if exists t_category;
create table t_category(
	cat_id int primary key auto_increment comment '主分类ID，主键自增',
	cat_name varchar(20) not null default '' comment '主分类名称，唯一',
	administrator_name varchar(100) NOT NULL DEFAULT '' COMMENT '管理员名',
	cat_createtime varchar(50) not null default '' comment '主分类生成时间'
);

insert into t_category(cat_name,administrator_name,cat_createtime) values('服装','realcjames','2016-07-07 18:09:08');
insert into t_category(cat_name,administrator_name,cat_createtime) values('食品','realcjames','2016-07-07 18:10:08');
insert into t_category(cat_name,administrator_name,cat_createtime) values('数码','realcjames','2016-07-07 18:11:08');
insert into t_category(cat_name,administrator_name,cat_createtime) values('日用品','realcjames','2016-07-07 18:12:08');
insert into t_category(cat_name,administrator_name,cat_createtime) values('办公','realcjames','2016-07-07 18:13:08');
insert into t_category(cat_name,administrator_name,cat_createtime) values('娱乐','realcjames','2016-07-07 18:14:08');
