DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` varchar(100) NOT NULL COMMENT '�û���������UUID����',
  `user_level` int(11) NOT NULL DEFAULT '1' COMMENT '�û��ȼ���Ĭ��һ��',
  `user_name` varchar(100) NOT NULL DEFAULT '' COMMENT '�û���',
  `user_password` varchar(100) NOT NULL DEFAULT '' COMMENT '����',
  `user_email` varchar(100) NOT NULL DEFAULT '' COMMENT '����',
  `user_telnum` varchar(100) NOT NULL DEFAULT '' COMMENT '�ֻ���',
  `user_img` varchar(100) NOT NULL DEFAULT '' COMMENT 'ͷ��ͼƬ��ַ',
  `user_ttpayment` double NOT NULL DEFAULT 0 COMMENT '�û������ܽ��',
  `user_regtime` varchar(100) NOT NULL DEFAULT '' COMMENT 'ע��ʱ��',
  `user_lastlogintime` varchar(100) NOT NULL DEFAULT '' COMMENT '�ϴε�¼ʱ��',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


