ALTER TABLE `xzhk`.`t_article`
ADD COLUMN `send_time` datetime(0) NULL COMMENT '发布时间' AFTER `content`;