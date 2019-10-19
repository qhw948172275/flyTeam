ALTER TABLE `xzhk`.`t_article`
ADD COLUMN `success_read_number` int(11) NULL COMMENT '成功阅读人数' ;
ALTER TABLE `xzhk`.`t_article`
MODIFY COLUMN `read_success_rate` double(5, 2) NULL DEFAULT NULL COMMENT '阅读成功率' AFTER `click_count`;
ALTER TABLE `xzhk`.`t_flight_report`
MODIFY COLUMN `weather_condition` varchar(125) NULL DEFAULT NULL COMMENT '天气情况:0-雨，1-雪，2-雷暴，3-冰雹，4-雾，\n5-结冰，6-乱流，7-风切边，8-其他' AFTER `title`;

ALTER TABLE `xzhk`.`r_test_system_member`
ADD COLUMN `is_test` tinyint(4) NULL COMMENT '是否测试0-否，1-是' AFTER `member_id`;