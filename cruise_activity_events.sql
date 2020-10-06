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
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `EVENT_NAME` varchar(100) DEFAULT NULL,
  `EVENT_DATE` date DEFAULT NULL,
  `START_TIME` time DEFAULT NULL,
  `DURATION` varchar(45) DEFAULT NULL,
  `LOCATION` varchar(45) DEFAULT NULL,
  `ATTENDEES` varchar(45) DEFAULT NULL,
  `CAPACITY` varchar(45) DEFAULT NULL,
  `COORDINATOR` varchar(45) DEFAULT NULL,
  `TYPE` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES ('Bowling1','2020-10-10','10:00:00','60','8','10','10','jerry','Athletic'),('Bowling2','2020-10-10','10:00:00','60','8','10','10','jerry','Athletic'),('Movie1','2020-10-10','10:00:00','120','3','25','25','jerry','show'),('Movie2','2020-10-10','10:00:00','120','3','50','50','jerry','show'),('Extreme Zipline','2020-10-10','10:00:00','30','15','15','15','jerry','Athletic'),('Skycourse Ropes','2020-10-10','10:00:00','60','14','8','8','jerry','Athletic'),('Ice skating','2020-10-10','10:00:00','60','8','35','35','jerry','Athletic'),('Go carting','2020-10-10','10:00:00','60','8','20','20','jerry','Athletic'),('Brodway show','2020-10-10','10:00:00','120','8','100','100','jerry','show'),('Planetarium','2020-10-10','10:00:00','60','8','100','100','jerry','show'),('Bowling1','2020-10-10','15:00:00','60','8','10','10','jerry','Athletic'),('Bowling2','2020-10-10','15:00:00','60','8','10','10','jerry','Athletic'),('Movie1','2020-10-10','15:00:00','120','3','25','25','jerry','show'),('Movie2','2020-10-10','15:00:00','120','3','25','25','jerry','show'),('Extreme Zipline','2020-10-10','15:00:00','30','15','15','15','jerry','Athletic'),('Skycourse Ropes','2020-10-10','15:00:00','60','14','8','8','jerry','Athletic'),('Ice skating','2020-10-10','15:00:00','60','8','35','35','jerry','Athletic'),('Go carting','2020-10-10','15:00:00','60','8','20','20','jerry','Athletic'),('Brodway show','2020-10-10','15:00:00','120','8','100','100','jerry','show'),('Planetarium','2020-10-10','15:00:00','60','8','100','100','jerry','show'),('Bowling1','2020-10-11','15:00:00','60','8','10','10','jerry','Athletic'),('Bowling2','2020-10-11','15:00:00','60','8','10','10','jerry','Athletic'),('Movie1','2020-10-11','15:00:00','120','3','25','25','jerry','show'),('Movie2','2020-10-11','15:00:00','120','3','25','25','jerry','show'),('Extreme Zipline','2020-10-11','15:00:00','30','15','15','15','jerry','Athletic'),('Skycourse Ropes','2020-10-11','15:00:00','60','14','8','8','jerry','Athletic'),('Ice skating','2020-10-11','15:00:00','60','8','35','35','jerry','Athletic'),('Go carting','2020-10-11','15:00:00','60','8','20','20','jerry','Athletic'),('Brodway show','2020-10-11','15:00:00','120','8','100','100','jerry','show'),('Planetarium','2020-10-11','15:00:00','60','8','100','100','jerry','show'),('Bowling1','2020-10-11','10:00:00','60','8','10','10','jerry','Athletic'),('Bowling2','2020-10-11','10:00:00','60','8','10','10','jerry','Athletic'),('Movie1','2020-10-11','10:00:00','120','3','25','25','jerry','show'),('Movie2','2020-10-11','10:00:00','120','3','25','25','jerry','show'),('Extreme Zipline','2020-10-11','10:00:00','30','15','15','15','jerry','Athletic'),('Skycourse Ropes','2020-10-11','10:00:00','60','14','8','8','jerry','Athletic'),('Ice skating','2020-10-11','10:00:00','60','8','35','35','jerry','Athletic'),('Go carting','2020-10-11','10:00:00','60','8','20','20','jerry','Athletic'),('Brodway show','2020-10-11','10:00:00','120','8','100','100','jerry','show'),('Planetarium','2020-10-11','10:00:00','60','8','100','100','jerry','show'),('Bowling1','2020-10-07','10:00:00','60','8','10','10','jerry','Athletic'),('Bowling2','2020-10-07','10:00:00','60','8','10','10','jerry','Athletic'),('Movie1','2020-10-07','10:00:00','120','3','25','25','jerry','show'),('Movie2','2020-10-07','10:00:00','120','3','25','25','jerry','show'),('Extreme Zipline','2020-10-07','10:00:00','30','15','15','15','jerry','Athletic'),('Skycourse Ropes','2020-10-07','10:00:00','60','14','8','8','jerry','Athletic'),('Ice skating','2020-10-07','10:00:00','60','8','35','35','jerry','Athletic'),('Go carting','2020-10-07','10:00:00','60','8','20','20','jerry','Athletic'),('Brodway show','2020-10-07','10:00:00','120','8','100','100','jerry','show'),('Planetarium','2020-10-07','10:00:00','60','8','100','100','jerry','show'),('Bowling1','2020-10-07','15:00:00','60','8','10','10','jerry','Athletic'),('Bowling2','2020-10-07','15:00:00','60','8','10','10','jerry','Athletic'),('Movie1','2020-10-07','15:00:00','120','3','25','25','jerry','show'),('Movie2','2020-10-07','15:00:00','120','3','25','25','jerry','show'),('Extreme Zipline','2020-10-07','15:00:00','30','15','15','15','jerry','Athletic'),('Skycourse Ropes','2020-10-07','15:00:00','60','14','8','8','jerry','Athletic'),('Ice skating','2020-10-07','15:00:00','60','8','35','35','jerry','Athletic'),('Go carting','2020-10-07','15:00:00','60','8','20','20','jerry','Athletic'),('Brodway show','2020-10-07','15:00:00','120','8','100','100','jerry','show'),('Planetarium','2020-10-07','15:00:00','60','8','100','100','jerry','show'),('Bowling1','2020-10-15','10:00:00','60','8','10','10','jerry','Athletic'),('Bowling2','2020-10-15','10:00:00','60','8','10','10','jerry','Athletic'),('Movie1','2020-10-15','10:00:00','120','3','25','25','jerry','show'),('Movie2','2020-10-15','10:00:00','120','3','25','25','jerry','show'),('Extreme Zipline','2020-10-15','10:00:00','30','15','15','15','jerry','Athletic'),('Skycourse Ropes','2020-10-15','10:00:00','60','14','8','8','jerry','Athletic'),('Ice skating','2020-10-15','10:00:00','60','8','35','35','jerry','Athletic'),('Go carting','2020-10-15','10:00:00','60','8','20','20','jerry','Athletic'),('Brodway show','2020-10-15','10:00:00','120','8','100','100','jerry','show'),('Planetarium','2020-10-15','10:00:00','60','8','100','100','jerry','show'),('Bowling1','2020-10-16','10:00:00','60','8','10','10','jerry','Athletic'),('Bowling2','2020-10-16','10:00:00','60','8','10','10','jerry','Athletic'),('Movie1','2020-10-16','10:00:00','120','3','25','25','jerry','show'),('Movie2','2020-10-16','10:00:00','120','3','25','25','jerry','show'),('Extreme Zipline','2020-10-16','10:00:00','30','15','15','15','jerry','Athletic'),('Skycourse Ropes','2020-10-16','10:00:00','60','14','8','8','jerry','Athletic'),('Ice skating','2020-10-16','10:00:00','60','8','35','35','jerry','Athletic'),('Go carting','2020-10-16','10:00:00','60','8','20','20','jerry','Athletic'),('Brodway show','2020-10-16','10:00:00','120','8','100','100','jerry','show'),('Planetarium','2020-10-16','10:00:00','60','8','100','100','jerry','show'),('Bowling1','2020-10-16','15:00:00','60','8','10','10','jerry','Athletic'),('Bowling2','2020-10-16','15:00:00','60','8','10','10','jerry','Athletic'),('Movie1','2020-10-16','15:00:00','120','3','25','25','jerry','show'),('Movie2','2020-10-16','15:00:00','120','3','25','25','jerry','show'),('Extreme Zipline','2020-10-16','15:00:00','30','15','15','15','jerry','Athletic'),('Skycourse Ropes','2020-10-16','15:00:00','60','14','8','8','jerry','Athletic'),('Ice skating','2020-10-16','15:00:00','60','8','35','35','jerry','Athletic'),('Go carting','2020-10-16','15:00:00','60','8','20','20','jerry','Athletic'),('Brodway show','2020-10-16','15:00:00','120','8','100','100','jerry','show'),('Planetarium','2020-10-16','15:00:00','60','8','100','100','jerry','show'),('Bowling1','2020-10-15','15:00:00','60','8','10','10','jerry','Athletic'),('Bowling2','2020-10-15','15:00:00','60','8','10','10','jerry','Athletic'),('Movie1','2020-10-15','15:00:00','120','3','25','25','jerry','show'),('Movie2','2020-10-15','15:00:00','120','3','25','25','jerry','show'),('Extreme Zipline','2020-10-15','15:00:00','30','15','15','15','jerry','Athletic'),('Skycourse Ropes','2020-10-15','15:00:00','60','14','8','8','jerry','Athletic'),('Ice skating','2020-10-15','15:00:00','60','8','35','35','jerry','Athletic'),('Go carting','2020-10-15','15:00:00','60','8','20','20','jerry','Athletic'),('Brodway show','2020-10-15','15:00:00','120','8','100','100','jerry','show'),('Planetarium','2020-10-15','15:00:00','60','8','100','100','jerry','show');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
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
