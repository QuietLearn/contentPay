/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.21-log : Database - cps
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cps` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cps`;

/*Table structure for table `cps_category` */

DROP TABLE IF EXISTS `cps_category`;

CREATE TABLE `cps_category` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '分类名',
  `desc` text COMMENT '描述',
  `pid` int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级分类',
  `pids` int(11) DEFAULT NULL COMMENT '包括父类的父类的父类，一直到根节点(方便查找所有子分类，不用递归)',
  `sort` int(6) DEFAULT NULL COMMENT '排序',
  `is_del` tinyint(2) DEFAULT NULL COMMENT '逻辑删除',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分类表';

/*Data for the table `cps_category` */

/*Table structure for table `cps_favorite` */

DROP TABLE IF EXISTS `cps_favorite`;

CREATE TABLE `cps_favorite` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `resouce_id` int(11) DEFAULT NULL COMMENT '资源id',
  `member_id` int(11) DEFAULT NULL COMMENT '注册用户id',
  `type` varchar(36) DEFAULT NULL COMMENT '资源类型 100 视频 101 小说 102 图集',
  `is_del` int(2) DEFAULT NULL COMMENT '逻辑删除',
  `add_time` datetime DEFAULT NULL COMMENT '收藏时间',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏夹表 分别查询三个表(先分别查出每个表资源的ids，在用in查询)';

/*Data for the table `cps_favorite` */

/*Table structure for table `cps_level` */

DROP TABLE IF EXISTS `cps_level`;

CREATE TABLE `cps_level` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `diamond_level` int(11) DEFAULT NULL COMMENT '钻石等级',
  `max_amount` int(11) DEFAULT NULL COMMENT '所需最高消费金额(RMB)',
  `min_amount` int(11) DEFAULT NULL COMMENT '所需最底消费金额(RMB)',
  `is_del` int(2) DEFAULT NULL COMMENT '逻辑删除 0是 1否',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员等级表';

/*Data for the table `cps_level` */

/*Table structure for table `cps_member` */

DROP TABLE IF EXISTS `cps_member`;

CREATE TABLE `cps_member` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `password` varchar(200) DEFAULT NULL COMMENT '明文密码',
  `Md5_password` varchar(60) DEFAULT NULL COMMENT 'md5加密密码',
  `gender` int(2) DEFAULT NULL COMMENT '性别',
  `introduction` text COMMENT '简介',
  `pic_address` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '头像图片地址',
  `mobile` varchar(45) DEFAULT NULL COMMENT '手机',
  `focus_count` int(11) DEFAULT NULL COMMENT '关注数',
  `fans_count` int(11) DEFAULT NULL COMMENT '粉丝数',
  `hit_count` int(11) DEFAULT NULL COMMENT '点击数',
  `qq` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `weixin` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `member_type_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '会员类型 表id',
  `member_type_name` varchar(45) DEFAULT NULL COMMENT '会员类型名称',
  `vip_level` int(11) DEFAULT NULL COMMENT '会员等级',
  `buy_time` datetime DEFAULT NULL COMMENT '购买时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `aging` int(11) DEFAULT NULL COMMENT '有效时间',
  `points` int(11) DEFAULT NULL COMMENT '积分',
  `user_level` int(11) DEFAULT NULL COMMENT '用户等级',
  `experience` int(11) DEFAULT NULL COMMENT '用户经验',
  `favorites_ids` text COMMENT '收藏夹ids ，分隔',
  `last_login` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_loginIp` varchar(45) DEFAULT NULL COMMENT '最后登录ip',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `register_ip` varchar(50) DEFAULT NULL COMMENT '注册ip',
  `UUID_token` varchar(100) DEFAULT NULL,
  `total_money` int(11) DEFAULT NULL COMMENT '总消费额',
  `is_lock` int(2) DEFAULT NULL COMMENT '是否锁定 0 是 １否',
  `is_del` int(2) DEFAULT NULL COMMENT '逻辑删除 0 是 １否',
  `gmt_receive` datetime DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_type_name` (`member_type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cps_member` */

/*Table structure for table `cps_member_type` */

DROP TABLE IF EXISTS `cps_member_type`;

CREATE TABLE `cps_member_type` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `server_code` varchar(45) NOT NULL COMMENT ' 1001 包月  1002 包年',
  `server_name` varchar(45) DEFAULT NULL COMMENT '服务名称',
  `price` decimal(20,2) NOT NULL COMMENT '购买单价',
  `aging` datetime DEFAULT NULL COMMENT '有效持续时间',
  `is_del` int(2) DEFAULT NULL COMMENT '逻辑删除',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `server_code` (`server_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员购买详情表_待定（一种商品/付费类型）';

/*Data for the table `cps_member_type` */

/*Table structure for table `cps_photos` */

DROP TABLE IF EXISTS `cps_photos`;

CREATE TABLE `cps_photos` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `upload_member_id` int(11) DEFAULT NULL COMMENT '上传用户id',
  `title` varchar(60) DEFAULT NULL COMMENT '图集标题名',
  `catgory_id` int(11) DEFAULT NULL COMMENT '类型id',
  `req_points` int(11) DEFAULT NULL COMMENT '所需积分',
  `tag_ids` text COMMENT '标签ids  ，分隔',
  `pic_address` mediumtext COMMENT '封面图片url',
  `download_url` mediumtext COMMENT '下载地址',
  `is_del` tinyint(2) DEFAULT NULL COMMENT '逻辑删除',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `upload_member_id` (`upload_member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cps_photos` */

/*Table structure for table `cps_tag` */

DROP TABLE IF EXISTS `cps_tag`;

CREATE TABLE `cps_tag` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(100) DEFAULT NULL COMMENT '标签名',
  `use_num` int(16) DEFAULT NULL COMMENT '使用相关数',
  `related_cont_id` int(11) DEFAULT NULL COMMENT '相关内容id (资源id)',
  PRIMARY KEY (`id`),
  KEY `tag_name` (`tag_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';

/*Data for the table `cps_tag` */

/*Table structure for table `cps_user_level` */

DROP TABLE IF EXISTS `cps_user_level`;

CREATE TABLE `cps_user_level` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_level` int(11) DEFAULT NULL COMMENT '等级  0-150',
  `max_experience` bigint(64) DEFAULT NULL COMMENT '所需最高经验 100- 900000000',
  `min_experience` bigint(64) DEFAULT NULL COMMENT '所需最低经验 0-  700000000',
  `is_del` int(2) DEFAULT NULL COMMENT '逻辑删除 0是 1否',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户等级表';

/*Data for the table `cps_user_level` */

/*Table structure for table `cps_video` */

DROP TABLE IF EXISTS `cps_video`;

CREATE TABLE `cps_video` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `upload_member_id` int(11) DEFAULT NULL COMMENT '上传用户id',
  `title` varchar(255) DEFAULT NULL COMMENT '视频名称',
  `desc` text COMMENT '视频简介(描述)',
  `pic_address` mediumtext COMMENT '视频封面图片url',
  `req_points` int(11) DEFAULT NULL COMMENT '所需积分',
  `digg_num` int(11) DEFAULT NULL COMMENT '推荐数',
  `tread_num` int(11) DEFAULT NULL COMMENT '踩 数量',
  `collect_num` int(11) DEFAULT NULL COMMENT '收藏数',
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  `source_url` mediumtext COMMENT '下载地址1',
  `source_url1` mediumtext COMMENT '下载地址2',
  `is_del` int(2) DEFAULT NULL COMMENT '逻辑删除',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cps_video` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
