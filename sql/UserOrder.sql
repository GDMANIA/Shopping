drop table if exists t_userorder;
create table t_userorder(
	userorder_id varchar(100) primary key comment '����ID��ϵͳ����ABC+�û���+ʱ��+ʱ������λ',
	user_id varchar(100) not null comment '��Ӧ���û�ID',
	user_name varchar(100) not null comment '��Ӧ���û���',
	order_createtime varchar(50) not null default '' comment '��������ʱ��',
	goods_id_num text not null comment '��ƷID:����,json��ʽ',
	order_ttprice double not null comment '�����ܼ�',
	order_address varchar(200) not null default '' comment '�����ջ�����Ϣ+��ַ',
	order_state int not null default 0 comment '����״̬��0Ϊ��֧����1Ϊ��֧����������2Ϊ�ѷ�����ǩ�գ�3Ϊ�����ۣ�4Ϊ�����ۣ�5Ϊ��Ч����24Сʱ֮��δ֧���ģ�',
	order_isdeleted int not null default 0 comment '�����Ƿ�ɾ����0Ϊδɾ����1Ϊ��ɾ����2Ϊɾ�����Ҳ�����ʾ',
	order_createtimestamp int not null comment '�������ɵ�ʱ����������Զ�����ʱ����ת��Ϊ��Ч������'
);

--�����Ǵ���userorder�洢�����Լ��¼��Ĺ��̣����þ���ÿ��һ���Զ����һ����δ֧����userorder�������¼����뵱ǰ�¼����Ĳ�ֵ�����������
--һ�죬Ҳ����86400�룬��ô�ͰѸ���δ֧���Ķ�����Ϊ��Ч�����������м�Ҫ���������ж��Ƿ����Ȼ��Ҫ�ж��Ƿ�ع������ύ

delimiter$$
drop procedure if exists check_order$$
create procedure check_order()
begin
--���濪����һ�������ж��������sqlexception�Ļ����ͻع��������ύ
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
