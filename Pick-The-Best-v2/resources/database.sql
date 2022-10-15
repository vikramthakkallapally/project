-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: pick_the_best
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `chat_map`
--

DROP TABLE IF EXISTS `chat_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_map` (
  `chat_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `adminname` varchar(45) NOT NULL,
  `tmp_user_id` varchar(225) DEFAULT NULL,
  `tmp_admin_id` varchar(225) DEFAULT NULL,
  `status` int DEFAULT '0',
  PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=414 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_map`
--

LOCK TABLES `chat_map` WRITE;
/*!40000 ALTER TABLE `chat_map` DISABLE KEYS */;
INSERT INTO `chat_map` VALUES (402,'user1','admin1','fe377ac6-a031-4a68-b40c-2a70e0202430','b14345a5-73ad-4f19-ba94-afae6ab96a62',1),(406,'User2','admin2','3fa9e26a-8c8c-41d0-9851-86ec51bf381e','172bce8d-dd08-4f1a-b250-b77485735d8d',1),(413,NULL,'admin1','175d2d61-ba3b-426d-813d-263049fe9eb0','9314903a-362e-4641-ae93-704d355f01f9',1);
/*!40000 ALTER TABLE `chat_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contactus`
--

DROP TABLE IF EXISTS `contactus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contactus` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `feedback` varchar(1000) DEFAULT NULL,
  `feedback_date` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactus`
--

LOCK TABLES `contactus` WRITE;
/*!40000 ALTER TABLE `contactus` DISABLE KEYS */;
INSERT INTO `contactus` VALUES (27,'8790363141','fg7y80ijnohivy8g7y80hipnjohivy8fg780hipbj kihvy9','2022-06-11 19:08:46.538000','in28minutes'),(28,'maxDix@luv2code.com','Its goodhjknhuikjhy','2022-06-11 19:44:36.693000','in28minutes'),(31,'maxDix@luv2code.com','ugfie8w9ufd[ojqojfosodddddddddkssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss','2022-06-11 19:45:15.079000','in28minutes');
/*!40000 ALTER TABLE `contactus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (414);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `amazon_id` varchar(255) DEFAULT NULL,
  `costco_id` varchar(255) DEFAULT NULL,
  `kroger_id` varchar(255) DEFAULT NULL,
  `target_id` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'Apple Watch Series 8 GPS + Cellular','B0BDHM1TBH','100805908','Not Available','A-87579619','admin'),(2,'Supersonic SC-68SW SC-68SW Bluetooth Smart Watch','B06VWP11KH','Not Available','63913120068','Not Available','admin'),(3,'Apple Watch Series 7','B09NWDXS13','100805852','Not Available','A-84736576','admin'),(4,'Canon EOS Rebel T7 DSLR Camera with 18-55mm Lens','B07C2Z21X5','Not Available','79637691654','A-54460467','admin'),(5,'GoPro HERO10 Black Action Camera Bundle','B09G9FZ7S5','100946503','81827902895','A-82685711','admin'),(6,'JBL CHARGE 5 - Portable Bluetooth Speaker','B08X4YMTPM','Not Available','5003638021','A-82803559','admin'),(7,'JBL Black Wireless Bluetooth Clip Speaker','B08PJ7JMQM','Not Available','5003637819','A-82803560','admin'),(8,'JBL Charge 4 Portable Bluetooth Speaker','B07HKQ6YGX','4000038037','5003634769','Not Available','admin'),(9,'JBL Go3 Wireless Speaker','B08KW1KR5H','Not Available','5003637425','A-81607899','admin'),(10,'Sony Compact Portable Bluetooth® Wireless Speaker','B08ZJ6DQNY','Not Available','2724292120','A-83974912','admin'),(11,'Bose SoundLink Flex SE Bluetooth Speaker','B099TJGJ91','4000037988','Not Available','A-84720679','admin'),(12,'Blink Mini – Compact indoor plug-in smart security camera','B07X6C9RMF','Not Available','84008052612','A-86774485','admin'),(13,'Sunglasses','B06XBZRQ3X','Not Available','74659165550','A-81257643','admin'),(14,'Router','B082X17B8P','Not Available','60644915218','A-82567795','admin'),(15,'Tv - TCL 32 Class 3-Series HD Smart Roku TV','B09YWT3P5Q','Not Available','84604201376','A-86217851','admin'),(16,'TCL 55\" Class 4-Series 4K UHD HDR Smart Roku TV','B08GWBBH4R','Not Available','84604206102','A-85269932','admin'),(17,'SAMSUNG 43-Inch Class Crystal 4K UHD AU8000 Series HDR','B08Z2823K3','Not Available','88727652296','Not Available','admin'),(18,'Apple AirPods (2nd Generation) Wireless','B07PXGQC1Q','100487204','Not Available','A-54191097','admin'),(19,'Apple AirPods Pro (2nd Generation)','B0BDHWDR12','100815258','Not Available','A-85978612','admin'),(20,'Apple AirPods (3rd Generation)','B0BDHB9Y8H','100806298','Not Available','A-77603772','admin'),(21,'Beats Fit Pro','B09JL5CY5Z','100806357','Not Available','A-84851473','admin'),(22,'Beats Fit Pro x Kim Kardashian','B0B6LW47C8','Not Available','Not Available','Not Available','admin'),(23,'Apple Watch Series 8 [GPS 41mm]','B0BDJ9FCYP','100813018','Not Available','A-87579544','admin'),(24,'Apple Watch Series 6 (GPS, 40MM)','B08KGVJQC8','Not Available','Not Available','A-81498743','admin'),(25,'Apple Watch Series 7 GPS 45mm:','B09HDYYXGX','4000037090','Not Available','A-84736576','admin'),(26,'SAMSUNG Galaxy Watch 4 40mm Smartwatch','B096BJLZZM','100795032','Not Available','A-84109733','admin'),(27,'SAMSUNG Galaxy Watch 5 40mm','B0B2J5TB3Q','Not Available','Not Available','A-86995607','admin'),(28,'Beats Studio Buds','B096SRYXMP','100779535','Not Available','A-83727077','admin'),(29,'Beats Studio3','B085296FLT','Not Available','Not Available','A-52960608','admin'),(30,'Beats Solo3 Wireless','B07YVYZ8T5','Not Available','Not Available','A-79437096','admin'),(31,'Powerbeats Pro','B07R5QD598','100530471','Not Available','A-78362035','admin'),(32,'Beats Flex Wireless Earbuds','B08L6ZYW21','Not Available','Not Available','A-82216716','admin'),(33,'The Everyday Raycon Bluetooth Wireless Earbuds','B095T4DNV1','Not Available','Not Available','Not Available','admin'),(34,'Samsung Earphones Corded Tuned by AKG','B072BZ4GSD','Not Available','Not Available','A-79844453','admin'),(35,'Samsung Galaxy Buds Pro','B08WX8WY4C','Not Available','Not Available','A-81111923','admin'),(36,'SAMSUNG Galaxy Buds 2 Pro','B0B2SH4CN6','4000037682','Not Available','A-87469756','admin'),(37,'Samsung Galaxy Buds Plus','B083KVM9VW','Not Available','Not Available','A-81111923','admin'),(38,'Google Pixel Buds Pro','B0B1NGPY94','Not Available','Not Available','A-86964222','admin'),(39,'JBL Tune 225TWS True Wireless Earbud','B088NDGYPX','Not Available','Not Available','Not Available','admin'),(40,'JBL Tune 230NC TWS True Wireless','B09FMS2QYD','Not Available','Not Available','A-85978969','admin'),(41,'iphone12','B0BDJ3R6QW','4000064298','Not Available','A-87577443','admin');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `message` varchar(225) NOT NULL,
  `sender` varchar(225) NOT NULL,
  `client` varchar(225) NOT NULL,
  `chat_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=413 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (386,'Hi','e6dda8b8-878a-4f69-b2c4-0a42d731f548','61646d696e31',385),(387,'Hello','61646d696e31','e6dda8b8-878a-4f69-b2c4-0a42d731f548',385),(388,'hi\n','e6dda8b8-878a-4f69-b2c4-0a42d731f548','61646d696e31',385),(389,'user1\n','61646d696e31','e6dda8b8-878a-4f69-b2c4-0a42d731f548',385),(390,'ADMIN!\n','61646d696e31','e6dda8b8-878a-4f69-b2c4-0a42d731f548',385),(391,'ADMIN1\n','61646d696e31','e6dda8b8-878a-4f69-b2c4-0a42d731f548',385),(392,'USER1\n','e6dda8b8-878a-4f69-b2c4-0a42d731f548','61646d696e31',385),(394,'USER2\n','a5e67fb4-2e97-4d29-9724-8821f8b073dd','61646d696e32',393),(395,'ADMIN@\n','61646d696e32','a5e67fb4-2e97-4d29-9724-8821f8b073dd',393),(396,'ADMIN2\n','61646d696e32','a5e67fb4-2e97-4d29-9724-8821f8b073dd',393),(397,'hi\n','61646d696e31','e6dda8b8-878a-4f69-b2c4-0a42d731f548',385),(399,'hello\n','a5e67fb4-2e97-4d29-9724-8821f8b073dd','61646d696e32',393),(400,'HI\n','2310942d-3a0d-4c35-b01d-9a66affc7228','61646d696e31',398),(401,'Hello\n','2310942d-3a0d-4c35-b01d-9a66affc7228','61646d696e31',398),(403,'Hi\n','fe377ac6-a031-4a68-b40c-2a70e0202430','b14345a5-73ad-4f19-ba94-afae6ab96a62',402),(404,'USER1\n','fe377ac6-a031-4a68-b40c-2a70e0202430','b14345a5-73ad-4f19-ba94-afae6ab96a62',402),(405,'ADMIN1\n','b14345a5-73ad-4f19-ba94-afae6ab96a62','fe377ac6-a031-4a68-b40c-2a70e0202430',402),(407,'USER2\n','3fa9e26a-8c8c-41d0-9851-86ec51bf381e','172bce8d-dd08-4f1a-b250-b77485735d8d',406),(408,'ADMIN2','172bce8d-dd08-4f1a-b250-b77485735d8d','3fa9e26a-8c8c-41d0-9851-86ec51bf381e',406),(409,'hi\n','3fa9e26a-8c8c-41d0-9851-86ec51bf381e','172bce8d-dd08-4f1a-b250-b77485735d8d',406),(410,'USER1\n','fe377ac6-a031-4a68-b40c-2a70e0202430','b14345a5-73ad-4f19-ba94-afae6ab96a62',402),(411,'ADMIN1\n','b14345a5-73ad-4f19-ba94-afae6ab96a62','fe377ac6-a031-4a68-b40c-2a70e0202430',402),(412,'Thank you\n','b14345a5-73ad-4f19-ba94-afae6ab96a62','fe377ac6-a031-4a68-b40c-2a70e0202430',402);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp_auth`
--

DROP TABLE IF EXISTS `otp_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otp_auth` (
  `email` varchar(225) NOT NULL,
  `token` varchar(225) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp_auth`
--

LOCK TABLES `otp_auth` WRITE;
/*!40000 ALTER TABLE `otp_auth` DISABLE KEYS */;
INSERT INTO `otp_auth` VALUES ('','$2a$10$gJYGyy35aGuzZrR1iUps6.rs0eVlG.zYRdkfxId7L5rOvpySLXWMK',129);
/*!40000 ALTER TABLE `otp_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `product_description` varchar(255) DEFAULT NULL,
  `productname` varchar(255) DEFAULT NULL,
  `request_date` datetime(6) DEFAULT NULL,
  `request_status` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `closed_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (18,'vamshiguntuku86@gmail.com','Please provide the item','Iphone 13 pro max','2022-06-11 16:01:50.313000','Y','vamshi.guntuku','vk04168749@gmail.com'),(19,'vamshiguntuku86@gmail.com',NULL,NULL,'2022-06-11 16:02:17.955000','Y','vamshi.guntuku','admin123'),(20,'vamshiguntuku86@gmail.com','ksadkeldpleplepepe','Apple care ','2022-06-11 17:36:59.646000','N','vamshi.guntuku',NULL),(21,'vamshiguntuku86@gmail.com',NULL,NULL,'2022-06-11 18:57:28.361000','Y','vamshi.guntuku','admin123'),(22,'8790363141',NULL,NULL,'2022-06-11 18:59:02.069000','Y','vamshi.guntuku','vk04168749@gmail.com'),(23,'vamshiguntuku86@gmail.com',NULL,NULL,'2022-06-11 19:01:41.725000','N','vamshi.guntuku',NULL),(24,'8790363141',NULL,NULL,'2022-06-11 19:02:10.148000','N','vamshi',NULL),(25,'vamshiguntuku86@gmail.com',NULL,NULL,'2022-06-11 19:04:06.944000','N','vamshi.guntuku',NULL),(26,'kkkkkkkkkkkkkkkkkkkkk',NULL,NULL,'2022-06-11 19:04:53.839000','N','hjjjkkk',NULL),(1000001,'vamshiguntuku86@gmail.com','Office chair','chair','2022-05-26 23:51:30.479000','N','Vamshikrishna Guntuku',NULL),(1000002,'rajdeep@gmail.com','nikon digital camera','camera','2022-05-26 23:51:30.479000','N','Rajdeep',NULL),(1000003,'kalyan@gmail.com','Boat Speakers','speaker','2022-05-25 23:51:30.479612','N','kalyan',NULL),(1000004,'rajdeep@gmail.com','nikon digital camera','camera','2022-05-25 23:51:30.479000','N','Rajdeep',NULL),(1000005,'kalyan@gmail.com','stylus','smart pen','2022-05-25 23:51:30.479612','N','kalyan',NULL),(1000006,'rajdeep@gmail.com','15.6 inch cooling pad','cooling pad','2022-05-25 23:51:30.479612','N','Rajdeep',NULL);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_name` varchar(255) NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('ADMIN','admin'),('USER','user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `search_audit`
--

DROP TABLE IF EXISTS `search_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `search_audit` (
  `id` int NOT NULL,
  `search_count` int DEFAULT NULL,
  `search_date` datetime(6) DEFAULT NULL,
  `search_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `search_audit`
--

LOCK TABLES `search_audit` WRITE;
/*!40000 ALTER TABLE `search_audit` DISABLE KEYS */;
INSERT INTO `search_audit` VALUES (1,14,'2022-06-11 00:00:00.000000','iphone13'),(2,1,'2022-06-11 00:00:00.000000','Glass'),(3,1,'2022-06-11 00:00:00.000000','Fosiil'),(4,0,'2022-06-11 00:00:00.000000','ipad'),(5,2,'2022-06-11 00:00:00.000000','mac book'),(6,0,'2022-06-11 00:00:00.000000','iphone12'),(7,1,'2022-06-11 00:00:00.000000','smart tv'),(8,0,'2022-06-11 00:00:00.000000','smart watch'),(9,0,'2022-06-11 00:00:00.000000','Casio watches'),(10,2,'2022-06-11 00:00:00.000000','water bottle'),(11,0,'2022-06-11 00:00:00.000000','mouse pad'),(12,0,'2022-06-11 00:00:00.000000','ear phones'),(13,0,'2022-06-11 00:00:00.000000','laptop'),(14,0,'2022-06-11 00:00:00.000000','shoes'),(15,0,'2022-06-11 00:00:00.000000','Speaker'),(16,0,'2022-06-11 00:00:00.000000','note books'),(17,25,'2022-06-11 00:00:00.000000','pro'),(33,82,'2022-06-12 00:00:00.000000','iphone13'),(34,5,'2022-06-12 00:00:00.000000','Glass'),(35,0,'2022-06-12 00:00:00.000000','Fosiil'),(36,0,'2022-06-12 00:00:00.000000','mac book'),(37,1,'2022-06-12 00:00:00.000000','water bottle'),(38,0,'2022-06-12 00:00:00.000000','smart tv'),(39,0,'2022-06-12 00:00:00.000000','ipad'),(40,1,'2022-06-12 00:00:00.000000','iphone12'),(41,1,'2022-06-12 00:00:00.000000','smart watch'),(42,0,'2022-06-12 00:00:00.000000','Casio watches'),(43,0,'2022-06-12 00:00:00.000000','mouse pad'),(44,0,'2022-06-12 00:00:00.000000','ear phones'),(45,2,'2022-06-12 00:00:00.000000','laptop'),(46,0,'2022-06-12 00:00:00.000000','shoes'),(47,0,'2022-06-12 00:00:00.000000','Speaker'),(48,0,'2022-06-12 00:00:00.000000','note books'),(49,11,'2022-06-12 00:00:00.000000','pro'),(52,2,'2022-06-20 00:00:00.000000','iphone13'),(53,5,'2022-06-20 00:00:00.000000','pro'),(54,2,'2022-06-20 00:00:00.000000','glass'),(55,0,'2022-06-20 00:00:00.000000','Fosiil'),(56,31,'2022-09-10 00:00:00.000000','pro'),(57,13,'2022-09-10 00:00:00.000000','iphone13'),(58,1,'2022-09-10 00:00:00.000000','chair'),(59,1,'2022-09-10 00:00:00.000000','Glass'),(60,1,'2022-09-10 00:00:00.000000','Pen'),(61,3,'2022-09-24 00:00:00.000000','pro'),(62,2,'2022-09-24 00:00:00.000000','iphone13'),(63,0,'2022-09-24 00:00:00.000000','chair'),(64,1,'2022-09-24 00:00:00.000000','Glass'),(65,0,'2022-09-24 00:00:00.000000','Pen'),(66,1,'2022-09-24 00:00:00.000000','macbook'),(67,1,'2022-09-25 00:00:00.000000','pro'),(68,0,'2022-09-25 00:00:00.000000','iphone13'),(69,1,'2022-09-25 00:00:00.000000','Glass'),(70,0,'2022-10-05 00:00:00.000000','pro'),(71,0,'2022-10-05 00:00:00.000000','iphone13'),(72,0,'2022-10-05 00:00:00.000000','Glass'),(73,0,'2022-10-05 00:00:00.000000','chair'),(74,0,'2022-10-05 00:00:00.000000','Pen'),(75,0,'2022-10-05 00:00:00.000000','macbook'),(76,10,'2022-10-06 00:00:00.000000','pro'),(77,18,'2022-10-06 00:00:00.000000','iphone13'),(78,1,'2022-10-06 00:00:00.000000','Glass'),(79,1,'2022-10-06 00:00:00.000000','chair'),(80,0,'2022-10-06 00:00:00.000000','Pen'),(81,0,'2022-10-06 00:00:00.000000','macbook'),(82,194,'2022-10-06 00:00:00.000000','iphone'),(83,1,'2022-10-06 00:00:00.000000','iphone12'),(84,46,'2022-10-07 00:00:00.000000','iphone'),(85,142,'2022-10-07 00:00:00.000000','iphone13'),(86,2,'2022-10-07 00:00:00.000000','Glass'),(87,1,'2022-10-07 00:00:00.000000','earphones'),(118,15,'2022-10-07 00:00:00.000000','JBL'),(119,2,'2022-10-07 00:00:00.000000','pro'),(120,1,'2022-10-07 00:00:00.000000','chair'),(121,0,'2022-10-07 00:00:00.000000','Pen'),(122,0,'2022-10-07 00:00:00.000000','macbook'),(123,3,'2022-10-07 00:00:00.000000','iphone12'),(124,2,'2022-10-07 00:00:00.000000','iphone14'),(125,11,'2022-10-07 00:00:00.000000','Apple'),(143,65,'2022-10-08 00:00:00.000000','Apple'),(144,156,'2022-10-08 00:00:00.000000','iphone13'),(145,1,'2022-10-08 00:00:00.000000','iron'),(146,1,'2022-10-08 00:00:00.000000','Glass'),(149,1,'2022-10-08 00:00:00.000000','JBL'),(276,5,'2022-10-09 00:00:00.000000','Apple'),(287,1,'2022-10-12 00:00:00.000000','Apple'),(295,7,'2022-10-13 00:00:00.000000','Apple'),(379,14,'2022-10-15 00:00:00.000000','Apple'),(382,1,'2022-10-15 00:00:00.000000','iphone13'),(383,1,'2022-10-15 00:00:00.000000','iphone14'),(384,2,'2022-10-15 00:00:00.000000','iphone12'),(1000001,26,'2022-05-26 23:51:30.479612','iphone13'),(1000002,25,'2022-05-25 23:51:30.479612','iphone13'),(1000003,24,'2022-05-24 23:51:30.479612','iphone13'),(1000004,25,'2022-05-26 23:51:30.479612','Glass'),(1000005,28,'2022-05-26 23:51:30.479612','Fosiil'),(1000006,25,'2022-05-22 23:51:30.479612','Glass'),(1000007,28,'2022-05-23 23:51:30.479612','Fosiil'),(1000008,25,'2022-05-26 23:51:30.479612','ipad'),(1000009,25,'2022-05-26 23:51:30.479612','mac book'),(1000010,25,'2022-05-26 23:51:30.479612','iphone12'),(1000011,25,'2022-05-26 23:51:30.479612','smart tv'),(1000012,25,'2022-05-26 23:51:30.479612','smart watch'),(1000013,25,'2022-05-26 23:51:30.479612','Casio watches'),(1000014,28,'2022-03-23 23:51:30.479612','NOT_FETCHED'),(1000015,25,'2022-05-26 23:51:30.479612','water bottle'),(1000016,25,'2022-05-26 23:51:30.479612','mouse pad'),(1000017,25,'2022-05-26 23:51:30.479612','ear phones'),(1000018,25,'2022-05-26 23:51:30.479612','laptop'),(1000019,25,'2022-05-26 23:51:30.479612','shoes'),(1000020,25,'2022-05-26 23:51:30.479612','Glass'),(1000021,25,'2022-05-26 23:51:30.479612','Speaker'),(1000022,25,'2022-05-26 23:51:30.479612','note books');
/*!40000 ALTER TABLE `search_audit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (		
  `user_name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `user_first_name` varchar(255) DEFAULT NULL,
  `user_last_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_name`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin1','admin1@gmail.com','admin','one','$10$qVrCzPpoX8nDvjBWl4sF/OGtzPJaXQDfsKoqe2cvoGoDzOXq4ZR1G'),('admin2','admin2@gmail.com','admin','two','$10$qVrCzPpoX8nDvjBWl4sF/OGtzPJaXQDfsKoqe2cvoGoDzOXq4ZR1G'),('user1','user1@gmail.com','user','one','$10$qVrCzPpoX8nDvjBWl4sF/OGtzPJaXQDfsKoqe2cvoGoDzOXq4ZR1G'),('user2','user2@gmail.com','user','two','$10$qVrCzPpoX8nDvjBWl4sF/OGtzPJaXQDfsKoqe2cvoGoDzOXq4ZR1G');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_name`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('admin1','Admin'),('admin2','Admin'),('user1','User'),('user2','User');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-15 22:50:14
