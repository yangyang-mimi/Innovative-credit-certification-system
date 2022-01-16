/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : lfy
Target Host     : localhost:3306
Target Database : lfy
Date: 2022-01-16 14:31:41
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `name` varchar(30) NOT NULL DEFAULT '',
  `identification` varchar(40) DEFAULT NULL,
  `schoolID` varchar(50) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `id` varchar(30) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `teacher` varchar(40) DEFAULT NULL,
  `grade` varchar(30) DEFAULT NULL,
  `level` varchar(40) DEFAULT NULL,
  `dept` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('刘洋', '11111', '542013460427', '男', 'zzuli.student', '123liu', '孙海燕', '3', '小菜鸡', '软件学院');
INSERT INTO `student` VALUES ('刘飞阳', '222', '111', '男', '1', '123', '孙海燕', '102', '优秀', '软件学院');
