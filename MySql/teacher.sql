/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : lfy
Target Host     : localhost:3306
Target Database : lfy
Date: 2022-01-16 14:31:48
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `name` varchar(20) NOT NULL DEFAULT '',
  `sex` varchar(30) DEFAULT NULL,
  `id` varchar(40) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `dept` varchar(50) DEFAULT NULL,
  `graSchool` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('孙海燕', '女', 'zzuli.teacher', '123sun', '111', '111');
INSERT INTO `teacher` VALUES ('王五', '男', 'zzuli.teacher', '123wang', null, null);
