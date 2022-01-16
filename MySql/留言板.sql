/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : lfy
Target Host     : localhost:3306
Target Database : lfy
Date: 2022-01-16 14:32:07
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for 留言板
-- ----------------------------
DROP TABLE IF EXISTS `留言板`;
CREATE TABLE `留言板` (
  `name` varchar(30) NOT NULL DEFAULT '',
  `date` varchar(30) DEFAULT NULL,
  `留言` varchar(5000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 留言板
-- ----------------------------
INSERT INTO `留言板` VALUES ('罗舒展', 'Sat Dec 18 17:19:25 CST 2021', '123');
INSERT INTO `留言板` VALUES ('刘飞阳', 'Tue Dec 21 10:16:31 CST 2021', '尚未确定');
