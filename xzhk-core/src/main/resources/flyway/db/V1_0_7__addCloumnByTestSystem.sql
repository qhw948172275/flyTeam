ALTER TABLE `xzhk`.`t_test_system`
ADD COLUMN `create_id` int(11) NULL COMMENT '创建人ID' AFTER `question_count`,
ADD COLUMN `status` tinyint(4) NULL COMMENT '0-草稿，1-发布，2-删除' AFTER `create_id`;