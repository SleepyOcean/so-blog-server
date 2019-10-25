-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `so_project`
--

DROP TABLE IF EXISTS `so_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `so_project` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `deadline` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `module_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `delete_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `so_project`
--

LOCK TABLES `so_project` WRITE;
/*!40000 ALTER TABLE `so_project` DISABLE KEYS */;
INSERT INTO `so_project` VALUES ('2c9020f86b976145016b97b12d150000','2019-06-27 14:48:19','2019-06-27','实现博客footer样式设计','footer版本1.0','SO博客前台',1,'2019-06-27 17:01:34',0),('2c9020f86b92e8ce016b92f0c80b002b','2019-06-26 16:39:41','2019-06-28','实现博客文章页面设计','按设计稿实现SO博客文章页面','SO博客前台',1,'2019-06-28 16:58:53',0),('2c9020f86b92e8ce016b92edc8c10029','2019-06-26 16:36:25','2019-06-26','项目管理模块','该模块用于管理项目开发进度','SO博客后台',1,'2019-06-26 16:36:33',0),('2c9020f86b98ac13016b98ade9b50000','2019-06-26 17:24:22','2019-06-27','实现博客首页设计','按设计稿实现SO博客首页','SO博客前台',1,'2019-06-27 14:26:15',0),('2c9020f86bb12ff5016bb201aeee0002','2019-07-02 17:26:23','2019-07-09','标签字段管理','实现标签相关操作接口','SO博客后台',1,'2019-07-09 19:51:18',0),('2c9020f86bb12ff5016bb202b9d40003','2019-07-02 17:27:31','2019-07-10','首页文章列表分页','注意：列表长度不要太长','SO博客前台',0,NULL,1),('2c9020f86bb249d4016bb24d23980000','2019-07-02 18:48:48','2019-07-03','数据库版本控制','在后台集成flyway','SO博客后台',1,'2019-07-04 15:06:08',0),('2c9020f86bbbcf2b016bbbd721c20000','2019-07-04 15:16:06','2019-07-08','博文存储转至ES','先转至ES，再做标签字段管理','SO博客后台',1,'2019-07-08 16:24:54',0),('2c9020f86c03fec7016c0403b5950000','2019-07-18 15:37:27','2019-07-18','相关文章接口','实现相关文章推荐接口','SO博客后台',1,'2019-07-18 15:37:30',0),('2c9020f86c03fec7016c040499020001','2019-07-18 15:38:25','2019-07-20','文章全局搜索接口','实现文章全局搜索','SO博客后台',1,'2019-08-08 16:05:22',0),('2c9020f86c03fec7016c04057e140002','2019-07-18 15:39:24','2019-07-19','首页文章列表分页','实现首页文章列表分页','SO博客前台',1,'2019-07-19 15:29:54',0),('2c9020f86c03fec7016c040643850003','2019-07-18 15:40:15','2019-07-20','关于页面','关于页面（包含SO博客系统介绍和自我介绍）','SO博客前台',1,'2019-08-08 16:05:24',0),('2c9020f86c07d3fa016c095669c40000','2019-07-19 16:25:53','2019-07-21','后台管理-文章管理页面','功能：搜索、查看、编辑、修改、删除','SO博客前台',1,'2019-08-08 16:05:06',0),('2c9020f86c07d3fa016c0958bc0b0001','2019-07-19 16:28:25','2019-07-21','后台管理-文章编写/编辑页面','功能：添加封面（默认取文章中的第一张图片）、添加摘要/副标题（默认从文章的<more>标签中获取）、添加主题/专栏、添加文章来源、文章可见','SO博客前台',1,'2019-08-08 16:05:03',0),('2c9020f86c07d3fa016c095b23a20002','2019-07-19 16:31:03','2019-10-11','后台管理-仪表盘页面','功能：专栏展示、TODO计划栏、恢复文章编写（文章历史纪录）、文章提交日统计（柱状）图、访问量统计（折线）图、标签展示（饼状）图、访问数、文章总数、转发数、评论数、关注数','SO博客前台',1,'2019-10-11 17:15:58',0),('8a80cb816c6fa168016c703d4b880000','2019-08-08 15:59:20','2019-08-08','后台管理-项目管理-页面重构','重构后台管理-项目管理页面','SO博客前台',1,'2019-08-08 16:00:16',0),('8a80cb816db33e25016db57e12710000','2019-10-10 19:46:41','2019-10-10','数据定时备份接口及任务','提供备份数据接口；设置备份数据定时任务','SO博客后台',1,'2019-10-10 19:46:47',0),('8a80cb816db8d2ac016db8d7a9850000','2019-10-11 11:23:24','2019-10-12','文章编写页面重构','文章页面编写逻辑重构，提供良好的交互接口','SO博客前台',1,'2019-10-21 17:08:24',0);
/*!40000 ALTER TABLE `so_project` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-25 16:22:58
