drop table if exists t_floor;
create table t_floor(
	floor_id int primary key auto_increment,
	floor_name varchar(20),
	goods_id text not null,
	administrator_name varchar(100) NOT NULL DEFAULT '' COMMENT '管理员名',
	floor_createtime varchar(50) not null default '' comment '楼层生成时间'
);

insert into t_floor values(1,'服装','02ca3105c7ad45789e8976d83b07527e,2b94fb13ef0a464dbf7496e2ae9f056a,3603eba48a3b46f490297b5a1c14c037,b3c9ac529ad449f59f9d3ecf3ac3faed','realcjames','2016-07-07 18:09:08');
insert into t_floor values(2,'食品','1849d2076c1e48b3b639b00e7757c8f3,abb7a6159e5a40ef87a68ebc00ea2196,a478e6bf65e943f28fa0041e89a3fae8','realcjames','2016-07-07 18:09:08');
insert into t_floor values(3,'数码','0a61269261d74cfea4f461662571947d,1e2added741b4f1fb09048fa9c4b17f8','realcjames','2016-07-07 18:09:08');
insert into t_floor values(4,'日用品','18ec1063d00a4eafbb338943cf8e171b,27342aac26764ef89c4d0ef16ad5e7d5,079452576dc945a69d1b852baf17b54d,0a8c7e3710084b90934da3a174b36947,ebece671a1d04ec3932985f69f922fde','realcjames','2016-07-07 18:09:08');
insert into t_floor values(5,'办公','7d593fcdf9b74c7e8d71330108f6062c,03ccf278e2ea4c0db87a16c5c46530c5,c61f9176069e45208c9b43bece6b6b92','realcjames','2016-07-07 18:09:08');
insert into t_floor values(6,'娱乐','0d6576933539422c8ca5b0349d8865c1,298b82860bd14137b466dd4222794f0b,00307cf78554434b8bf39206ab9b7ca0,6ef06cd06a424d17822d7083b59cc8a9','realcjames','2016-07-07 18:09:08');
