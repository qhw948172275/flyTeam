CREATE TABLE `xzhk`.`r_test_system_member`  (
  `id` int(11) NOT NULL,
  `test_system_id` int(11) NULL COMMENT '测试系统ID',
  `member_id` int(11) NULL COMMENT '成员ID',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_r_test_system_test_system_id` FOREIGN KEY (`test_system_id`) REFERENCES `xzhk`.`t_test_system` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_r_test_system_member_id` FOREIGN KEY (`member_id`) REFERENCES `xzhk`.`t_department_member` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);
ALTER TABLE `xzhk`.`t_annex_manage`
ADD COLUMN `size` varchar(225) NULL COMMENT '文件大小' AFTER `article_id`;