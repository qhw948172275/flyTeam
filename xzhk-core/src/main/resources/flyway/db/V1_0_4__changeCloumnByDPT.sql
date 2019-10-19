ALTER TABLE `xzhk`.`t_department`
CHANGE COLUMN `status` `level_path` varchar(225) NULL DEFAULT NULL COMMENT '状态' AFTER `dp_order`;