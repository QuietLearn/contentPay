/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50554
Source Host           : 127.0.0.1:3306
Source Database       : guns

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2018-10-15 08:43:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cps_atlas
-- ----------------------------
DROP TABLE IF EXISTS `cps_atlas`;
CREATE TABLE `cps_atlas` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `account_id` int(10) DEFAULT NULL,
  `picId` int(11) DEFAULT NULL COMMENT '图片编号',
  `title` varchar(200) DEFAULT NULL COMMENT '图片名称',
  `description` text COMMENT '描述',
  `pay_points` int(10) DEFAULT NULL COMMENT '支付积分',
  `tags_ids` varchar(255) DEFAULT NULL COMMENT '标签类型',
  `cover_pic` varchar(255) DEFAULT NULL COMMENT '封面',
  `type_id` int(2) DEFAULT NULL COMMENT '图集类型',
  `number` int(11) DEFAULT NULL COMMENT '图集编号',
  `is_del` int(2) DEFAULT NULL,
  `picaddress` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `gmtcreated` datetime DEFAULT NULL,
  `gmtmodified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6514 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cps_atlas
-- ----------------------------

-- ----------------------------
-- Table structure for cps_chapter
-- ----------------------------
DROP TABLE IF EXISTS `cps_chapter`;
CREATE TABLE `cps_chapter` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `novelId` int(11) DEFAULT NULL COMMENT '小说编号',
  `number` int(11) DEFAULT NULL COMMENT '章节编号',
  `title` varchar(200) DEFAULT NULL COMMENT '章节名称',
  `content` mediumtext COMMENT '章节内容',
  `is_del` int(2) DEFAULT NULL '逻辑删除',
  `gmt_created` datetime DEFAULT NULL '创建时间',
  `gmt_modified` datetime DEFAULT NULL '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cps_chapter
-- ----------------------------

-- ----------------------------
-- Table structure for cps_novel
-- ----------------------------
DROP TABLE IF EXISTS `cps_novel`;
CREATE TABLE `cps_novel` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `account_id` int(10) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL COMMENT ' 小说标题',
  `pic_address` varchar(200) DEFAULT NULL COMMENT '小说封面',
  `type` int(2) DEFAULT NULL COMMENT '小说类别',
  `author` varchar(45) DEFAULT NULL COMMENT '作者名',
  `refresh_time` varchar(45) DEFAULT NULL COMMENT '更新时间',
  `status` varchar(10) DEFAULT NULL COMMENT '小说状态',
  `pay_points` int(10) DEFAULT NULL '支付积分',
  `description` text '描述',
  `is_del` int(2) DEFAULT NULL '逻辑删除',
  `gmt_created` datetime DEFAULT NULL '创建时间',
  `gmt_modified` datetime DEFAULT NULL '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cps_novel
-- ----------------------------

-- ----------------------------
-- Table structure for cps_picture
-- ----------------------------
DROP TABLE IF EXISTS `cps_picture`;
CREATE TABLE `cps_picture` (
  `id` int(10) NOT NULL AUTO_INCREMENT 'id',
  `account_id` int(10) DEFAULT NULL '用户',
  `title` varchar(200) DEFAULT NULL '标题',
  `is_del` int(2) DEFAULT NULL '逻辑删除',
  `description` text '描述',
  `gmt_created` datetime DEFAULT NULL '创建时间',
  `gmt_modified` datetime DEFAULT NULL '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4639 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cps_picture
-- ----------------------------

-- ----------------------------
-- Table structure for key_words
-- ----------------------------
DROP TABLE IF EXISTS `key_words`;
CREATE TABLE `key_words` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT 'id',
  `name` varchar(200) DEFAULT NULL COMMENT '模特名字',
  `is_del` int(2) DEFAULT NULL '逻辑删除',
  `gmt_created` datetime DEFAULT NULL '创建时间',
  `gmt_modified` datetime DEFAULT NULL '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of key_words
-- ----------------------------


-- ----------------------------
-- Table structure for pay_channel
-- ----------------------------
DROP TABLE IF EXISTS `pay_channel`;
CREATE TABLE `pay_channel` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT 'id',
  `name` varchar(45) DEFAULT NULL '名字',
  `pay_code` varchar(45) DEFAULT NULL '支付码',
  `pay_type` int(2) DEFAULT NULL COMMENT '支付渠道类型',
  `is_choose` int(2) DEFAULT NULL '选中',
  `sort` int(11) DEFAULT NULL COMMENT '支付先后顺序',
  `is_del` int(2) DEFAULT NULL '逻辑删除',
  `gmt_created` datetime DEFAULT NULL '创建时间',
  `gmt_modified` datetime DEFAULT NULL '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pay_channel
-- ----------------------------
INSERT INTO `pay_channel` VALUES ('0000000001', '第三方', '0', 'DSF', '2', '1', '1', '2018-06-14 18:16:50', '2018-06-15 15:18:55');
INSERT INTO `pay_channel` VALUES ('0000000002', '支付宝', '0', 'ZFB', '3', '0', '2', '2018-06-15 14:49:24', '2018-06-15 14:49:26');
INSERT INTO `pay_channel` VALUES ('0000000003', '微信', '0', 'WX', '4', '0', '3', '2018-06-15 15:19:47', '2018-06-15 15:19:47');

-- ----------------------------
-- Table structure for pay_points
-- ----------------------------
DROP TABLE IF EXISTS `pay_points`;
CREATE TABLE `pay_points` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT 'id',
  `accoutid` int(11) DEFAULT NULL COMMENT '用户id',
  `type` int(10) DEFAULT NULL COMMENT '积分购买类型',
  `is_del` int(2) DEFAULT NULL '逻辑删除',
  `gmt_created` datetime DEFAULT NULL '创建时间',
  `gmt_modified` datetime DEFAULT NULL '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pay_points
-- ----------------------------

-- ----------------------------
-- Table structure for service_orders
-- ----------------------------
DROP TABLE IF EXISTS `service_orders`;
CREATE TABLE `service_orders` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `accountId` int(11) DEFAULT NULL COMMENT '用户账号id',
  `channelId` varchar(30) DEFAULT NULL ,
  `pay_orderId` varchar(100) DEFAULT NULL COMMENT '支付订单号',
  `pay_type` int(2) DEFAULT NULL COMMENT '0 积分支付 1 pc支付宝 2 手机支付宝 3 是手机微信 4模豆支付 5 苹果内购',
  `pay_status` int(2) DEFAULT NULL COMMENT '支付状态　１未支付　２支付成功　３支付失败',
  `pay_money` varchar(100) DEFAULT NULL COMMENT '支付金额',
  `buy_goodsId` int(11) DEFAULT NULL COMMENT '商品id\n',
  `quantity` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL '价格',
  `description` text '描述',
  `days` int(11) DEFAULT NULL COMMENT '服务天数',
  `buy_type` int(2) DEFAULT NULL COMMENT '购买商品类型 1 会员　２　自定义商品 3积分商品  4 模豆提取码商品',
  `order_type` int(2) DEFAULT NULL COMMENT ' 1 代表 直接开通趣向资源包   2 代表商城购买商品  ',
  `goods_name` varchar(45) DEFAULT NULL COMMENT '商品名称 ',
  `goods_alias` varchar(45) DEFAULT NULL '商品图片',
  `points` int(11) DEFAULT NULL COMMENT '消费的积分',
  `extract` text COMMENT '提取网文本',
  `appId` varchar(45) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL '创建时间',
  `gmt_modified` datetime DEFAULT NULL '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service_orders
-- ----------------------------
INSERT INTO `service_orders` VALUES ('0000000001', '222', '22', '22', '22', '2', '462', '62462', '62642', '626', '6264', '6246', '1', '2', '546', '642', '64', '62462626', '6', '2018-06-22 17:53:00', '2018-06-22 17:53:03');
