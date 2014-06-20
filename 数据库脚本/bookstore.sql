/*
MySQL Data Transfer
Source Host: localhost
Source Database: bookstore
Target Host: localhost
Target Database: bookstore
Date: 2013-6-30 9:41:04
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for bookinfo
-- ----------------------------
DROP TABLE IF EXISTS `bookinfo`;
CREATE TABLE `bookinfo` (
  `isbn` varchar(15) NOT NULL,
  `bookkind` varchar(4) default NULL,
  `bookname` varchar(40) default NULL,
  `price` float default NULL,
  `publisher` varchar(20) default NULL,
  `number` int(11) default NULL,
  PRIMARY KEY  (`isbn`),
  KEY `FK_bookkind` (`bookkind`),
  CONSTRAINT `FK_bookkind` FOREIGN KEY (`bookkind`) REFERENCES `booktype` (`bookkind`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for booktype
-- ----------------------------
DROP TABLE IF EXISTS `booktype`;
CREATE TABLE `booktype` (
  `bookkind` varchar(4) NOT NULL,
  `kindname` varchar(20) default NULL,
  PRIMARY KEY  (`bookkind`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(6) NOT NULL,
  `password` varchar(6) default NULL,
  `name` varchar(8) default NULL,
  `phone` varchar(13) default NULL,
  `idcard` varchar(18) default NULL,
  `photo` varchar(30) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `orderid` int(11) default NULL,
  `isbn` varchar(15) default NULL,
  `number` int(11) default NULL,
  `saleprice` float default NULL,
  KEY `FK_isbn` (`isbn`),
  KEY `FK_orderid` (`orderid`),
  CONSTRAINT `FK_orderid` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`),
  CONSTRAINT `FK_isbn` FOREIGN KEY (`isbn`) REFERENCES `bookinfo` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderid` int(11) NOT NULL auto_increment,
  `webuserid` int(11) default NULL,
  `status` varchar(4) default NULL,
  `name` varchar(8) default NULL,
  `email` varchar(20) default NULL,
  `phone` varchar(13) default NULL,
  `address` varchar(200) default NULL,
  `saletime` datetime default NULL,
  PRIMARY KEY  (`orderid`),
  KEY `FK_webuserid` (`webuserid`),
  CONSTRAINT `FK_webuserid` FOREIGN KEY (`webuserid`) REFERENCES `webuser` (`webuserid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for saleinfo
-- ----------------------------
DROP TABLE IF EXISTS `saleinfo`;
CREATE TABLE `saleinfo` (
  `isbn` varchar(15) default NULL,
  `number` int(11) default NULL,
  `saleprice` float default NULL,
  `saletime` datetime default NULL,
  KEY `FK_isbn2` (`isbn`),
  CONSTRAINT `FK_isbn2` FOREIGN KEY (`isbn`) REFERENCES `bookinfo` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for webuser
-- ----------------------------
DROP TABLE IF EXISTS `webuser`;
CREATE TABLE `webuser` (
  `webuserid` int(11) NOT NULL auto_increment,
  `webuser` varchar(6) default NULL,
  `password` varchar(6) default NULL,
  PRIMARY KEY  (`webuserid`),
  UNIQUE KEY `webuser` (`webuser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `bookinfo` VALUES ('2-777', 'T', '计算机应用基础', '24', '厦门大学出版社', '50');
INSERT INTO `bookinfo` VALUES ('2-777-333', 'T', 'C语言程序设计教程', '35', '厦门大学出版社', '50');
INSERT INTO `bookinfo` VALUES ('222-111', 'T', 'Visual Foxpro', '22', '厦门大学出版社', '50');
INSERT INTO `bookinfo` VALUES ('23-789000-1234', 'T', 'JAVA程序设计', '34', '电子工业出版社', '100');
INSERT INTO `booktype` VALUES ('A', '马列毛邓');
INSERT INTO `booktype` VALUES ('B', '哲学');
INSERT INTO `booktype` VALUES ('C', '社会科学');
INSERT INTO `booktype` VALUES ('D', '政治法律');
INSERT INTO `booktype` VALUES ('E', '军事');
INSERT INTO `booktype` VALUES ('F', '经济');
INSERT INTO `booktype` VALUES ('G', '文化教育');
INSERT INTO `booktype` VALUES ('H', '语言文字');
INSERT INTO `booktype` VALUES ('I', '文学');
INSERT INTO `booktype` VALUES ('J', '艺术');
INSERT INTO `booktype` VALUES ('K', '历史地理');
INSERT INTO `booktype` VALUES ('N', '自然科学');
INSERT INTO `booktype` VALUES ('O', '数理化');
INSERT INTO `booktype` VALUES ('P', '天文地球学');
INSERT INTO `booktype` VALUES ('Q', '生物学');
INSERT INTO `booktype` VALUES ('R', '医药');
INSERT INTO `booktype` VALUES ('S', '农业');
INSERT INTO `booktype` VALUES ('T', '计算机工业技术');
INSERT INTO `booktype` VALUES ('U', '交通');
INSERT INTO `booktype` VALUES ('V', '航天');
INSERT INTO `booktype` VALUES ('X', '环境安全');
INSERT INTO `booktype` VALUES ('Z', '综合');
INSERT INTO `login` VALUES ('1', 'admin', '111111', 'Tom', '110', '1234567890', '1.jpg');
