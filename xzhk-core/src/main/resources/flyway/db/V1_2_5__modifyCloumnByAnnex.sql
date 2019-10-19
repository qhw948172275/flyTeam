ALTER TABLE `xzhk`.`t_annex_manage`
CHANGE COLUMN `article_id` `apply_id` int(11) NULL DEFAULT NULL COMMENT '应用ID' AFTER `name`;