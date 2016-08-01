DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` varchar(100) NOT NULL COMMENT '用户主键，用UUID设置',
  `user_level` int(11) NOT NULL DEFAULT '1' COMMENT '用户等级，默认一级',
  `user_name` varchar(100) NOT NULL DEFAULT '' COMMENT '用户名',
  `user_password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
  `user_email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱',
  `user_telnum` varchar(100) NOT NULL DEFAULT '' COMMENT '手机号',
  `user_img` varchar(100) NOT NULL DEFAULT '' COMMENT '头像图片地址',
  `user_ttpayment` double NOT NULL DEFAULT 0 COMMENT '用户消费总金额',
  `user_regtime` varchar(100) NOT NULL DEFAULT '' COMMENT '注册时间',
  `user_lastlogintime` varchar(100) NOT NULL DEFAULT '' COMMENT '上次登录时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


