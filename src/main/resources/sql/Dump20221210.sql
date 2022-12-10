-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: kutto
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `tb_rol`
--

DROP TABLE IF EXISTS `tb_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_rol` (
  `N_ID_ROL` int NOT NULL AUTO_INCREMENT,
  `V_ROL_NOMBRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`N_ID_ROL`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_rol`
--

LOCK TABLES `tb_rol` WRITE;
/*!40000 ALTER TABLE `tb_rol` DISABLE KEYS */;
INSERT INTO `tb_rol` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_ATENCION'),(3,'ROLE_LOGISTICA');
/*!40000 ALTER TABLE `tb_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_usuario`
--

DROP TABLE IF EXISTS `tbl_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_usuario` (
  `N_ID_USUARIO` int NOT NULL AUTO_INCREMENT,
  `V_NOMBRE_USUARIO` varchar(255) DEFAULT NULL,
  `V_PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`N_ID_USUARIO`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuario`
--

LOCK TABLES `tbl_usuario` WRITE;
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
INSERT INTO `tbl_usuario` VALUES (1,'admin','$2a$10$upjd2hh1/ZBqFRwfDmPba.bOBBEigXmm4uTTXb4e5Xh6RLFAUZX8m'),(2,'logistica','$2a$10$jlSiA7ExkS3.HiC/nJoWZeDuLDcW8eC3agTio2ugJkMEDfZXPu5z2'),(3,'atencion','$2a$10$ffWRCYTKNAlTjI5Dc2yaZuEpCCqbcDWIrmBOAaZFejsy8t0UOICeG');
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_usuario_rol`
--

DROP TABLE IF EXISTS `tbl_usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_usuario_rol` (
  `N_ID_USUARIO` int NOT NULL,
  `N_ID_ROL` int NOT NULL,
  PRIMARY KEY (`N_ID_USUARIO`,`N_ID_ROL`),
  KEY `fk_tbl_usuario_rol_tbl_rol_idx` (`N_ID_ROL`),
  KEY `fk_tbl_usuario_rol_tbl_usuario_idx` (`N_ID_USUARIO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuario_rol`
--

LOCK TABLES `tbl_usuario_rol` WRITE;
/*!40000 ALTER TABLE `tbl_usuario_rol` DISABLE KEYS */;
INSERT INTO `tbl_usuario_rol` VALUES (1,1),(1,2),(1,3),(2,3),(3,2);
/*!40000 ALTER TABLE `tbl_usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'kutto'
--

--
-- Dumping routines for database 'kutto'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-10 17:03:21
