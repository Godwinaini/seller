/*
 Navicat Premium Data Transfer

 Source Server         : demo
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : seller

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 15/03/2021 16:53:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `detail_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8, 2) NOT NULL COMMENT '当前价格,单位分',
  `product_quantity` int(11) NOT NULL COMMENT '数量',
  `product_icon` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小图',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('111001', '05318888', '188101', '把子肉', 8.00, 100, '***.jpg', '2021-01-27 16:47:27', '2021-01-27 16:47:27');
INSERT INTO `order_detail` VALUES ('111002', '05318888', '188101', '把子肉', 8.00, 100, '***.jpg', '2021-01-27 16:47:46', '2021-01-27 16:47:46');
INSERT INTO `order_detail` VALUES ('1611742910661822935', '1611742908921447989', '188101', '把子肉', 8.00, 1, '***.jpg', '2021-01-27 18:21:50', '2021-01-27 18:21:50');
INSERT INTO `order_detail` VALUES ('1611743666970901854', '1611743665209727682', '188101', '把子肉', 8.00, 1, '***.jpg', '2021-01-27 18:34:26', '2021-01-27 18:34:26');
INSERT INTO `order_detail` VALUES ('1611744112382425295', '1611744110684598774', '188101', '把子肉', 8.00, 1, '***.jpg', '2021-01-27 18:41:52', '2021-01-27 18:41:52');
INSERT INTO `order_detail` VALUES ('1611744176709255436', '1611744174745407556', '188101', '把子肉', 8.00, 1, '***.jpg', '2021-01-27 18:42:56', '2021-01-27 18:42:56');
INSERT INTO `order_detail` VALUES ('1611744259487858212', '1611744257817926602', '188101', '把子肉', 8.00, 1, '***.jpg', '2021-01-27 18:44:19', '2021-01-27 18:44:19');
INSERT INTO `order_detail` VALUES ('1611803310939693335', '1611803309177787307', '188101', '把子肉', 8.00, 1, '***.jpg', '2021-01-28 11:08:30', '2021-01-28 11:08:30');
INSERT INTO `order_detail` VALUES ('1611824644232873701', '1611824644138106290', '188102', '藤椒鸡腿', 5.00, 1, '***.jpg', '2021-01-28 17:04:04', '2021-01-28 17:04:04');
INSERT INTO `order_detail` VALUES ('1611824742636674535', '1611824742452316139', '188102', '藤椒鸡腿', 5.00, 1, '***.jpg', '2021-01-28 17:05:42', '2021-01-28 17:05:42');
INSERT INTO `order_detail` VALUES ('1614668274411962800', '1614668274269543899', '188105', '糖醋里脊', 42.00, 1, '***.jpg', '2021-03-02 14:57:54', '2021-03-02 14:57:54');
INSERT INTO `order_detail` VALUES ('1615356741222698257', '1615356741113697183', '188105', '糖醋里脊', 42.00, 1, '***.jpg', '2021-03-10 14:12:21', '2021-03-10 14:12:21');
INSERT INTO `order_detail` VALUES ('1615357082029147633', '1615357082019664864', '188104', '甜筒', 0.01, 1, '***.jpg', '2021-03-10 14:18:02', '2021-03-10 14:18:02');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master`  (
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `buyer_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8, 2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '支付状态, 默认未支付',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `idx_buyer_openid`(`buyer_openid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('05318888', '刘亦菲', '18888888', 'China', '233666', 1.80, 0, 0, '2021-01-27 16:41:54', '2021-01-28 18:08:40');
INSERT INTO `order_master` VALUES ('1611742908921447989', '欧阳娜娜', '15566666', 'China', '110110', 8.00, 0, 0, '2021-01-27 18:21:50', '2021-03-10 14:14:32');
INSERT INTO `order_master` VALUES ('1611743665209727682', '欧阳娜娜', '15566666', 'China', '110110', 8.00, 0, 0, '2021-01-27 18:34:27', '2021-01-27 18:34:27');
INSERT INTO `order_master` VALUES ('1611744110684598774', '欧阳娜娜', '15566666', 'China', '110110', 8.00, 0, 0, '2021-01-27 18:41:52', '2021-01-27 18:41:52');
INSERT INTO `order_master` VALUES ('1611744174745407556', '欧阳娜娜', '15566666', 'China', '110110', 8.00, 0, 0, '2021-01-27 18:42:56', '2021-01-27 18:42:56');
INSERT INTO `order_master` VALUES ('1611744257817926602', '欧阳娜娜', '15566666', 'China', '110110', 8.00, 0, 0, '2021-01-27 18:44:19', '2021-01-27 18:44:19');
INSERT INTO `order_master` VALUES ('1611803309177787307', '欧阳娜娜', '15566666', 'China', '110110', 8.00, 0, 0, '2021-01-28 11:08:31', '2021-01-28 11:08:31');
INSERT INTO `order_master` VALUES ('1611824644138106290', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 5.00, 0, 0, '2021-01-28 17:04:04', '2021-01-28 17:04:04');
INSERT INTO `order_master` VALUES ('1611824742452316139', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 5.00, 0, 0, '2021-01-28 17:05:42', '2021-01-28 17:05:42');
INSERT INTO `order_master` VALUES ('1614668274269543899', '万茜', '15566666', 'China', '110110', 42.00, 0, 0, '2021-03-02 14:57:54', '2021-03-10 14:14:13');
INSERT INTO `order_master` VALUES ('1615356741113697183', '万茜', '15566666', 'China', '110110', 42.00, 0, 0, '2021-03-10 14:12:21', '2021-03-10 14:12:21');
INSERT INTO `order_master` VALUES ('1615357082019664864', 'fly', '1888', 'earth', '110110', 0.01, 0, 0, '2021-03-10 14:18:02', '2021-03-10 14:53:43');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类目名字',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE INDEX `uqe_category_type`(`category_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (1, '热销榜', 1, '2021-01-26 11:02:28', '2021-01-27 11:10:10');
INSERT INTO `product_category` VALUES (2, '女生喜欢', 2, '2021-01-26 11:09:55', '2021-01-27 11:10:16');
INSERT INTO `product_category` VALUES (3, '男生喜欢', 3, '2021-01-26 11:11:03', '2021-01-27 11:10:21');
INSERT INTO `product_category` VALUES (4, '学生喜欢', 4, '2021-01-26 14:13:43', '2021-01-27 11:10:29');
INSERT INTO `product_category` VALUES (5, '家长喜欢', 5, '2021-01-26 14:45:05', '2021-01-27 11:10:35');
INSERT INTO `product_category` VALUES (6, '老师喜欢', 6, '2021-01-26 16:01:13', '2021-01-27 11:10:55');
INSERT INTO `product_category` VALUES (7, '情侣喜欢', 7, '2021-01-26 17:15:55', '2021-01-27 11:11:00');
INSERT INTO `product_category` VALUES (8, '卖的喜欢', 8, '2021-01-26 18:09:33', '2021-01-27 11:11:06');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info`  (
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8, 2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小图',
  `product_status` tinyint(3) NULL DEFAULT 0 COMMENT '商品状态,0正常1下架',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('188101', '把子肉', 8.00, 301, '三口一块', '***.jpg', 0, 1, '2021-01-27 11:12:25', '2021-03-02 14:58:42');
INSERT INTO `product_info` VALUES ('188102', '藤椒鸡腿', 5.00, 98, '吃着麻', '***.jpg', 0, 1, '2021-01-27 11:13:55', '2021-01-28 17:05:42');
INSERT INTO `product_info` VALUES ('188103', '红烧铁狮子头', 28.00, 100, '吃着爽', '***.jpg', 0, 1, '2021-01-27 11:14:44', '2021-01-27 11:14:44');
INSERT INTO `product_info` VALUES ('188104', '甜筒', 0.01, 200, '一口一个', '***.jpg', 0, 1, '2021-01-27 11:15:22', '2021-03-10 14:53:33');
INSERT INTO `product_info` VALUES ('188105', '糖醋里脊', 42.00, 100, '酸甜可口', '***.jpg', 0, 1, '2021-01-27 14:13:01', '2021-03-10 14:13:28');
INSERT INTO `product_info` VALUES ('188106', '大盆鸡', 98.00, 100, '香辣可口', '***.jpg', 0, 1, '2021-01-27 14:30:40', '2021-01-27 15:07:30');

-- ----------------------------
-- Table structure for seller_info
-- ----------------------------
DROP TABLE IF EXISTS `seller_info`;
CREATE TABLE `seller_info`  (
  `seller_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信openid',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`seller_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '卖家信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seller_info
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
