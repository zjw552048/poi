-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: poi
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `order_info`
--

DROP TABLE IF EXISTS `order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_info` (
  `orderid` varchar(100) NOT NULL COMMENT '鍐呴儴璁㈠崟鍙?,
  `customorderid` varchar(100) NOT NULL COMMENT '鐢ㄦ埛璁㈠崟鍙?,
  `dateoforder` datetime NOT NULL COMMENT '涓嬪崟鏃ユ湡',
  `dateofissuance` datetime DEFAULT NULL COMMENT '鍙戣揣鏃ユ湡,鍙负绌?,
  `statusoforder` int(11) NOT NULL COMMENT '璁㈠崟鐘舵€?0=浜ゆ槗鍏抽棴 1=宸插畬鎴?,
  `methodofpayment` varchar(100) DEFAULT NULL COMMENT '鏀粯鏂瑰紡,浜ゆ槗鍏抽棴鏃朵负绌?,
  `statusofpayment` int(11) NOT NULL COMMENT '鏀粯鐘舵€?0=鏈敮浠?1=宸叉敮浠?,
  `methodOfDistribution` varchar(100) NOT NULL COMMENT '閰嶉€佹柟寮?,
  `consignee` varchar(100) DEFAULT NULL COMMENT '鏀惰揣浜?鍙负绌?,
  `phoneNumberOfConsignee` varchar(100) DEFAULT NULL COMMENT '鏀惰揣浜烘墜鏈哄彿,鍙负绌?,
  `phoneNumberOfCustom` varchar(100) DEFAULT NULL COMMENT '涓嬪崟浜烘墜鏈哄彿,鍙负绌?,
  `discountGrade` varchar(100) NOT NULL COMMENT '涓嬪崟浼氬憳绛夌骇鎶樻墸',
  `perksOfMembershi` varchar(100) DEFAULT NULL COMMENT '璁㈠崟浼氬憳鏉冪泭,鍙负绌?,
  `nameOfCommodity` varchar(100) NOT NULL COMMENT '鍟嗗搧鍚嶇О',
  `performanceId` varchar(100) NOT NULL COMMENT '鍦烘Id',
  `nameOfPerformance` varchar(100) NOT NULL COMMENT '鍦烘鍚嶇О',
  `ieldOfPerformance` varchar(100) NOT NULL COMMENT '棣嗗巺',
  `dateOfPerformance` datetime NOT NULL COMMENT '婕斿嚭鏃ユ湡',
  `priceAndNumber` varchar(100) NOT NULL COMMENT '绁ㄤ环',
  `priceOfTicket` double NOT NULL COMMENT '鍗曚环',
  `numberOfTicket` int(11) NOT NULL COMMENT '寮犳暟',
  `shouldPayment` double NOT NULL COMMENT '搴旀敹',
  `actualPayment` double NOT NULL COMMENT '瀹炴敹=搴旀敹*鎶樻墸'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_info`
--

LOCK TABLES `order_info` WRITE;
/*!40000 ALTER TABLE `order_info` DISABLE KEYS */;
INSERT INTO `order_info` VALUES ('B22793028698541','C22793027649964','2017-09-08 06:05:00',NULL,1,'寰俊',1,'涓婇棬鑷彇','鍞愮嚂','13913929894','13913929894','100',NULL,'椹厠瑗垮路灏ら噾路鑾熀鍒楀か鏂熀閽㈢惔鐙闊充箰浼?,'561','鍦烘1','闊充箰鍘?,'2017-09-15 11:30:00','20*2 ',20,2,40,40),('B22677495546111','C22677495546110','2017-09-06 23:29:00',NULL,1,'寰俊',1,'涓婇棬鑷彇','鍛ㄤ粊浼?,'13801592273','13801592273','100',NULL,'鍗椾含甯傛枃鍖栨秷璐规斂搴滆ˉ璐村墽鐩?涓婃捣瓒婂墽闄㈣秺鍓?绾㈡ゼ姊?,'482','绾㈡ゼ姊?,'鎴忓墽鍘?,'2017-09-09 11:00:00','336*1 530*2 ',336,1,1396,1396),('B22677495546111','C22677495546110','2017-09-06 23:29:00',NULL,1,'寰俊',1,'涓婇棬鑷彇','鍛ㄤ粊浼?,'13801592273','13801592273','100',NULL,'鍗椾含甯傛枃鍖栨秷璐规斂搴滆ˉ璐村墽鐩?涓婃捣瓒婂墽闄㈣秺鍓?绾㈡ゼ姊?,'482','绾㈡ゼ姊?,'鎴忓墽鍘?,'2017-09-09 11:00:00','336*1 530*2 ',530,2,1396,1396),('B22632002027733','C22632000979156','2017-09-06 11:25:00',NULL,0,NULL,0,'蹇€掗厤閫?,'鐜嬮┌鎮?,'15895900923','15895900923','100',NULL,'缁翠篃绾崇埍涔愪箰鍥㈤煶涔愪細','307','鍦烘涓€10鏈?6鏃?,'闊充箰鍘?,'2017-10-26 11:30:00','680*1 ',680,1,680,680);
/*!40000 ALTER TABLE `order_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-19 19:58:35
