DROP TABLE IF EXISTS `t_adminloginrecord`;
CREATE TABLE `t_adminloginrecord` (
  `record_id` int primary key auto_increment,
  `administrator_name` varchar(100) NOT NULL DEFAULT '' COMMENT '����Ա��',
  `login_time` varchar(100) NOT NULL DEFAULT '' COMMENT '����Ա��½ʱ��'
);

--insert into t_administrator(administrator_id,administrator_name,administrator_password) values('e676bd779db4408e96b391302067af70','realcjames','111111');
