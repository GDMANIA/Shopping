drop table if exists t_shoprecommendation;
create table t_shoprecommendation(
	recommendation_id int primary key auto_increment,
	goods_cat int not null comment '商品的分类ID',
	goods_key int not null comment '商品的关键字ID',
	goods_id varchar(50) not null comment '商品ID',
	goods_soldnum int not null default 0 comment '商品已售件数',
	administrator_name varchar(100) NOT NULL DEFAULT '' COMMENT '管理员名',
	shoprecommendation_createtime varchar(50) not null default '' comment '店铺推荐生成时间'
);

insert into t_shoprecommendation values(1,1,1,'02ca3105c7ad45789e8976d83b07527e',1116,'realcjames','2016-07-07 18:09:08');
insert into t_shoprecommendation values(2,2,1,'241ea49c34ac4ee7ba234e64299c1147',22110,'realcjames','2016-07-07 18:09:08');
insert into t_shoprecommendation values(3,3,1,'42871a3e71764ca3ba40860208dd201e',3311,'realcjames','2016-07-07 18:09:08');
insert into t_shoprecommendation values(4,4,1,'018b9eae89514edcb91c5d8086b0db83',4416,'realcjames','2016-07-07 18:09:08');
insert into t_shoprecommendation values(5,5,1,'09a6cfa397b64bc7aca6de8a4fb56c9b',55110,'realcjames','2016-07-07 18:09:08');
insert into t_shoprecommendation values(6,6,1,'0d6576933539422c8ca5b0349d8865c1',6612,'realcjames','2016-07-07 18:09:08');
insert into t_shoprecommendation values(7,1,2,'2b94fb13ef0a464dbf7496e2ae9f056a',1128,'realcjames','2016-07-07 18:09:08');
insert into t_shoprecommendation values(8,2,2,'1849d2076c1e48b3b639b00e7757c8f3',2229,'realcjames','2016-07-07 18:09:08');
insert into t_shoprecommendation values(9,3,2,'1e2added741b4f1fb09048fa9c4b17f8',33210,'realcjames','2016-07-07 18:09:08');
insert into t_shoprecommendation values(10,4,2,'27342aac26764ef89c4d0ef16ad5e7d5',44210,'realcjames','2016-07-07 18:09:08');

