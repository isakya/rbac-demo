/*
Navicat MySQL Data Transfer

Source Server         : mysql-111
Source Server Version : 50731
Source Host           : 192.168.201.111:3306
Source Database       : rbac

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2023-4-30 09:51:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `sn` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '人力资源部', 'Human Resources Department');
INSERT INTO `department` VALUES ('2', '采购部', 'Order Department');
INSERT INTO `department` VALUES ('3', '仓储部', 'Warehousing Department');
INSERT INTO `department` VALUES ('4', '财务部', 'Finance Department');
INSERT INTO `department` VALUES ('5', '技术部', 'Technolog Department ');
INSERT INTO `department` VALUES ('16', '测试1', '测试1');
INSERT INTO `department` VALUES ('18', '测试部02', 'TS02');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'admin', '123', 'admin@qq.com', '18', '', '1');
INSERT INTO `employee` VALUES ('2', '赵总', '$2a$10$kFp6OZCQt8gafI8bYIAGUeKKR9Sqch3qIs5i2yXKOHUlgAjmqpNMG', 'zhaoz@wolfcode.cn', '35', '', '1');
INSERT INTO `employee` VALUES ('3', '赵一明', '123', 'zhaoym@wolfcode.cn', '25', '\0', '1');
INSERT INTO `employee` VALUES ('4', '钱总', 'c4ca4238a0b923820dcc509a6f75849b', 'qianz@wolfcode.cn', '35', '\0', '1');
INSERT INTO `employee` VALUES ('5', '钱二明', 'c4ca4238a0b923820dcc509a6f75849b', 'qianem@wolfcode.cn', '25', '\0', '2');
INSERT INTO `employee` VALUES ('6', '孙总', 'c4ca4238a0b923820dcc509a6f75849b', 'sunz@wolfcode.cn', '35', '\0', '3');
INSERT INTO `employee` VALUES ('7', '孙三明', 'c4ca4238a0b923820dcc509a6f75849b', 'sunsm@wolfcode.cn', '25', '\0', '3');
INSERT INTO `employee` VALUES ('8', '李总', 'c4ca4238a0b923820dcc509a6f75849b', 'liz@wolfcode.cn', '35', '\0', '4');
INSERT INTO `employee` VALUES ('9', '李四明', 'c4ca4238a0b923820dcc509a6f75849b', 'lism@wolfcode.cn', '25', '\0', '4');
INSERT INTO `employee` VALUES ('10', '周总', 'c4ca4238a0b923820dcc509a6f75849b', 'zhouz@wolfcode.cn', '35', '\0', '5');
INSERT INTO `employee` VALUES ('12', '吴总', 'c4ca4238a0b923820dcc509a6f75849b', 'wuz@wolfcode.cn', '35', '\0', '5');
INSERT INTO `employee` VALUES ('13', '吴六明', 'c4ca4238a0b923820dcc509a6f75849b', 'wulm@wolfcode.cn', '25', '\0', '5');
INSERT INTO `employee` VALUES ('14', '郑总', 'c4ca4238a0b923820dcc509a6f75849b', 'zhengz@wolfcode.cn', '35', '\0', '5');
INSERT INTO `employee` VALUES ('15', '郑七明', 'c4ca4238a0b923820dcc509a6f75849b', 'zhengqm@wolfcode.cn', '25', '\0', '5');
INSERT INTO `employee` VALUES ('17', '孙五明', 'c4ca4238a0b923820dcc509a6f75849b', 'sunwm@wolfcode.cn', '25', '\0', '3');
INSERT INTO `employee` VALUES ('18', '李五明', 'c4ca4238a0b923820dcc509a6f75849b', 'liwm@wolfcode.cn', '25', '\0', '4');
INSERT INTO `employee` VALUES ('19', '李六明', 'c4ca4238a0b923820dcc509a6f75849b', 'lilm@wolfcode.cn', '25', '\0', '4');
INSERT INTO `employee` VALUES ('38', 'xiaohei', '123', '333@qq.com', '22', '\0', '1');
INSERT INTO `employee` VALUES ('39', 'xiaohei3', '$2a$10$kFp6OZCQt8gafI8bYIAGUeKKR9Sqch3qIs5i2yXKOHUlgAjmqpNMG', '333@qq.com', '22', '\0', '1');
INSERT INTO `employee` VALUES ('43', 'xiaohei666', '$2a$10$kFp6OZCQt8gafI8bYIAGUeKKR9Sqch3qIs5i2yXKOHUlgAjmqpNMG', '333@qq.com', '22', '\0', '1');
INSERT INTO `employee` VALUES ('44', '小明', '$2a$10$kFp6OZCQt8gafI8bYIAGUeKKR9Sqch3qIs5i2yXKOHUlgAjmqpNMG', '123456@qq.com', '22', '\0', '2');
INSERT INTO `employee` VALUES ('45', 'xiaohei2', '123', '123@qq.com', '22', '', '1');

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role` (
  `employee_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_role
-- ----------------------------
INSERT INTO `employee_role` VALUES ('38', '1');
INSERT INTO `employee_role` VALUES ('39', '2');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `expression` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('115', '角色列表', 'role:listAll');
INSERT INTO `permission` VALUES ('116', '权限分页列表', 'permission:list');
INSERT INTO `permission` VALUES ('117', '员工列表', 'employee:list');
INSERT INTO `permission` VALUES ('118', '部门信息', 'department:info');
INSERT INTO `permission` VALUES ('119', '角色信息', 'role:info');
INSERT INTO `permission` VALUES ('120', '更新管理员', 'employee:updateState');
INSERT INTO `permission` VALUES ('121', '部门分页列表', 'department:list');
INSERT INTO `permission` VALUES ('122', '角色保存或更新', 'role:saveOrUpdate');
INSERT INTO `permission` VALUES ('123', '部门删除', 'department:delete');
INSERT INTO `permission` VALUES ('124', '员工信息', 'employee:info');
INSERT INTO `permission` VALUES ('125', '权限列表', 'permission:listAll');
INSERT INTO `permission` VALUES ('126', '部门保存或更新', 'department:saveOrUpdate');
INSERT INTO `permission` VALUES ('127', '部门列表', 'department:listAll');
INSERT INTO `permission` VALUES ('128', '角色删除', 'role:delete');
INSERT INTO `permission` VALUES ('129', '角色分页列表', 'role:list');
INSERT INTO `permission` VALUES ('130', '员工删除', 'employee:delete');
INSERT INTO `permission` VALUES ('131', '员工保存或更新', 'employee:saveOrUpdate');
INSERT INTO `permission` VALUES ('132', '加载权限', 'permission:load');
INSERT INTO `permission` VALUES ('133', '查询权限', 'department:queryPermission');
INSERT INTO `permission` VALUES ('134', '角色信息', 'role:query');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `sn` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '人事管理', 'HR_MGR');
INSERT INTO `role` VALUES ('2', '采购管理', 'ORDER_MGR');
INSERT INTO `role` VALUES ('3', '仓储管理', 'WAREHOUSING_MGR');
INSERT INTO `role` VALUES ('4', '行政部管理', 'Admin_MGR');
INSERT INTO `role` VALUES ('11', '市场经理', 'Market_Manager');
INSERT INTO `role` VALUES ('12', '市场专员', 'Market');
INSERT INTO `role` VALUES ('14', '开发经理', 'DEV-MRG');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '123');
INSERT INTO `role_permission` VALUES ('1', '118');
INSERT INTO `role_permission` VALUES ('1', '121');
INSERT INTO `role_permission` VALUES ('1', '127');
INSERT INTO `role_permission` VALUES ('1', '133');
INSERT INTO `role_permission` VALUES ('1', '126');
INSERT INTO `role_permission` VALUES ('1', '130');
INSERT INTO `role_permission` VALUES ('1', '124');
INSERT INTO `role_permission` VALUES ('1', '117');
INSERT INTO `role_permission` VALUES ('1', '131');
INSERT INTO `role_permission` VALUES ('1', '119');
INSERT INTO `role_permission` VALUES ('1', '129');
INSERT INTO `role_permission` VALUES ('1', '115');
INSERT INTO `role_permission` VALUES ('2', '123');
INSERT INTO `role_permission` VALUES ('2', '118');
INSERT INTO `role_permission` VALUES ('2', '121');
INSERT INTO `role_permission` VALUES ('2', '127');
INSERT INTO `role_permission` VALUES ('2', '133');
INSERT INTO `role_permission` VALUES ('2', '126');
INSERT INTO `role_permission` VALUES ('2', '115');
