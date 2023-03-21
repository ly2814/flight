/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : flightdb

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 24/11/2022 10:46:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cityinfo
-- ----------------------------
DROP TABLE IF EXISTS `cityinfo`;
CREATE TABLE `cityinfo`  (
  `cityid` int NOT NULL,
  `cityname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`cityid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cityinfo
-- ----------------------------
INSERT INTO `cityinfo` VALUES (1, 'tianjn');
INSERT INTO `cityinfo` VALUES (2, 'beijing');
INSERT INTO `cityinfo` VALUES (3, 'shanghai');

-- ----------------------------
-- Table structure for flightinfo
-- ----------------------------
DROP TABLE IF EXISTS `flightinfo`;
CREATE TABLE `flightinfo`  (
  `flightid` int NOT NULL AUTO_INCREMENT,
  `flightnum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `flydate` date NOT NULL,
  `starttime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `flytime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `startcity` int NULL DEFAULT NULL,
  `endcity` int NULL DEFAULT NULL,
  `seatnum` int NOT NULL,
  PRIMARY KEY (`flightid`) USING BTREE,
  INDEX `startcity`(`startcity` ASC) USING BTREE,
  INDEX `endcity`(`endcity` ASC) USING BTREE,
  CONSTRAINT `endcity` FOREIGN KEY (`endcity`) REFERENCES `cityinfo` (`cityid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `startcity` FOREIGN KEY (`startcity`) REFERENCES `cityinfo` (`cityid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flightinfo
-- ----------------------------
INSERT INTO `flightinfo` VALUES (20, '003', '2022-11-07', '22:01', '1h', 2, 1, 20);
INSERT INTO `flightinfo` VALUES (21, '001', '2022-11-09', '21:07', '2h', 1, 2, 23);
INSERT INTO `flightinfo` VALUES (22, '004', '2022-11-08', 'pm16:54', 'pm16:54', 2, 1, 435);
INSERT INTO `flightinfo` VALUES (23, '005', '2022-11-08', 'pm20:46', '3h', 2, 3, 232);
INSERT INTO `flightinfo` VALUES (24, '123456', '2002-11-30', '12', '12', 1, 2, 200);
INSERT INTO `flightinfo` VALUES (25, '123456', '2002-11-30', '12', '12', 1, 2, 200);
INSERT INTO `flightinfo` VALUES (26, '053', '2022-11-18', '22:01', '1h', 2, 1, 20);
INSERT INTO `flightinfo` VALUES (27, '053', '2022-11-18', '22:01', '1h', 2, 1, 20);
INSERT INTO `flightinfo` VALUES (28, '153', '2022-11-18', '22:01', '1h', 2, 1, 20);
INSERT INTO `flightinfo` VALUES (29, '053', '2022-11-18', '22:01', '1h', 2, 1, 20);
INSERT INTO `flightinfo` VALUES (30, 'test11_22', '2022-11-21', '16:43', '2h', 1, 2, 211);
INSERT INTO `flightinfo` VALUES (31, 'test11_22', '2022-11-21', '16:43', '2h', 1, 2, 211);
INSERT INTO `flightinfo` VALUES (32, '1645', '2022-11-22', '12:21', '2h', 3, 2, 231);
INSERT INTO `flightinfo` VALUES (33, '1234', '2022-11-21', '12:34', '1.2h', 2, 1, 111);
INSERT INTO `flightinfo` VALUES (34, '111', '2022-11-21', '11', '11', 1, 3, 111);
INSERT INTO `flightinfo` VALUES (35, '1111', '2022-11-21', '12:23', '1h', 1, 2, 111);
INSERT INTO `flightinfo` VALUES (36, '053', '2022-11-18', '22:01', '1h', 2, 1, 20);

SET FOREIGN_KEY_CHECKS = 1;
