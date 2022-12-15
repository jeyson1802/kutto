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
-- Table structure for table `tbl_cliente`
--

DROP TABLE IF EXISTS `tbl_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_cliente` (
  `V_COD_CLIENTE` varchar(6) NOT NULL,
  `V_COD_TIPO_PERSONA` varchar(4) NOT NULL,
  `V_COD_TIPO_DOCUMENTO` varchar(4) NOT NULL,
  `V_DOCUMENTO` varchar(20) NOT NULL,
  `V_NOMBRES_RAZON_SOCIAL` varchar(250) NOT NULL,
  `V_CORREO` varchar(200) DEFAULT NULL,
  `V_DIRECCION` varchar(250) DEFAULT NULL,
  `N_ACTIVO` int NOT NULL,
  `V_COD_USU_REGISTRA` varchar(20) NOT NULL,
  `D_FEC_REGISTRO` datetime NOT NULL,
  `V_COD_USU_MODIFICA` varchar(20) DEFAULT NULL,
  `D_FEC_MODIFICACION` datetime DEFAULT NULL,
  PRIMARY KEY (`V_COD_CLIENTE`),
  UNIQUE KEY `V_COD_CLIENTE_UNIQUE` (`V_COD_CLIENTE`),
  KEY `fk_tbl_tipo_documento_tbl_cliente_idx` (`V_COD_TIPO_DOCUMENTO`),
  CONSTRAINT `fk_tbl_tipo_documento_tbl_cliente` FOREIGN KEY (`V_COD_TIPO_DOCUMENTO`) REFERENCES `tbl_tipo_documento` (`V_COD_TIPO_DOCUMENTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cliente`
--

LOCK TABLES `tbl_cliente` WRITE;
/*!40000 ALTER TABLE `tbl_cliente` DISABLE KEYS */;
INSERT INTO `tbl_cliente` VALUES ('CL0001','TP01','TD01','10469040955','JOSE SALAS','JOSE@GMAIL.COM','S 3 G8 MZ R LT 9 VILLA EL SALVADOR',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL),('CL0002','TP01','TD01','10469040956','JOSIAS PEREZ','JOSIAS@GMAIL.COM','S 3 G8 MZ R LT 9 VILLA EL SALVADOR',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL),('CL0003','TP02','TD02','46904098','DIANA SANCHEZ','DIANA@GMAIL.COM','S 3 G8 MZ R LT 9 VILLA EL SALVADOR',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL),('CL0004','TP02','TD02','46904099','DAYANA LUCAS','DAYANA@GMAIL.COM','S 3 G8 MZ R LT 9 VILLA EL SALVADOR',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL);
/*!40000 ALTER TABLE `tbl_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_comprobante`
--

DROP TABLE IF EXISTS `tbl_comprobante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_comprobante` (
  `V_COD_COMPROBANTE` varchar(7) NOT NULL,
  `V_COD_TIPO_COMPROBANTE` varchar(4) NOT NULL,
  `V_COD_SERIE` varchar(4) NOT NULL,
  `N_NUMERO` int NOT NULL,
  `D_FEC_EMISION` date NOT NULL,
  `D_FEC_VENCIMIENTO` date NOT NULL,
  `V_COD_CLIENTE` varchar(6) NOT NULL,
  `V_GLOSA` varchar(250) DEFAULT NULL,
  `N_SUBTOTAL` decimal(10,2) DEFAULT NULL,
  `N_IGV` decimal(10,2) DEFAULT NULL,
  `N_TOTAL` decimal(10,2) NOT NULL,
  `N_ACTIVO` int NOT NULL,
  `V_COD_USU_REGISTRA` varchar(20) NOT NULL,
  `D_FEC_REGISTRO` datetime NOT NULL,
  `V_COD_USU_MODIFICA` varchar(20) DEFAULT NULL,
  `D_FEC_MODIFICACION` datetime DEFAULT NULL,
  PRIMARY KEY (`V_COD_COMPROBANTE`),
  UNIQUE KEY `V_COD_COMPROBANTE_UNIQUE` (`V_COD_COMPROBANTE`),
  KEY `fk_tbl_tipo_comprobante_tbl_comprobante_idx` (`V_COD_TIPO_COMPROBANTE`),
  KEY `fk_tbl_cliente_tbl_comprobante_idx` (`V_COD_CLIENTE`),
  CONSTRAINT `fk_tbl_cliente_tbl_comprobante` FOREIGN KEY (`V_COD_CLIENTE`) REFERENCES `tbl_cliente` (`V_COD_CLIENTE`),
  CONSTRAINT `fk_tbl_tipo_comprobante_tbl_comprobante` FOREIGN KEY (`V_COD_TIPO_COMPROBANTE`) REFERENCES `tbl_tipo_comprobante` (`V_COD_TIPO_COMPROBANTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_comprobante`
--

LOCK TABLES `tbl_comprobante` WRITE;
/*!40000 ALTER TABLE `tbl_comprobante` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_comprobante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_detalle_comprobante`
--

DROP TABLE IF EXISTS `tbl_detalle_comprobante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_detalle_comprobante` (
  `V_COD_DETALLE_COMPROBANTE` varchar(9) NOT NULL,
  `V_COD_COMPROBANTE` varchar(7) NOT NULL,
  `V_COD_ARTICULO` varchar(9) NOT NULL,
  `N_CANTIDAD` int NOT NULL,
  `N_PRECIO_UNITARIO` decimal(10,2) NOT NULL,
  `N_PRECIO_TOTAL` decimal(10,2) NOT NULL,
  `N_ACTIVO` int NOT NULL,
  `V_COD_USU_REGISTRA` varchar(20) NOT NULL,
  `D_FEC_REGISTRO` datetime NOT NULL,
  `V_COD_USU_MODIFICA` varchar(20) DEFAULT NULL,
  `D_FEC_MODIFICACION` datetime DEFAULT NULL,
  PRIMARY KEY (`V_COD_DETALLE_COMPROBANTE`),
  UNIQUE KEY `V_COD_DETALLE_COMPROBANTE_UNIQUE` (`V_COD_DETALLE_COMPROBANTE`),
  KEY `fk_tbl_comprobante_tbl_detalle_comprobante_idx` (`V_COD_COMPROBANTE`),
  KEY `fk_tbl_articulo_tbl_detalle_comprobante_idx` (`V_COD_ARTICULO`),
  CONSTRAINT `fk_tbl_articulo_tbl_detalle_comprobante` FOREIGN KEY (`V_COD_ARTICULO`) REFERENCES `tbl_articulo` (`V_COD_ARTICULO`),
  CONSTRAINT `fk_tbl_comprobante_tbl_detalle_comprobante` FOREIGN KEY (`V_COD_COMPROBANTE`) REFERENCES `tbl_comprobante` (`V_COD_COMPROBANTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_detalle_comprobante`
--

LOCK TABLES `tbl_detalle_comprobante` WRITE;
/*!40000 ALTER TABLE `tbl_detalle_comprobante` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_detalle_comprobante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_serie`
--

DROP TABLE IF EXISTS `tbl_serie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_serie` (
  `V_COD_SERIE` varchar(4) NOT NULL,
  `N_VALOR_SGTE` int NOT NULL,
  `V_COD_TIPO_COMPROBANTE` varchar(4) NOT NULL,
  `N_ACTIVO` int NOT NULL,
  `V_COD_USU_REGISTRA` varchar(20) NOT NULL,
  `D_FEC_REGISTRO` datetime NOT NULL,
  `V_COD_USU_MODIFICA` varchar(20) DEFAULT NULL,
  `D_FEC_MODIFICACION` datetime DEFAULT NULL,
  PRIMARY KEY (`V_COD_SERIE`),
  UNIQUE KEY `V_COD_SERIE_UNIQUE` (`V_COD_SERIE`),
  KEY `fk_tbl_tipo_comprobante_tbl_serie_idx` (`V_COD_TIPO_COMPROBANTE`),
  CONSTRAINT `fk_tbl_tipo_comprobante_tbl_serie` FOREIGN KEY (`V_COD_TIPO_COMPROBANTE`) REFERENCES `tbl_tipo_comprobante` (`V_COD_TIPO_COMPROBANTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_serie`
--

LOCK TABLES `tbl_serie` WRITE;
/*!40000 ALTER TABLE `tbl_serie` DISABLE KEYS */;
INSERT INTO `tbl_serie` VALUES ('BE01',2,'TC02',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL),('BE02',2,'TC02',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL),('BE03',2,'TC02',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL),('BE04',1,'TC02',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL),('FE01',4,'TC01',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL),('FE02',1,'TC01',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL),('FE03',2,'TC01',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL),('FE04',2,'TC01',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL);
/*!40000 ALTER TABLE `tbl_serie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_tipo_comprobante`
--

DROP TABLE IF EXISTS `tbl_tipo_comprobante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_tipo_comprobante` (
  `V_COD_TIPO_COMPROBANTE` varchar(4) NOT NULL,
  `V_DESCRIPCION` varchar(150) DEFAULT NULL,
  `N_ACTIVO` int NOT NULL,
  `V_COD_USU_REGISTRA` varchar(20) NOT NULL,
  `D_FEC_REGISTRO` datetime NOT NULL,
  `V_COD_USU_MODIFICA` varchar(20) DEFAULT NULL,
  `D_FEC_MODIFICACION` datetime DEFAULT NULL,
  PRIMARY KEY (`V_COD_TIPO_COMPROBANTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_tipo_comprobante`
--

LOCK TABLES `tbl_tipo_comprobante` WRITE;
/*!40000 ALTER TABLE `tbl_tipo_comprobante` DISABLE KEYS */;
INSERT INTO `tbl_tipo_comprobante` VALUES ('TC01','FACTURA',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL),('TC02','BOLETA',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL);
/*!40000 ALTER TABLE `tbl_tipo_comprobante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_tipo_documento`
--

DROP TABLE IF EXISTS `tbl_tipo_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_tipo_documento` (
  `V_COD_TIPO_DOCUMENTO` varchar(4) NOT NULL,
  `V_DESCRIPCION` varchar(150) DEFAULT NULL,
  `N_ACTIVO` int NOT NULL,
  `V_COD_USU_REGISTRA` varchar(20) NOT NULL,
  `D_FEC_REGISTRO` datetime NOT NULL,
  `V_COD_USU_MODIFICA` varchar(20) DEFAULT NULL,
  `D_FEC_MODIFICACION` datetime DEFAULT NULL,
  PRIMARY KEY (`V_COD_TIPO_DOCUMENTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_tipo_documento`
--

LOCK TABLES `tbl_tipo_documento` WRITE;
/*!40000 ALTER TABLE `tbl_tipo_documento` DISABLE KEYS */;
INSERT INTO `tbl_tipo_documento` VALUES ('TD01','RUC',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL),('TD02','DNI',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL),('TD03','CARNET DE EXTRANJERIA',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL),('TD04','PASAPORTE',1,'ADMIN','2022-12-13 00:00:00',NULL,NULL);
/*!40000 ALTER TABLE `tbl_tipo_documento` ENABLE KEYS */;
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

-- Dump completed on 2022-12-14 19:04:23
