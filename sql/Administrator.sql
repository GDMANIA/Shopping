DROP TABLE IF EXISTS `t_administrator`;
CREATE TABLE `t_administrator` (
  `administrator_id` varchar(100) NOT NULL COMMENT '����Ա��������UUID����',
  `administrator_name` varchar(100) NOT NULL DEFAULT '' COMMENT '����Ա��',
  `administrator_password` varchar(100) NOT NULL DEFAULT '' COMMENT '����',
  PRIMARY KEY (`administrator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into t_administrator(administrator_id,administrator_name,administrator_password) values('e676bd779db4408e96b391302067af70','realcjames','111111');
