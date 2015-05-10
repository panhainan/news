/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : news

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-04-25 14:48:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `adminName` varchar(20) NOT NULL,
  `adminPass` varchar(20) NOT NULL,
  PRIMARY KEY (`adminName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('phn', '123456');

-- ----------------------------
-- Table structure for `t_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` smallint(2) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(20) DEFAULT NULL,
  `categoryPriority` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category
-- ----------------------------

-- ----------------------------
-- Table structure for `t_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `commentIP` varchar(20) DEFAULT NULL,
  `commentAddress` varchar(20) DEFAULT NULL,
  `commentContent` varchar(255) DEFAULT NULL,
  `commentPublishTime` datetime DEFAULT NULL,
  `commentNewsId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cnewsid` (`commentNewsId`),
  CONSTRAINT `cnewsid` FOREIGN KEY (`commentNewsId`) REFERENCES `t_news` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `t_news`
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `newsTitle` varchar(30) DEFAULT NULL,
  `newsAuthor` varchar(20) DEFAULT NULL,
  `newsAuthorSite` varchar(30) DEFAULT NULL,
  `newsPublishTime` datetime DEFAULT NULL,
  `newsContent` text,
  `newsIsPicture` smallint(1) DEFAULT NULL,
  `newsPictureSite` varchar(30) DEFAULT NULL,
  `newsIsTopLine` smallint(1) DEFAULT NULL,
  `newsIsHot` smallint(1) DEFAULT NULL,
  `newsHitCount` int(6) DEFAULT NULL,
  `newsCategoryId` smallint(2) DEFAULT NULL,
  `newsCommentCount` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ncategoryid` (`newsCategoryId`),
  CONSTRAINT `ncategoryid` FOREIGN KEY (`newsCategoryId`) REFERENCES `t_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_news
-- ----------------------------
