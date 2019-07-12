/*
 Navicat Premium Data Transfer

 Source Server         : kd8832
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 12/07/2019 17:14:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for so_project
-- ----------------------------
DROP TABLE IF EXISTS `so_project`;
CREATE TABLE `so_project`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deadline` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `module_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `update_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `delete_flag` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of so_project
-- ----------------------------
INSERT INTO `so_project` VALUES ('2c9020f86b976145016b97b12d150000', '2019-06-27 14:48:19', '2019-06-27', '实现博客footer样式设计', 'footer版本1.0', 'SO博客前台', 1, '2019-06-27 17:01:34', 0);
INSERT INTO `so_project` VALUES ('2c9020f86b92e8ce016b92f0c80b002b', '2019-06-26 16:39:41', '2019-06-28', '实现博客文章页面设计', '按设计稿实现SO博客文章页面', 'SO博客前台', 1, '2019-06-28 16:58:53', 0);
INSERT INTO `so_project` VALUES ('2c9020f86b92e8ce016b92edc8c10029', '2019-06-26 16:36:25', '2019-06-26', '项目管理模块', '该模块用于管理项目开发进度', 'SO博客后台', 1, '2019-06-26 16:36:33', 0);
INSERT INTO `so_project` VALUES ('2c9020f86b98ac13016b98ade9b50000', '2019-06-26 17:24:22', '2019-06-27', '实现博客首页设计', '按设计稿实现SO博客首页', 'SO博客前台', 1, '2019-06-27 14:26:15', 0);
INSERT INTO `so_project` VALUES ('2c9020f86bb12ff5016bb201aeee0002', '2019-07-02 17:26:23', '2019-07-09', '标签字段管理', '实现标签相关操作接口', 'SO博客后台', 1, '2019-07-09 19:51:18', 0);
INSERT INTO `so_project` VALUES ('2c9020f86bb12ff5016bb202b9d40003', '2019-07-02 17:27:31', '2019-07-10', '首页文章列表分页', '注意：列表长度不要太长', 'SO博客前台', 0, NULL, 1);
INSERT INTO `so_project` VALUES ('2c9020f86bb249d4016bb24d23980000', '2019-07-02 18:48:48', '2019-07-03', '数据库版本控制', '在后台集成flyway', 'SO博客后台', 1, '2019-07-04 15:06:08', 0);
INSERT INTO `so_project` VALUES ('2c9020f86bbbcf2b016bbbd721c20000', '2019-07-04 15:16:06', '2019-07-08', '博文存储转至ES', '先转至ES，再做标签字段管理', 'SO博客后台', 1, '2019-07-08 16:24:54', 0);

SET FOREIGN_KEY_CHECKS = 1;
