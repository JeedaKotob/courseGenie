-- docker compose exec -T mysql sh -c \
--   'mysql -uroot -p"$MYSQL_ROOT_PASSWORD" course_genie' < ../queries/merged_database.sql
-- MySQL dump 10.13  Distrib 8.0.43, for Linux (x86_64)
--
-- Host: localhost    Database: course_genie
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `course_genie`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `course_genie` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `course_genie`;

--
-- Table structure for table `assessment`
--

DROP TABLE IF EXISTS `assessment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assessment` (
  `assessment_id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(50) DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `max_points` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `topic` int DEFAULT NULL,
  `week` int DEFAULT NULL,
  `section_id` bigint NOT NULL,
  PRIMARY KEY (`assessment_id`),
  KEY `FKbntms95sroxoaitgq9blocyn8` (`section_id`),
  CONSTRAINT `FKbntms95sroxoaitgq9blocyn8` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assessment_clo`
--

DROP TABLE IF EXISTS `assessment_clo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assessment_clo` (
  `assessment_id` bigint NOT NULL,
  `clo_id` bigint NOT NULL,
  KEY `FK8i64anmg2pk5yv5uxi5mknh19` (`clo_id`),
  KEY `FKpjhb42f0f5rwjeui1se6vit80` (`assessment_id`),
  CONSTRAINT `FK8i64anmg2pk5yv5uxi5mknh19` FOREIGN KEY (`clo_id`) REFERENCES `clo` (`clo_id`),
  CONSTRAINT `FKpjhb42f0f5rwjeui1se6vit80` FOREIGN KEY (`assessment_id`) REFERENCES `assessment` (`assessment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `benchmark`
--

DROP TABLE IF EXISTS `benchmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `benchmark` (
  `benchmark_id` bigint NOT NULL AUTO_INCREMENT,
  `benchmark_type` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `percentage` int NOT NULL,
  `threshold` int NOT NULL,
  PRIMARY KEY (`benchmark_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `isbn` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKehpdfjpu1jm3hijhj4mm0hx9h` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `book_section`
--

DROP TABLE IF EXISTS `book_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_section` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `required_reading` bit(1) NOT NULL,
  `suggested_reading` bit(1) NOT NULL,
  `book_id` bigint NOT NULL,
  `section_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlqw1f699abdi8adycjhv03v9p` (`book_id`),
  KEY `FK6jaty68gcrxsx9yr5gcb15shm` (`section_id`),
  CONSTRAINT `FK6jaty68gcrxsx9yr5gcb15shm` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`),
  CONSTRAINT `FKlqw1f699abdi8adycjhv03v9p` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category_description`
--

DROP TABLE IF EXISTS `category_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_description` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `section_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKj57sh0tumkq0vunybtwwtb8f9` (`section_id`,`category_name`),
  CONSTRAINT `FKtbhnsomprlp3abt2gv1o8f6fy` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `clo`
--

DROP TABLE IF EXISTS `clo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clo` (
  `clo_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `course_id` bigint NOT NULL,
  PRIMARY KEY (`clo_id`),
  KEY `FKanecitw3i1p2gm5hl1g4tou31` (`course_id`),
  CONSTRAINT `FKanecitw3i1p2gm5hl1g4tou31` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `corequisites` varchar(255) DEFAULT NULL,
  `course_approval_date` varchar(255) DEFAULT NULL,
  `course_deletion` tinyint(1) NOT NULL DEFAULT '0',
  `credits` varchar(255) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `designated_innovation_journey_course` tinyint(1) DEFAULT NULL,
  `discipline` varchar(255) DEFAULT NULL,
  `graduate` tinyint(1) NOT NULL DEFAULT '0',
  `last_revision_date` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `new_course` tinyint(1) NOT NULL DEFAULT '0',
  `prerequisites` varchar(255) DEFAULT NULL,
  `undergraduate` tinyint(1) NOT NULL DEFAULT '0',
  `semester` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade` (
  `grade_id` bigint NOT NULL AUTO_INCREMENT,
  `score` double NOT NULL,
  `assessment_id` bigint NOT NULL,
  `student_id` varchar(255) NOT NULL,
  PRIMARY KEY (`grade_id`),
  KEY `FKbai458q61mil6r9xb8rnan288` (`assessment_id`),
  KEY `FK5secqnjjwgh9wxk4h1xwgj1n0` (`student_id`),
  CONSTRAINT `FK5secqnjjwgh9wxk4h1xwgj1n0` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `FKbai458q61mil6r9xb8rnan288` FOREIGN KEY (`assessment_id`) REFERENCES `assessment` (`assessment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor` (
  `professor_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `office` varchar(255) DEFAULT NULL,
  `office_hours` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`professor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `professor_roles`
--

DROP TABLE IF EXISTS `professor_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor_roles` (
  `professor_id` bigint NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  KEY `FKqqcoc0up79w7h39u0hn9sl36q` (`professor_id`),
  CONSTRAINT `FKqqcoc0up79w7h39u0hn9sl36q` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`professor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `section` (
  `section_id` bigint NOT NULL AUTO_INCREMENT,
  `class_number` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `configured` bit(1) NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `schedule` varchar(255) DEFAULT NULL,
  `teaching_methodology` varchar(2000) DEFAULT NULL,
  `term` varchar(255) DEFAULT NULL,
  `course_id` bigint NOT NULL,
  `professor_id` bigint NOT NULL,
  PRIMARY KEY (`section_id`),
  KEY `FKoy8uc0ftpivwopwf5ptwdtar0` (`course_id`),
  KEY `FKlppf099tookr65umsa4vuem80` (`professor_id`),
  CONSTRAINT `FKlppf099tookr65umsa4vuem80` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`professor_id`),
  CONSTRAINT `FKoy8uc0ftpivwopwf5ptwdtar0` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `syllabus`
--

DROP TABLE IF EXISTS `syllabus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `syllabus` (
  `syllabus_id` bigint NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `section_id` bigint NOT NULL,
  PRIMARY KEY (`syllabus_id`),
  UNIQUE KEY `UK8iilpqep5bm2rmmtdx5m8fmr1` (`section_id`),
  CONSTRAINT `FKohgkt14marnts6y4extd5inxk` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-17 16:03:16
