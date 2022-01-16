/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : lfy
Target Host     : localhost:3306
Target Database : lfy
Date: 2022-01-16 14:31:59
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for 回复留言
-- ----------------------------
DROP TABLE IF EXISTS `回复留言`;
CREATE TABLE `回复留言` (
  `teaName` varchar(40) NOT NULL DEFAULT '',
  `date` varchar(40) DEFAULT NULL,
  `stuName` varchar(40) DEFAULT NULL,
  `留言` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 回复留言
-- ----------------------------
INSERT INTO `回复留言` VALUES ('孙海燕', 'Tue Dec 21 13:32:10 CST 2021', '刘飞阳', '你好哇');
INSERT INTO `回复留言` VALUES ('孙海燕', 'Tue Dec 21 13:37:43 CST 2021', '刘洋', '你好哇');
