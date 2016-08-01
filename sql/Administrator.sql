DROP TABLE IF EXISTS `t_administrator`;
CREATE TABLE `t_administrator` (
  `administrator_id` varchar(100) NOT NULL COMMENT '管理员主键，用UUID设置',
  `administrator_name` varchar(100) NOT NULL DEFAULT '' COMMENT '管理员名',
  `administrator_password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
  PRIMARY KEY (`administrator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into t_administrator(administrator_id,administrator_name,administrator_password) values('e676bd779db4408e96b391302067af70','realcjames','111111');
