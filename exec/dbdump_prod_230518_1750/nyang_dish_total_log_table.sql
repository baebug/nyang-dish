-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: k8e2031.p.ssafy.io    Database: nyang
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dish_total_log_table`
--

DROP TABLE IF EXISTS `dish_total_log_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish_total_log_table` (
  `dish_total_log_id` bigint NOT NULL AUTO_INCREMENT,
  `dish_id` bigint NOT NULL,
  `date` date NOT NULL,
  `battery_amount` int NOT NULL,
  `food_amount` int NOT NULL,
  `cat_count` int NOT NULL,
  `tnr_count` int NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `created_date` timestamp NOT NULL,
  `updated_date` timestamp NOT NULL,
  PRIMARY KEY (`dish_total_log_id`),
  KEY `dish_id` (`dish_id`),
  CONSTRAINT `dish_total_log_table_ibfk_1` FOREIGN KEY (`dish_id`) REFERENCES `dish_table` (`dish_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish_total_log_table`
--

LOCK TABLES `dish_total_log_table` WRITE;
/*!40000 ALTER TABLE `dish_total_log_table` DISABLE KEYS */;
INSERT INTO `dish_total_log_table` VALUES (1,1,'2023-05-02',100,70,5,3,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(2,2,'2023-05-02',100,70,5,3,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(3,3,'2023-05-02',100,70,5,3,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(4,1,'2023-05-03',80,40,6,3,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(5,2,'2023-05-03',80,40,6,3,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(6,3,'2023-05-03',80,40,6,3,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(7,1,'2023-05-04',40,90,6,3,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(8,2,'2023-05-04',40,90,6,3,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(9,3,'2023-05-04',40,90,6,3,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(10,1,'2023-05-05',90,60,6,4,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(11,2,'2023-05-05',90,60,6,4,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(12,3,'2023-05-05',90,60,6,4,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(13,1,'2023-05-06',70,35,5,4,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(14,2,'2023-05-06',70,35,5,4,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(15,3,'2023-05-06',70,35,5,4,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(16,1,'2023-05-07',20,100,5,3,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(17,2,'2023-05-07',20,100,5,3,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(18,3,'2023-05-07',20,100,5,3,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(19,1,'2023-05-08',90,85,5,4,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(20,2,'2023-05-08',90,85,5,4,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(21,3,'2023-05-08',90,85,5,4,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(22,1,'2023-05-09',70,60,6,4,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(23,2,'2023-05-09',70,60,6,4,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(24,3,'2023-05-09',70,60,6,4,0,'2023-05-10 01:11:28','2023-05-10 01:11:28'),(25,1,'2023-05-10',100,100,0,0,0,'2023-05-10 13:00:00','2023-05-17 07:14:31'),(26,2,'2023-05-10',50,40,0,0,0,'2023-05-10 13:00:00','2023-05-17 07:13:55'),(27,3,'2023-05-10',20,10,0,0,0,'2023-05-10 13:00:00','2023-05-17 07:13:19'),(28,1,'2023-05-11',100,100,0,0,0,'2023-05-11 13:00:00','2023-05-17 07:14:33'),(29,2,'2023-05-11',50,40,0,0,0,'2023-05-11 13:00:00','2023-05-17 07:13:58'),(30,3,'2023-05-11',20,10,0,0,0,'2023-05-11 13:00:00','2023-05-17 07:13:17'),(31,1,'2023-05-12',70,100,2,0,0,'2023-05-12 13:00:00','2023-05-17 08:11:54'),(32,2,'2023-05-12',40,47,3,0,0,'2023-05-12 13:00:00','2023-05-18 04:55:02'),(33,3,'2023-05-12',90,40,2,0,0,'2023-05-12 13:00:00','2023-05-17 02:10:22'),(34,1,'2023-05-13',40,75,3,0,0,'2023-05-13 13:00:00','2023-05-17 08:12:20'),(35,2,'2023-05-13',15,15,2,0,0,'2023-05-13 13:00:00','2023-05-17 01:50:15'),(36,3,'2023-05-13',65,15,2,0,0,'2023-05-13 13:00:00','2023-05-17 02:20:24'),(37,1,'2023-05-14',10,52,2,0,0,'2023-05-14 13:00:00','2023-05-17 06:19:35'),(38,2,'2023-05-14',97,92,2,0,0,'2023-05-14 13:00:00','2023-05-17 02:06:44'),(39,3,'2023-05-14',40,93,3,0,0,'2023-05-14 13:00:00','2023-05-17 02:25:18'),(40,1,'2023-05-15',95,41,2,1,0,'2023-05-15 13:00:00','2023-05-18 02:30:34'),(41,2,'2023-05-15',72,60,0,0,0,'2023-05-15 13:00:01','2023-05-17 07:14:06'),(42,3,'2023-05-15',12,65,0,0,0,'2023-05-15 13:00:01','2023-05-17 07:13:04'),(43,1,'2023-05-16',65,19,0,0,0,'2023-05-16 13:00:05','2023-05-17 07:14:41'),(44,2,'2023-05-16',50,38,0,0,0,'2023-05-16 13:00:05','2023-05-17 07:14:09'),(45,3,'2023-05-16',100,40,4,0,0,'2023-05-16 13:00:05','2023-05-18 08:25:25'),(46,1,'2023-05-17',100,100,0,0,0,'2023-05-17 13:00:00','2023-05-18 03:20:13'),(47,2,'2023-05-17',50,40,2,0,0,'2023-05-17 13:00:00','2023-05-18 03:19:49'),(48,3,'2023-05-17',20,10,3,0,0,'2023-05-17 13:00:00','2023-05-17 13:00:00');
/*!40000 ALTER TABLE `dish_total_log_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-18 17:50:53
