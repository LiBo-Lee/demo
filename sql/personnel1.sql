/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost
 Source Database       : personnel

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : utf-8

 Date: 04/26/2019 16:22:02 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `dept_inf`
-- ----------------------------
DROP TABLE IF EXISTS `dept_inf`;
CREATE TABLE `dept_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `dept_inf`
-- ----------------------------
BEGIN;
INSERT INTO `dept_inf` VALUES ('1', '技术部', '技术部'), ('2', '运营部', '运营部'), ('3', '财务部', '财务部'), ('4', '总公办', '总公办'), ('5', '市场部', '市场部'), ('6', '教学部', '教学部');
COMMIT;

-- ----------------------------
--  Table structure for `document_inf`
-- ----------------------------
DROP TABLE IF EXISTS `document_inf`;
CREATE TABLE `document_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `filename` varchar(300) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `document_inf`
-- ----------------------------
BEGIN;
INSERT INTO `document_inf` VALUES ('1', '2,2', '01.jpg', '11', null, null), ('2', '2,2', '01.jpg', null, null, null), ('3', '4', '2.jpg', '4', null, null), ('4', '1', '1.jpg', '1', null, null), ('5', '2', '1.jpg', '2', null, null), ('6', '2', '1.jpg', '2', null, null), ('7', '3', '5.jpg', '3', null, null), ('8', '2', '5.jpg', '2', null, null), ('9', '额外热污染', 'tqe.sql', '热舞任务二r', null, '1');
COMMIT;

-- ----------------------------
--  Table structure for `employee_inf`
-- ----------------------------
DROP TABLE IF EXISTS `employee_inf`;
CREATE TABLE `employee_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `card_id` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `post_code` varchar(50) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `qq_num` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `party` varchar(10) DEFAULT NULL,
  `birthday` char(20) DEFAULT NULL,
  `race` varchar(100) DEFAULT NULL,
  `education` varchar(10) DEFAULT NULL,
  `speciality` varchar(20) DEFAULT NULL,
  `hobby` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
	`update_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `employee_inf`
-- ----------------------------
BEGIN;
INSERT INTO `employee_inf` VALUES ('1', '1', '8', '爱丽丝', '4328011988', '广州天河', '510000', '020-77777777', '13902001111', '36750066', '251425887@qq.com', '0', '党员', '1980-01-01 00:00:00', '满', '本科', '美声', '唱歌', '四大天王', '2023-03-10 10:22:08', null, '2023-03-10 10:22:08'), ('2', '3', '1', '杰克', '22623', '43234', '42427424', '42242', '4247242', '42424', '251425887@qq.com', '2', null, null, null, '1', null, null, null, '2023-03-10 10:22:08', null, '2023-03-10 10:22:08'), ('3', '1', '2', '肉丝', '432801197711251038', '广州', '510000', '020-99999999', '13907351532', '36750064', '36750064@qq.com', '1', '党员', '1977-11-25 00:00:00', '汉', '本科', '计算机', '爬山', '无', '2023-03-10 10:22:08', null, '2023-03-10 10:22:08');
COMMIT;

-- ----------------------------
--  Table structure for `job_inf`
-- ----------------------------
DROP TABLE IF EXISTS `job_inf`;
CREATE TABLE `job_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `job_inf`
-- ----------------------------
BEGIN;
INSERT INTO `job_inf` VALUES ('1', 'Java开发工程师', 'Java开发工程师'), ('2', 'Java中级开发工程师', 'Java中级开发工程师'), ('3', 'Java高级开发工程师', 'Java高级开发工程师'), ('4', '架构师', '架构师'), ('5', '主管', '主管'), ('6', '经理', '经理'), ('7', '总经理', '总经理');
COMMIT;

-- ----------------------------
--  Table structure for `notice_inf`
-- ----------------------------
DROP TABLE IF EXISTS `notice_inf`;
CREATE TABLE `notice_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `create_date` char(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `notice_inf`
-- ----------------------------
BEGIN;
INSERT INTO `notice_inf` VALUES ('1', '1112', '132323', null, null), ('2', '444', '4444', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `user_inf`
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `loginname` varchar(20) DEFAULT NULL COMMENT '登录名称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码（密文）',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐值',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `createdate` char(20) DEFAULT NULL COMMENT '创建日期',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `pwd_login_limit` tinyint(1) NULL DEFAULT NULL COMMENT '密码登录限制（1：连续错3次，锁定账号15分钟。2：连续错5次，锁定账号30分钟）',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT '盐值';

-- ----------------------------
--  Records of `user_inf`
-- ----------------------------
BEGIN;
INSERT INTO `user_inf` VALUES ('1', '超级管理员', 'root', '718e2f66813bb6f1', '386f25e43ae6272ee97e7bf8ebdbb4f0', '2', '2023-03-10 10:22:08', 1, '超级管理员', 1), ('2', '管理员', 'admin', '718e2f66813bb6f1', '386f25e43ae6272ee97e7bf8ebdbb4f0', '2', '2023-03-10 10:22:08', 2, '管理员', 1), ('3', '主管', 'LiBo', '718e2f66813bb6f1', '386f25e43ae6272ee97e7bf8ebdbb4f0', '2', '2023-03-10 10:22:08', 3, '主管', 1), ('4', '员工', 'Test', '718e2f66813bb6f1', '386f25e43ae6272ee97e7bf8ebdbb4f0', '2', '2023-03-10 10:22:08', 4, '员工', 1);
COMMIT;

-- ----------------------------
-- Table structure for role_inf
-- ----------------------------
DROP TABLE IF EXISTS `role_inf`;
CREATE TABLE `role_inf`  (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '角色ID',
  `role_key` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '角色权限字符',
  `role_name` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '角色名称',
  `remark` varchar(255) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `role_inf` VALUES ('1', 'SuperAdmin', '超级管理员', NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `role_inf` VALUES ('2', 'Admin', '管理员', NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `role_inf` VALUES ('3', 'SuperVisor', '主管', NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `role_inf` VALUES ('4', 'Member', '员工', NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
COMMIT;

-- ----------------------------
-- Table structure for menu_inf
-- ----------------------------
DROP TABLE IF EXISTS `menu_inf`;
CREATE TABLE `menu_inf`  (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '菜单ID',
  `parent_id` varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '父菜单ID',
  `name` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '菜单名称',
  `sort` int NULL DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(200) CHARACTER SET utf8 NULL DEFAULT '' COMMENT '访问路径',
  `path` varchar(100) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '组件名称',
  `type` char(1) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `state` tinyint(1) NULL DEFAULT 1 COMMENT '菜单状态（1正常 2停用 3删除）',
  `perms` varchar(100) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '权限标识',
  `visible` tinyint(1) NULL DEFAULT 0 COMMENT '显示状态（0 显示 1隐藏）',
  `remark` varchar(500) CHARACTER SET utf8 NULL DEFAULT '' COMMENT '备注',
  `create_by` varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu_inf
-- ----------------------------
BEGIN;
INSERT INTO `menu_inf` VALUES ('1', 'ROOT', '用户管理', 1, '/user/listUser', 'user', 'C', 1, 'user:view', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('2', 'ROOT', '部门管理', 2, '/dept/listDept', 'dept', 'C', 1, 'dept:view', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('3', 'ROOT', '员工管理', 3, '/employee/listEmployee', 'employee', 'C', 1, 'employee:view', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('4', 'ROOT', '文档管理', 4, '/document/listDocument', 'document', 'C', 1, 'document:view', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('5', 'ROOT', '提醒管理', 5, '/notice/listNotice', 'notice', 'C', 1, 'notice:view', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('11', '1', '新增', 1, '/user/insertUser', 'user', 'F', 1, 'user:add', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('12', '1', '修改', 2, '/user/updateUser', 'user', 'F', 1, 'user:update', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('13', '1', '删除', 3, '/user/deleteUser', 'user', 'F', 1, 'user:delete', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('21', '2', '新增', 1, '/dept/insertDept', 'dept', 'F', 1, 'dept:add', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('22', '2', '修改', 2, '/dept/updateDept', 'dept', 'F', 1, 'dept:update', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('23', '2', '删除', 3, '/dept/deleteDeptByName', 'dept', 'F', 1, 'dept:delete', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('31', '3', '新增', 1, '/employee/insertEmployee', 'employee', 'F', 1, 'employee:add', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('32', '3', '修改', 2, '/employee/updateEmployee', 'employee', 'F', 1, 'employee:update', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('33', '3', '删除', 3, '/employee/deleteEmployee', 'employee', 'F', 1, 'employee:delete', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('41', '4', '新增', 1, '/document/insertDocument', 'document', 'F', 1, 'document:add', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('42', '4', '修改', 2, '/document/updateDocument', 'document', 'F', 1, 'document:update', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('43', '4', '删除', 3, '/document/deleteDocument', 'document', 'F', 1, 'document:delete', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('51', '5', '新增', 1, '/notice/insertNotice', 'notice', 'F', 1, 'notice:add', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('52', '5', '修改', 2, '/notice/updateNotice', 'notice', 'F', 1, 'notice:update', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
INSERT INTO `menu_inf` VALUES ('53', '5', '删除', 3, '/notice/deleteNotice', 'notice', 'F', 1, 'notice:delete', 0, NULL, NULL, '2023-03-10 10:22:08', NULL, '2023-03-10 10:22:08');
COMMIT;

-- ----------------------------
-- Table structure for role_menu_inf
-- ----------------------------
DROP TABLE IF EXISTS `role_menu_inf`;
CREATE TABLE `role_menu_inf`  (
  `role_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '角色ID',
  `menu_id` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '角色菜单关联' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of role_menu_inf
-- ----------------------------
-- 超级管理员权限
INSERT INTO `role_menu_inf` VALUES ('1', '1');
INSERT INTO `role_menu_inf` VALUES ('1', '11');
INSERT INTO `role_menu_inf` VALUES ('1', '12');
INSERT INTO `role_menu_inf` VALUES ('1', '13');
INSERT INTO `role_menu_inf` VALUES ('1', '2');
INSERT INTO `role_menu_inf` VALUES ('1', '21');
INSERT INTO `role_menu_inf` VALUES ('1', '22');
INSERT INTO `role_menu_inf` VALUES ('1', '23');
INSERT INTO `role_menu_inf` VALUES ('1', '3');
INSERT INTO `role_menu_inf` VALUES ('1', '31');
INSERT INTO `role_menu_inf` VALUES ('1', '32');
INSERT INTO `role_menu_inf` VALUES ('1', '33');
INSERT INTO `role_menu_inf` VALUES ('1', '4');
INSERT INTO `role_menu_inf` VALUES ('1', '41');
INSERT INTO `role_menu_inf` VALUES ('1', '42');
INSERT INTO `role_menu_inf` VALUES ('1', '43');
INSERT INTO `role_menu_inf` VALUES ('1', '5');
INSERT INTO `role_menu_inf` VALUES ('1', '51');
INSERT INTO `role_menu_inf` VALUES ('1', '52');
INSERT INTO `role_menu_inf` VALUES ('1', '53');
-- 管理员权限
INSERT INTO `role_menu_inf` VALUES ('2', '1');
INSERT INTO `role_menu_inf` VALUES ('2', '2');
INSERT INTO `role_menu_inf` VALUES ('2', '21');
INSERT INTO `role_menu_inf` VALUES ('2', '22');
INSERT INTO `role_menu_inf` VALUES ('2', '23');
INSERT INTO `role_menu_inf` VALUES ('2', '3');
INSERT INTO `role_menu_inf` VALUES ('2', '31');
INSERT INTO `role_menu_inf` VALUES ('2', '32');
INSERT INTO `role_menu_inf` VALUES ('2', '33');
INSERT INTO `role_menu_inf` VALUES ('2', '4');
INSERT INTO `role_menu_inf` VALUES ('2', '41');
INSERT INTO `role_menu_inf` VALUES ('2', '42');
INSERT INTO `role_menu_inf` VALUES ('2', '43');
INSERT INTO `role_menu_inf` VALUES ('2', '5');
INSERT INTO `role_menu_inf` VALUES ('2', '51');
INSERT INTO `role_menu_inf` VALUES ('2', '52');
INSERT INTO `role_menu_inf` VALUES ('2', '53');
-- 主管权限
INSERT INTO `role_menu_inf` VALUES ('3', '1');
INSERT INTO `role_menu_inf` VALUES ('3', '2');
INSERT INTO `role_menu_inf` VALUES ('3', '3');
INSERT INTO `role_menu_inf` VALUES ('3', '31');
INSERT INTO `role_menu_inf` VALUES ('3', '32');
INSERT INTO `role_menu_inf` VALUES ('3', '4');
INSERT INTO `role_menu_inf` VALUES ('3', '41');
INSERT INTO `role_menu_inf` VALUES ('3', '42');
INSERT INTO `role_menu_inf` VALUES ('3', '43');
INSERT INTO `role_menu_inf` VALUES ('3', '5');
INSERT INTO `role_menu_inf` VALUES ('3', '51');
INSERT INTO `role_menu_inf` VALUES ('3', '52');
INSERT INTO `role_menu_inf` VALUES ('3', '53');
-- 员工权限
INSERT INTO `role_menu_inf` VALUES ('4', '4');
INSERT INTO `role_menu_inf` VALUES ('4', '41');
INSERT INTO `role_menu_inf` VALUES ('4', '42');
INSERT INTO `role_menu_inf` VALUES ('4', '43');
INSERT INTO `role_menu_inf` VALUES ('4', '5');
INSERT INTO `role_menu_inf` VALUES ('4', '51');
INSERT INTO `role_menu_inf` VALUES ('4', '52');
INSERT INTO `role_menu_inf` VALUES ('4', '53');


SET FOREIGN_KEY_CHECKS = 1;
