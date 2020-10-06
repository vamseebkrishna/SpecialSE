-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cruise_activity
-- ------------------------------------------------------
-- Server version	5.7.31-log

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
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_user` (
  `USERNAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  `LASTNAME` varchar(45) DEFAULT NULL,
  `FIRSTNAME` varchar(45) DEFAULT NULL,
  `PHONE` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `ROOM_NUMBER` varchar(45) DEFAULT NULL,
  `DECK_NUMBER` varchar(45) DEFAULT NULL,
  `MEM_TYPE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user`
--

LOCK TABLES `system_user` WRITE;
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
INSERT INTO `system_user` VALUES ('Bhavana','Golio.151!','Ganapathiraman','Bhavana','6822830843','bhavana@gmail.com','105','5','Passenger'),('bhavanag','Bhavana!1234','Ganapatipuram','Bhavana','7896321475','bhavana@gmail.com','126','3','Passenger'),('EventCoordinator','Pass!1234','Fincher','David','7894561231','david@gmail.com','54','72','coordinator'),('EventManager','Login!123','Bezos','Jeff','1234567891','jeff@gmail.com','45','89','manager'),('nikhilbkrishna','Nikhil!1234','Ikkurthi','Nikhil','6822834154','nikhileshwar.ikkurthi@gmail.com','124','7','passenger'),('qwerty','Qwerty!123','QwertyLast','QwertyFirst','6822834156','qwerty@abc.com','23','23',''),('testuser123','TestUser!123','Test','User','7894561237','testuser123@gmail.com','130','66','Passenger'),('testuser2','TestUser!123','Test2','User2','1234567995','testuser2@gmail.com','144','6','Passenger'),('vamse','Opensaysme!123','Vamse','VamseFirst','6822834156','vamseebkrishna@gmail.com','78','56',''),('vamseebkrishna','Opensaysme!123','bollina','vamsee','6822834156','vamseebkrishna@gmail.com','215','99','passenger'),('vamseebkrishna01','Opensaysme!123','Bollina','Vamsee','7894561234','vamseebkrishna@gmail.com','101','98','Passenger'),('venkat','Venkat!1234','Mukkamala','Venkat','7896541237','venkat@gmail.com','125','5','passenger'),('wajid123','Wajid!123','Ahmed','Wajid','5555555555','wajid.ahmed@hotmail.com','123','8','passenger');
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-06  8:52:47
