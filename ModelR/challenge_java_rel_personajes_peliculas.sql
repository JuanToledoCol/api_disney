-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: challenge_java
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
-- Table structure for table `rel_personajes_peliculas`
--

DROP TABLE IF EXISTS `rel_personajes_peliculas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rel_personajes_peliculas` (
  `id_rel_personaje_pelicula` bigint NOT NULL AUTO_INCREMENT,
  `fk_id_pelicula_serie` bigint NOT NULL,
  `fk_id_personaje` bigint NOT NULL,
  PRIMARY KEY (`id_rel_personaje_pelicula`),
  KEY `FKehsl0lwc8lp8as9tc5n5m8417` (`fk_id_pelicula_serie`),
  KEY `FKs7bvutlw9y087q8taolbq0n1w` (`fk_id_personaje`),
  CONSTRAINT `FKehsl0lwc8lp8as9tc5n5m8417` FOREIGN KEY (`fk_id_pelicula_serie`) REFERENCES `peliculas_series` (`id_pelicula_serie`),
  CONSTRAINT `FKs7bvutlw9y087q8taolbq0n1w` FOREIGN KEY (`fk_id_personaje`) REFERENCES `personajes` (`id_personaje`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rel_personajes_peliculas`
--

LOCK TABLES `rel_personajes_peliculas` WRITE;
/*!40000 ALTER TABLE `rel_personajes_peliculas` DISABLE KEYS */;
INSERT INTO `rel_personajes_peliculas` VALUES (1,2,1),(2,2,2),(3,2,3),(4,3,4),(5,4,5),(6,1,1),(7,1,5);
/*!40000 ALTER TABLE `rel_personajes_peliculas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-13  0:40:03
