-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ship_ship
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.28-MariaDB

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
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_SHOP'),(3,'ROLE_SHIPPER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(450) NOT NULL,
  `role_id` int(11) NOT NULL,
  `avatar` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_idx` (`role_id`),
  CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'hoang','$2a$12$RFEK3SkZ6o9he3Zv8WJN/.M07wQrfyNND88WZ4ciwliLWa/F72IEC',1,'ducthuan-avatar.jpg'),(4,'thuan','$2a$12$RFEK3SkZ6o9he3Zv8WJN/.M07wQrfyNND88WZ4ciwliLWa/F72IEC',2,'ducthuan-avatar.jpg'),(6,'thuannd169@gmail.com','$2a$10$4RaSdoU/yadrBQ8GLl23AuKSUpwKWZuVN0meosXU95bvK7gzaTgq.',3,'ducthuan-avatar.jpg'),(7,'abc','$2a$10$Km2Jud2mvNqgFFP88wFcwu7JLZmcp.D/bTcR3YED1wi0c0VLgg.EC',2,'ducthuan-avatar.jpg'),(8,'def','$2a$10$h84.QCp0CG60XisgXLwTHeOY.bkYlqzmidQS4jiDUCxBQOLAygKki',2,'ducthuan-avatar.jpg'),(9,'ducthuan','$2a$10$/4Gt4g9Wa2ZQcGnh2sL2YuAx0kH.dPaSAxEX8LSG7pbjPAKu0VGja',2,'ducthuan-avatar.jpg'),(10,'thuannd','$2a$10$djsw2n.EVxtAI1osYkBi9O6.y/8HA3fXIWTotcXc.FcUOEIBLtR.2',3,'thuannd-avatar.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_order`
--

DROP TABLE IF EXISTS `user_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(450) DEFAULT NULL,
  `from_add` varchar(45) DEFAULT NULL,
  `to_add` varchar(45) DEFAULT NULL,
  `fee` int(11) NOT NULL,
  `addvance_money` int(11) NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_idx` (`created_by`),
  CONSTRAINT `fk_user` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_order`
--

LOCK TABLES `user_order` WRITE;
/*!40000 ALTER TABLE `user_order` DISABLE KEYS */;
INSERT INTO `user_order` VALUES (2,'Vừa buồn vừa thương con bé. Không biết phải làm như thế nào để con bé khoẻ mạnh sống vui vẻ cùng sen :((( mới ngã lầu 5 xuống chưa dc bao lâu thì có u trên lưng rồi nó tự vỡ ra. Đã đc phẫu thuật. N rồi tưởng chừng tới lúc con bé sắp lành lặn trở lại thì cái u đó di căn xuống vùng nách và n đớn lắm. N k thể làm gì khác. Thuoeng con nhiều lắm','adasdas','ádasd',2123,123,4,'2018-10-23 00:39:00',1),(3,'asdasd','asdasd','asdas',123,123,4,'2018-10-22 23:25:06',1),(4,'eret','rtr','erter',234,2343,4,'2018-10-22 23:27:18',1),(7,'dasd','adasd','asd',12,132,8,'2018-10-23 15:02:08',1),(8,'asd','asd','asd',213,123,8,'2018-10-23 15:04:14',1);
/*!40000 ALTER TABLE `user_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ship_ship'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-23 21:13:20
