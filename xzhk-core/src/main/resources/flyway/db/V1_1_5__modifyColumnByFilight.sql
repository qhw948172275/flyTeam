ALTER TABLE `xzhk`.`t_flight_report`
MODIFY COLUMN `event_content` text NULL COMMENT '事件经过及可能原因' AFTER `is_flight_record`;