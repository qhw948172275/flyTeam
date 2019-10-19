CREATE TABLE r_department_member
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	department_id INTEGER
		COMMENT '部门ID' NULL,
	member_id INTEGER
		COMMENT '成员ID' NULL
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE r_department_member COMMENT = '部门员工关联表';


/******************** Add Table: r_member_article ************************/

/* Build Table Structure */
CREATE TABLE r_member_article
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	member_id INTEGER
		COMMENT '成员ID' NULL,
	article INTEGER
		COMMENT '文章ID' NULL,
	start_read_time DATETIME
		COMMENT '开始阅读时间' NULL,
	end_read_time DATETIME
		COMMENT '结束阅读时间' NULL,
	is_read_success TINYINT
		COMMENT '是否阅读成功，0-否，1-是' NULL,
	ip VARCHAR(225)
		COMMENT 'IP地址' NULL
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE r_member_article COMMENT = '成员接受文章关联';


/* Build Table Structure */
CREATE TABLE r_test_system_question_brank
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	test_system_id INTEGER
		COMMENT '测试系统ID' NULL,
	question_brank_id INTEGER
		COMMENT '题库ID' NULL,
	mark TINYINT
		COMMENT '单个题分数' NULL
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE r_test_system_question_brank COMMENT = '测试系统跟题关联表';


/******************** Add Table: t_annex ************************/

/* Build Table Structure */
CREATE TABLE t_annex
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	status TINYINT
		COMMENT '状态：0-启用，1-禁用' NULL,
	url VARCHAR(225)
		COMMENT '附件地址' NULL,
	name VARCHAR(225)
		COMMENT '附件名称' NULL,
	article_id INTEGER
		COMMENT '文章ID' NULL
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE t_annex COMMENT = '附件管理';


/******************** Add Table: t_article ************************/

/* Build Table Structure */
CREATE TABLE t_article
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	type TINYINT
		COMMENT '文章类型' NULL,
	title VARCHAR(225)
		COMMENT '文章标题' NULL,
	send_status TINYINT
		COMMENT '发布状态：0-草稿，1-发布，2-未发布' NULL,
	cover VARCHAR(225)
		COMMENT '封面' NULL,
	click_count BIGINT
		COMMENT '点击量' NULL,
	read_success_rate DOUBLE
		COMMENT '阅读成功率' NULL,
	create_time DATETIME
		COMMENT '创建时间' NULL,
	update_time DATETIME
		COMMENT '更新时间' NULL,
	status TINYINT
		COMMENT '状态' NULL,
	shzre_set TINYINT
		COMMENT '0-仅在企业内分享，1-可对外分享，2-不能分享' NULL,
	summary VARCHAR(225)
		COMMENT '摘要' NULL,
	author VARCHAR(225)
		COMMENT '作者' NULL,
	receive_number INTEGER
		COMMENT '应接收人数' NULL,
	create_id INTEGER
		COMMENT '创建人ID' NULL,
	send_type TINYINT
		COMMENT '发布类型，0-直接发布，1-定时发布' NULL,
	content TEXT
		COMMENT '文章内容' NULL
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE t_article COMMENT = '文章信息';


/******************** Add Table: t_department ************************/

/* Build Table Structure */
CREATE TABLE t_department
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name VARCHAR(225)
		COMMENT '部门名称' NULL,
	parent_id INTEGER
		COMMENT '父类ID' NULL,
	`order` TINYINT
		COMMENT '排序' NULL,
	status TINYINT
		COMMENT '状态' NULL
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE t_department COMMENT = '部门';


/******************** Add Table: t_department_member ************************/

/* Build Table Structure */
CREATE TABLE t_department_member
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	user_id INTEGER
		COMMENT '成员UserID' NULL,
	name VARCHAR(225)
		COMMENT '	成员名称' NULL,
	mobile VARCHAR(225)
		COMMENT '手机号码' NULL,
	`order` INTEGER
		COMMENT '部门内的排序值' NULL,
	position VARCHAR(225)
		COMMENT '职务信息' NULL,
	gender TINYINT
		COMMENT '0表示未定义，1表示男性，2表示女性' NULL,
	email VARCHAR(225)
		COMMENT '邮箱' NULL,
	is_leader_in_dept TINYINT
		COMMENT '表示在所在的部门内是否为上级' NULL,
	avatar VARCHAR(225)
		COMMENT '头像url' NULL,
	telephone VARCHAR(225)
		COMMENT '座机' NULL,
	status TINYINT
		COMMENT '激活状态: 1=已激活，2=已禁用，4=未激活 ' NULL,
	address VARCHAR(225)
		COMMENT '地址' NULL
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE t_department_member COMMENT = '部门成员';


/******************** Add Table: t_do_record ************************/

/* Build Table Structure */
CREATE TABLE t_do_record
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	member_id INTEGER
		COMMENT '成员ID' NULL,
	marks INTEGER
		COMMENT '总分数' NULL,
	right_count TINYINT
		COMMENT '正确答案数量' NULL,
	error_count TINYINT
		COMMENT '错误答案数量' NULL,
	test_system_id INTEGER
		COMMENT '测试系统ID' NULL,
	t_test_system_id INTEGER NULL
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE t_do_record COMMENT = '系统测试做题记录';


/******************** Add Table: t_do_record_detail ************************/

/* Build Table Structure */
CREATE TABLE t_do_record_detail
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	do_record_id INTEGER
		COMMENT '成员做题统计ID' NULL,
	question_brank_id INTEGER
		COMMENT '题ID' NULL,
	answer VARCHAR(225)
		COMMENT '成员提交的答案' NULL,
	member_id INTEGER
		COMMENT '成员ID' NULL,
	mark TINYINT
		COMMENT '得分' NULL,
	is_right TINYINT
		COMMENT '是否正确：0-否，1-是' NULL,
	t_do_record_id INTEGER NULL
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE t_do_record_detail COMMENT = '成员做题详情';


/******************** Add Table: t_flight_report ************************/

/* Build Table Structure */
CREATE TABLE t_flight_report
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	create_id INTEGER
		COMMENT '创建人ID' NULL,
	create_time DATETIME
		COMMENT '创建时间' NULL,
	input_time VARCHAR(125)
		COMMENT '输入日期' NULL,
	flight_model VARCHAR(125)
		COMMENT '飞行型号' NULL,
	flight_number VARCHAR(125)
		COMMENT '航班号' NULL,
	route VARCHAR(125)
		COMMENT '航线' NULL,
	left_seat_member_name VARCHAR(125)
		COMMENT '左座人员姓名' NULL,
	right_seat_member_name VARCHAR(125)
		COMMENT '右座人员姓名' NULL,
	other_member_names TEXT
		COMMENT '其他成员姓名' NULL,
	title TEXT
		COMMENT '标题' NULL,
	weather_condition TINYINT
		COMMENT '天气情况:0-雨，1-雪，2-雷暴，3-冰雹，4-雾，
5-结冰，6-乱流，7-风切边，8-其他' NULL,
	weather_condition_ramark VARCHAR(125)
		COMMENT '天气情况其他说明' NULL,
	flight_impact TINYINT
		COMMENT '飞行影响:0-轻微，1-轻度，2-中度，3-严重' NULL,
	event_stage TINYINT
		COMMENT '事件发生阶段：0-地面准备，1-起飞前滑行，2-起飞，3-爬升，4-巡航/作业，5-下降，6-接近，7-复飞，8-着陆，9-接地后滑跑，10-脱离跑到后滑行，11-关车，12-其他' NULL,
	event_stage_remarke VARCHAR(125)
		COMMENT '事件发生阶段备注' NULL,
	flight_fault VARCHAR(125)
		COMMENT '飞机故障情况：0-发动机故障，1-仪表设备故障，2-电气系统故障，3-操作系统故障，4-液压系统故障，5-电子系统故障，6-其他系统故障' NULL,
	flight_fault_remark VARCHAR(125)
		COMMENT '飞机故障的其他故障备注' NULL,
	is_flight_record TINYINT
		COMMENT '是否填写飞行记录：0-未填写，1-填写' NULL,
	event_content TINYINT
		COMMENT '事件经过及可能原因' NULL,
	status TINYINT
		COMMENT '0-启用你，1-禁用' NULL
);

/* Add Comments */
ALTER TABLE t_flight_report COMMENT = '飞行报告表';


/******************** Add Table: t_question_bank ************************/

/* Build Table Structure */
CREATE TABLE t_question_bank
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY
		COMMENT '题库ID' NOT NULL,
	title VARCHAR(225)
		COMMENT '题目' NULL,
	type TINYINT
		COMMENT '题类型' NULL,
	content TEXT
		COMMENT '题内容' NULL,
	choose_result VARCHAR(225)
		COMMENT '正确答案' NULL
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE t_question_bank COMMENT = '题库';


/* Build Table Structure */
CREATE TABLE t_test_system
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name VARCHAR(225)
		COMMENT '标题名称' NULL,
	push_time DATETIME
		COMMENT '发布日期' NULL,
	receive_number INTEGER
		COMMENT '接收人数' NULL,
	qualified_number INTEGER
		COMMENT '合格人数' NULL,
	create_time DATETIME
		COMMENT '创建时间' NULL,
	question_count TINYINT
		COMMENT '题数量' NULL
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE t_test_system COMMENT = '测试系统';





/************ Add Foreign Keys ***************/

/* Add Foreign Key: fk_r_department_member_t_department */
ALTER TABLE r_department_member ADD CONSTRAINT fk_r_department_member_t_department
	FOREIGN KEY (department_id) REFERENCES t_department (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_r_department_member_t_department_member */
ALTER TABLE r_department_member ADD CONSTRAINT fk_r_department_member_t_department_member
	FOREIGN KEY (member_id) REFERENCES t_department_member (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_r_member_article_t_article */
ALTER TABLE r_member_article ADD CONSTRAINT fk_r_member_article_t_article
	FOREIGN KEY (article) REFERENCES t_article (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_r_member_article_t_department_member */
ALTER TABLE r_member_article ADD CONSTRAINT fk_r_member_article_t_department_member
	FOREIGN KEY (member_id) REFERENCES t_department_member (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_r_test_system_question_brank_t_question_bank */
ALTER TABLE r_test_system_question_brank ADD CONSTRAINT fk_r_test_system_question_brank_t_question_bank
	FOREIGN KEY (question_brank_id) REFERENCES t_question_bank (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_r_test_system_question_brank_t_test_system */
ALTER TABLE r_test_system_question_brank ADD CONSTRAINT fk_r_test_system_question_brank_t_test_system
	FOREIGN KEY (test_system_id) REFERENCES t_test_system (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_t_annex_t_article */
ALTER TABLE t_annex ADD CONSTRAINT fk_t_annex_t_article
	FOREIGN KEY (article_id) REFERENCES t_article (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_t_do_record_t_test_system */
ALTER TABLE t_do_record ADD CONSTRAINT fk_t_do_record_t_test_system
	FOREIGN KEY (t_test_system_id) REFERENCES t_test_system (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_t_do_record_detail_t_do_record */
ALTER TABLE t_do_record_detail ADD CONSTRAINT fk_t_do_record_detail_t_do_record
	FOREIGN KEY (t_do_record_id) REFERENCES t_do_record (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;