drop table if exists t_keyword;
create table t_keyword(
	key_id int primary key auto_increment comment '关键字ID，主键自增',
	key_name varchar(20) not null default '' comment '关键字名称，对于每个分类来说唯一',
	cat_id int not null comment '主分类ID，每个关键字属于一个主分类',
	administrator_name varchar(100) NOT NULL DEFAULT '' COMMENT '管理员名',
	key_createtime varchar(50) not null default '' comment '关键字生成时间'
);

insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('女装',1,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('男装',1,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('童装',1,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('内衣',1,'realcjames','2016-07-07 18:09:08');

insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('生鲜',2,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('零食',2,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('酒类',2,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('饮料',2,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('粮油',2,'realcjames','2016-07-07 18:09:08');

insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('电脑',3,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('手机',3,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('相机',3,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('电视',3,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('冰箱',3,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('空调',3,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('洗衣机',3,'realcjames','2016-07-07 18:09:08');

insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('美妆',4,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('洗护',4,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('保健品',4,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('百货',4,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('收纳',4,'realcjames','2016-07-07 18:09:08');

insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('文具',5,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('打印机',5,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('投影仪',5,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('保险箱',5,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('点钞机',5,'realcjames','2016-07-07 18:09:08');

insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('动漫',6,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('影视',6,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('游戏',6,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('户外',6,'realcjames','2016-07-07 18:09:08');
insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values('运动',6,'realcjames','2016-07-07 18:09:08');
