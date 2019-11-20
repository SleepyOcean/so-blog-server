-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `so_img`
--

DROP TABLE IF EXISTS `so_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `so_img` (
  `id` varchar(32) NOT NULL,
  `alias` varchar(255) DEFAULT NULL COMMENT '图片别名',
  `archive` varchar(255) DEFAULT NULL COMMENT '图片归档（多个以，分隔）',
  `associate_attribute` varchar(255) DEFAULT NULL COMMENT '关联属性，格式：【图片出处】：【图片使用位置】：是否可以单独删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `describe_info` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `img_format` varchar(8) DEFAULT NULL COMMENT '图片格式',
  `img_size` varchar(8) DEFAULT NULL COMMENT '图片大小',
  `location` varchar(255) DEFAULT NULL COMMENT '位置信息',
  `path` varchar(255) DEFAULT NULL COMMENT '本地存储相对路径',
  `resolution_ratio` varchar(16) DEFAULT NULL COMMENT '分辨率',
  `tags` varchar(255) DEFAULT NULL COMMENT '图片标签（多个以，分隔）',
  `type` varchar(16) DEFAULT NULL COMMENT '图片类别，如封面、照片、壁纸等等，用于图片保存路径的分配',
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `so_img`
--

LOCK TABLES `so_img` WRITE;
/*!40000 ALTER TABLE `so_img` DISABLE KEYS */;
INSERT INTO `so_img` VALUES ('8a80cb816e437a9b016e43c2a6e80004','《如何有效的向用户传递信息》内容-1',NULL,'文章内容:《如何有效的向用户传递信息》','2019-11-07 02:47:40',NULL,'png','362.24KB',NULL,'其他\\2019-11-07\\2d56b4247ab1486996c38d4e4941cdcd.png','1920 × 1080',NULL,'其他','2019-11-07 02:47:40'),('8a80cb816e437a9b016e43c3009e0005','《如何有效的向用户传递信息》封面',NULL,'文章封面:如何有效的向用户传递信息','2014-09-26 11:40:37',NULL,'jpg','1.64MB',NULL,'封面\\2019-11-07\\c4972731a00b4ebfbb65a68304fb4270.jpeg','1920 × 1080','文章封面','封面','2019-11-07 02:48:03');
/*!40000 ALTER TABLE `so_img` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-20 20:39:32
