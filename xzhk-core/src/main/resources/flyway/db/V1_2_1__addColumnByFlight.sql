ALTER TABLE `xzhk`.`t_flight_report`
ADD COLUMN `base_place` varchar (225) NULL COMMENT '出发地' ;
ALTER TABLE `xzhk`.`t_flight_report`
ADD COLUMN `destination` varchar (225) NULL COMMENT '目的地' ;

ALTER TABLE `xzhk`.`t_test_system`
ADD COLUMN `end_date` datetime(0) NULL COMMENT '截至日期' ;
