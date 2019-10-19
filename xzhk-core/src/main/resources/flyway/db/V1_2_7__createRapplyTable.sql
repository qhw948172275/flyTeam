CREATE TABLE `xzhk`.`r_annex_apply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `annex_id` int(11) NULL COMMENT '附件ID',
  `apply_id` int(11) NULL COMMENT '应用ID',
  `type` tinyint(4) NULL COMMENT '应用类型0-文章，1-飞行报告',
  PRIMARY KEY (`id`)
);

ALTER TABLE `xzhk`.`t_annex_manage`
DROP COLUMN `apply_id`,
DROP COLUMN `type`;