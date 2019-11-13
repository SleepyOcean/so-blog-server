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
-- Table structure for table `so_setting`
--

DROP TABLE IF EXISTS `so_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `so_setting` (
  `id` varchar(32) NOT NULL,
  `config_info` varchar(255) DEFAULT NULL COMMENT '配置项的备注',
  `config_key` varchar(255) DEFAULT NULL COMMENT '配置项的键',
  `config_value` varchar(255) DEFAULT NULL COMMENT '配置项的值',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `so_setting`
--

LOCK TABLES `so_setting` WRITE;
/*!40000 ALTER TABLE `so_setting` DISABLE KEYS */;
INSERT INTO `so_setting` VALUES ('8a80cb816e34d27d016e34d355aa0000','图片服务器地址','ImageServerUrl','http://localhost:9999/resource/img/'),('8a80cb816e34d27d016e34d44fb10001','图片服务器中图片保存路径','ImageLocalPath','E:\\ImageServer\\'),('8a80cb816e34d27d016e34d49c860002','是否启用ES','esEnable','false'),('8a80cb816e437a9b016e4381996d0000','是否使用后台服务，false时为离线模式','searchOnline','true'),('8a80cb816e437a9b016e43893bd90001','是否启用自动备份','autoBackup','false'),('8a80cb816e437a9b016e438aad480002','自动备份地址，不指定则默认备份到本地','autoBackupAddress',''),('8a80cb816e437a9b016e438bb14f0003','自动备份周期，默认1天','autoBackupCycle','1天');
/*!40000 ALTER TABLE `so_setting` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-08 17:05:30
