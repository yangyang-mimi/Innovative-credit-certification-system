/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : lfy
Target Host     : localhost:3306
Target Database : lfy
Date: 2022-01-16 14:31:16
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `name` varchar(20) NOT NULL DEFAULT '',
  `id` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('管理员', '123', '123');
