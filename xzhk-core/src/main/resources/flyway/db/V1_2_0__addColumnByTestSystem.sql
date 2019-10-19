ALTER TABLE `xzhk`.`t_test_system`
ADD COLUMN `pass_mark` int(5) NULL COMMENT '及格分数' ;
ALTER TABLE `xzhk`.`t_test_system`
ADD COLUMN `pass_count` int(11) NULL COMMENT '及格人数' ;