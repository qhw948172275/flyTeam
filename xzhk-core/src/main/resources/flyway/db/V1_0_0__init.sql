/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : xzhk

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 24/09/2019 14:44:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('parent-client', NULL, '$2a$10$oitzdtvtBCKdvdyRQAOz1OlgbThEkAWpsl7ifj7H7vwH6T2GPTNAm', 'read,write,trust', 'password,refresh_token', NULL, 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 864000, 864000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('teacher-client', NULL, '$2a$10$oitzdtvtBCKdvdyRQAOz1OlgbThEkAWpsl7ifj7H7vwH6T2GPTNAm', 'read,write,trust', 'password,refresh_token', NULL, 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 864000, 864000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('user-client', NULL, '$2a$10$oitzdtvtBCKdvdyRQAOz1OlgbThEkAWpsl7ifj7H7vwH6T2GPTNAm', 'read,write,trust', 'password,refresh_token', NULL, 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 864000, 864000, NULL, NULL);

-- ----------------------------
-- Table structure for r_sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `r_sys_role_resource`;
CREATE TABLE `r_sys_role_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT 0 COMMENT '角色组ID',
  `resource_id` int(11) NOT NULL COMMENT '资源功能ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_r_sys_role_resource_t_sys_role`(`role_id`) USING BTREE,
  CONSTRAINT `fk_r_sys_role_resource_t_sys_role` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17643 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与权限关联关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_sys_role_resource
-- ----------------------------
INSERT INTO `r_sys_role_resource` VALUES (17072, 38, 865);
INSERT INTO `r_sys_role_resource` VALUES (17073, 38, 759);
INSERT INTO `r_sys_role_resource` VALUES (17076, 38, 866);
INSERT INTO `r_sys_role_resource` VALUES (17080, 38, 868);
INSERT INTO `r_sys_role_resource` VALUES (17081, 38, 860);
INSERT INTO `r_sys_role_resource` VALUES (17082, 38, 861);
INSERT INTO `r_sys_role_resource` VALUES (17083, 38, 862);
INSERT INTO `r_sys_role_resource` VALUES (17084, 38, 863);
INSERT INTO `r_sys_role_resource` VALUES (17085, 38, 864);
INSERT INTO `r_sys_role_resource` VALUES (17188, 36, 759);
INSERT INTO `r_sys_role_resource` VALUES (17189, 36, 760);
INSERT INTO `r_sys_role_resource` VALUES (17190, 36, 761);
INSERT INTO `r_sys_role_resource` VALUES (17346, 36, 856);
INSERT INTO `r_sys_role_resource` VALUES (17347, 36, 857);
INSERT INTO `r_sys_role_resource` VALUES (17348, 36, 858);
INSERT INTO `r_sys_role_resource` VALUES (17373, 38, 869);
INSERT INTO `r_sys_role_resource` VALUES (17623, 101, 865);
INSERT INTO `r_sys_role_resource` VALUES (17624, 101, 759);
INSERT INTO `r_sys_role_resource` VALUES (17625, 101, 866);
INSERT INTO `r_sys_role_resource` VALUES (17626, 101, 868);
INSERT INTO `r_sys_role_resource` VALUES (17627, 101, 860);
INSERT INTO `r_sys_role_resource` VALUES (17628, 101, 861);
INSERT INTO `r_sys_role_resource` VALUES (17629, 101, 862);
INSERT INTO `r_sys_role_resource` VALUES (17630, 101, 863);
INSERT INTO `r_sys_role_resource` VALUES (17631, 101, 864);
INSERT INTO `r_sys_role_resource` VALUES (17632, 101, 869);
INSERT INTO `r_sys_role_resource` VALUES (17633, 102, 865);
INSERT INTO `r_sys_role_resource` VALUES (17634, 102, 759);
INSERT INTO `r_sys_role_resource` VALUES (17635, 102, 866);
INSERT INTO `r_sys_role_resource` VALUES (17636, 102, 868);
INSERT INTO `r_sys_role_resource` VALUES (17637, 102, 860);
INSERT INTO `r_sys_role_resource` VALUES (17638, 102, 861);
INSERT INTO `r_sys_role_resource` VALUES (17639, 102, 862);
INSERT INTO `r_sys_role_resource` VALUES (17640, 102, 863);
INSERT INTO `r_sys_role_resource` VALUES (17641, 102, 864);
INSERT INTO `r_sys_role_resource` VALUES (17642, 102, 869);

-- ----------------------------
-- Table structure for r_sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `r_sys_role_user`;
CREATE TABLE `r_sys_role_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `school_id` int(11) NULL DEFAULT 0 COMMENT '学校ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_r_sys_role_user_t_sys_role`(`role_id`) USING BTREE,
  INDEX `fk_r_sys_role_user_t_sys_user`(`uid`) USING BTREE,
  CONSTRAINT `fk_r_sys_role_user_t_sys_role` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_r_sys_role_user_t_sys_user` FOREIGN KEY (`uid`) REFERENCES `t_sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 483 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与角色关联关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_sys_role_user
-- ----------------------------
INSERT INTO `r_sys_role_user` VALUES (400, 189, 36, 0);
INSERT INTO `r_sys_role_user` VALUES (478, 346, 101, 1);
INSERT INTO `r_sys_role_user` VALUES (479, 351, 102, 2);
INSERT INTO `r_sys_role_user` VALUES (482, 354, 36, 0);

-- ----------------------------
-- Table structure for t_sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_resource`;
CREATE TABLE `t_sys_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '功能ID，功能在数据库的唯一标示。',
  `resource_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能名称',
  `resource_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能请求路径',
  `is_basic` int(1) NOT NULL DEFAULT 0 COMMENT '是否是基础功能,0表示不是基础功能，1表示是基础功能。',
  `parent_id` int(4) NOT NULL COMMENT '父节点ID',
  `parent_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所有父级id拼接',
  `level` int(4) NULL DEFAULT NULL COMMENT '当前节点的级别，例如例如根节点，第一级节点，，，，',
  `remark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能描述',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态0表示正常，1表示不正常',
  `resource_kind` int(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '1菜单资源属于商城，2菜单资源属于平台，3菜单资源属于直播 5菜单属于erp 6菜单属于系统',
  `resource_type` int(4) NULL DEFAULT NULL COMMENT '资源类型 0 菜单 1按钮',
  `seq` int(1) NULL DEFAULT NULL COMMENT '排序',
  `open_mode` int(1) NULL DEFAULT NULL COMMENT '打开方式 ajax,iframe',
  `opened` int(1) NULL DEFAULT NULL COMMENT '打开状态',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 870 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_resource
-- ----------------------------
INSERT INTO `t_sys_resource` VALUES (759, '系统管理', 'system', 0, -1, 'string', 0, '', 0, 0, 0, 2, 0, 0, 'example');
INSERT INTO `t_sys_resource` VALUES (760, '用户管理', 'system/useradmin', 0, 759, 'string', 0, 'string', 0, 0, 0, 1, 0, 0, '');
INSERT INTO `t_sys_resource` VALUES (761, '权限管理', 'system/jurisdiction', 0, 759, 'string', 0, 'string', 0, 0, 0, 2, 0, 0, '');
INSERT INTO `t_sys_resource` VALUES (856, '学校管理', 'school', 0, -1, NULL, NULL, '', 0, NULL, 0, 3, NULL, NULL, 'example');
INSERT INTO `t_sys_resource` VALUES (857, '基础管理', 'information/basic-information', 0, 856, NULL, NULL, '', 0, NULL, 0, 1, NULL, NULL, '');
INSERT INTO `t_sys_resource` VALUES (858, '使用信息登记', 'school/schoolUseInfo', 0, 856, NULL, NULL, '', 0, NULL, 0, 2, NULL, NULL, '');
INSERT INTO `t_sys_resource` VALUES (860, 'banner管理', 'banner', 0, -1, NULL, NULL, '', 0, NULL, 0, 6, NULL, NULL, 'example');
INSERT INTO `t_sys_resource` VALUES (861, '课程管理', 'curriculum', 0, -1, NULL, NULL, '', 0, NULL, 0, 7, NULL, NULL, 'example');
INSERT INTO `t_sys_resource` VALUES (862, '班级管理', 'class', 0, -1, NULL, NULL, '', 0, NULL, 0, 8, NULL, NULL, 'example');
INSERT INTO `t_sys_resource` VALUES (863, '任课老师管理', 'teachers', 0, -1, NULL, NULL, '', 0, NULL, 0, 9, NULL, NULL, 'example');
INSERT INTO `t_sys_resource` VALUES (864, '学生管理', 'student', 0, -1, NULL, NULL, '', 0, NULL, 0, 10, NULL, NULL, 'example');
INSERT INTO `t_sys_resource` VALUES (865, '首页', 'dashboard', 0, -1, NULL, NULL, '', 0, NULL, 0, 1, NULL, NULL, 'example');
INSERT INTO `t_sys_resource` VALUES (866, '角色管理', 'system/role', 0, 759, NULL, NULL, '', 0, NULL, 0, 4, NULL, NULL, '');
INSERT INTO `t_sys_resource` VALUES (868, '学校管理', 'school', 0, -1, NULL, NULL, '', 0, NULL, 0, 5, NULL, NULL, 'example');
INSERT INTO `t_sys_resource` VALUES (869, '用户管理', 'system/schooluser', 0, 759, NULL, NULL, '学校平台用户管理', 0, NULL, 0, 3, NULL, NULL, '');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色组的唯一标识',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色组名称',
  `description` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色组描述',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '角色状态,0默认为有效，1表示已被禁用不能使用。',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色组创建人',
  `createtime` bigint(20) NOT NULL COMMENT '创建时间',
  `last_update_creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '此角色组上一次修改人',
  `last_update_createtime` bigint(20) NULL DEFAULT NULL COMMENT '此角色上一次修改时间',
  `role_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色代码',
  `school_id` int(11) NULL DEFAULT NULL COMMENT '学校Id',
  `parent_id` int(11) NULL DEFAULT NULL,
  `role_type` int(1) NOT NULL COMMENT '角色类型：0 普通 角色；1 系统角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (36, '超级管理员', '超级管理员', 0, 'admin', 1487596539093, '藏獒', 1563352417932, 'FINALPLATFORMADMINROLECODE', 0, NULL, 0);
INSERT INTO `t_sys_role` VALUES (38, '基础校管理员角色', NULL, 0, 'admin', 1563264226426, NULL, NULL, '82NBI6', 0, NULL, 0);
INSERT INTO `t_sys_role` VALUES (101, '校管理员', NULL, 0, '超级管理员', 1564974446896, NULL, NULL, 'SCHOOLDEFAULTADMINCODE', 1, NULL, 0);
INSERT INTO `t_sys_role` VALUES (102, '校管理员', NULL, 0, '超级管理员', 1566211388641, NULL, NULL, 'SCHOOLDEFAULTADMINCODE', 2, NULL, 0);

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '登录用户ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户email号。用于保证用户唯一，且用于通过email进行帐号激活。',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者',
  `createtime` bigint(20) NOT NULL COMMENT '创建时间',
  `period` int(8) NULL DEFAULT 0 COMMENT '课时',
  `last_Login_Time` bigint(20) NULL DEFAULT NULL COMMENT '上次登录时间',
  `last_Login_Ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上次登录IP',
  `real_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户真实姓名',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `school_id` int(11) NULL DEFAULT 0 COMMENT '学校ID。为空则表示为平台管理用户',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '教师ID',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `is_teacher` int(1) NULL DEFAULT 0 COMMENT '是否是任课老师0-不是，1-是',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别,男/女',
  `school_job_id` int(11) NULL DEFAULT NULL COMMENT '职务ID',
  `client_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推送ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 355 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES (189, 'admin', NULL, '$2a$10$sNH5KoYksvVXPbrRbXnLQ.8hc9zGmKOMc53cVYQbk5CA1S0MLG.5a', 0, 'admin', 1563193947358, NULL, NULL, NULL, '超级管理员', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES (346, 'hgg123', NULL, '$2a$10$PoB7u1Yhn5uY1IBFm6mg8.WPHmllYkv16nwQBonzJEuogyUJ9BOkm', 0, '超级管理员', 1564974446882, 0, NULL, NULL, '校管理员', NULL, 1, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES (347, '13880077874', NULL, '$2a$10$g9kliHTGHGOAuIGUs6ffTebLWmmN2Tpn7J04dS1uaL4b0rhsdHo1K', 0, '校管理员', 1564975333064, 3, NULL, NULL, '向老师', '13880077874', 1, NULL, NULL, 1, '女', NULL, NULL);
INSERT INTO `t_sys_user` VALUES (348, '15311111111', NULL, '$2a$10$Erx8Y3.u6tIkgaaOBG9A4ujjSnrzYJc2a4GI1iu0Jlbb/xukoK0Te', 0, '校管理员', 1565004814298, 8, NULL, NULL, '董老师', '17828006626', 1, NULL, NULL, 1, '女', NULL, NULL);
INSERT INTO `t_sys_user` VALUES (349, '18780167353', NULL, '$2a$10$qLxx.fuQbhsHeLgz5Eyz7.npYTE4kewBbTXZjGZqt7jdNSGemEZVG', 0, '校管理员', 1565667610632, 0, NULL, NULL, '芒果老师', '18780167353', 1, NULL, NULL, 1, '女', NULL, NULL);
INSERT INTO `t_sys_user` VALUES (350, '13688060079', NULL, '$2a$10$pgiWQMtkileB9JgabWsr.Ornwmv/84NlJdAcDHaEF3LdTAeDYRrJ6', 0, '校管理员', 1565667734518, 0, NULL, NULL, '刘老师', '13688060079', 1, NULL, NULL, 1, '女', NULL, NULL);
INSERT INTO `t_sys_user` VALUES (351, 'nccs123', NULL, '$2a$10$gj6zyBx2ByXG1CsBkatwF.o0RVql9pjHuAbqJAYKx/Tk9fQnMSAoK', 0, '超级管理员', 1566211388630, 0, NULL, NULL, '校管理员', NULL, 2, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES (352, 'string', 'string', '$2a$10$8vTA7bl7TH5gPee/a/1g.e630My3lL9AyH5vIHNFftGAGfPAcfere', 0, '校管理员', 1567508314268, 0, 0, 'string', 'string', 'string', 1, 0, 'string', 0, '1', 0, 'string');
INSERT INTO `t_sys_user` VALUES (353, 'string001', 'string', '$2a$10$98BtBc3LAiAtL9723TQ2hu2TlXLZ/LPn6V1rstLWytQPd1PbjXROe', 0, '校管理员', 1567508537240, 0, 0, 'string', 'string', 'string', 1, 0, 'string', 0, '1', 0, 'string');
INSERT INTO `t_sys_user` VALUES (354, 'string01', 'string', '$2a$10$GugZmTbFwkbYb97XTPFSQOGCht6I1iSvh8yaqZ3k7CgtQwY6HKWUO', 0, '校管理员', 1567508730615, 0, 0, 'string', 'string', 'string', 1, 0, 'string', 0, '1', 0, 'string');

SET FOREIGN_KEY_CHECKS = 1;
