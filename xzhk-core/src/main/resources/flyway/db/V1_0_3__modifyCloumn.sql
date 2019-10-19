ALTER TABLE `xzhk`.`t_department`
CHANGE COLUMN `order` `dp_order` int(11) NULL DEFAULT NULL COMMENT '排序' AFTER `parent_id`;
ALTER TABLE `xzhk`.`t_department_member`
MODIFY COLUMN `user_id` varchar(225) NULL DEFAULT NULL COMMENT '成员UserID' AFTER `id`;
ALTER TABLE `xzhk`.`t_department_member`
DROP COLUMN `order`;
ALTER TABLE `xzhk`.`r_department_member`
ADD COLUMN `is_leaderIn_dept` int(11) NULL COMMENT '是否为上级' AFTER `member_id`,
ADD COLUMN `mb_order` int(11) NULL COMMENT '部门内排序' AFTER `is_leaderIn_dept`;