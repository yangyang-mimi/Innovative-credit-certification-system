/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : lfy
Target Host     : localhost:3306
Target Database : lfy
Date: 2022-01-16 14:32:15
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for 申请材料
-- ----------------------------
DROP TABLE IF EXISTS `申请材料`;
CREATE TABLE `申请材料` (
  `stuName` varchar(30) NOT NULL DEFAULT '',
  `teaName` varchar(40) DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  `projectName` varchar(50) DEFAULT NULL,
  `fenshu` varchar(30) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `file` mediumblob,
  `批阅情况` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 申请材料
-- ----------------------------
INSERT INTO `申请材料` VALUES ('刘洋', '孙海燕', '学术论文类', '基于虚拟现实的实现', '3', '2021/12/20', 0xE58898E9A39EE998B3E58898E9A39EE998B3E58898E9A39EE998B3, '未批阅');
INSERT INTO `申请材料` VALUES ('刘飞阳', '孙海燕', '科学技术类', '基于虚拟现实的演讲训练系统', '3', '2021/12/16', 0xE58898E9A39EE998B3E58898E9A39EE998B3E58898E9A39EE998B3, '通过');
INSERT INTO `申请材料` VALUES ('刘飞阳', '孙海燕', '学术论文类', '基于创新学分认证系统的实现', '2', '2021/12/22', 0xE58898E9A39EE998B3E58898E9A39EE998B3E58898E9A39EE998B3, '通过');
INSERT INTO `申请材料` VALUES ('刘飞阳', '孙海燕', '科学技术类', '111', '2', '2021/12/22', 0xE58898E9A39EE998B3E58898E9A39EE998B3E58898E9A39EE998B3, '通过');
