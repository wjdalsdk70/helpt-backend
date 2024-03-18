-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.36 - MySQL Community Server - GPL
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- helptdb 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `helptdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `helptdb`;

-- 테이블 helptdb.admin 구조 내보내기
CREATE TABLE IF NOT EXISTS `admin` (
  `userId` int unsigned NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.equipment 구조 내보내기
CREATE TABLE IF NOT EXISTS `equipment` (
  `userId` int unsigned NOT NULL,
  `password` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.gym 구조 내보내기
CREATE TABLE IF NOT EXISTS `gym` (
  `gymId` int unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(50) NOT NULL,
  `gymName` varchar(50) NOT NULL,
  PRIMARY KEY (`gymId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.manager 구조 내보내기
CREATE TABLE IF NOT EXISTS `manager` (
  `managerId` int unsigned NOT NULL,
  `token` varchar(50) NOT NULL DEFAULT '',
  `gymId` int unsigned NOT NULL,
  PRIMARY KEY (`managerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `userId` int unsigned NOT NULL AUTO_INCREMENT,
  `gymId` int unsigned DEFAULT NULL,
  `token` varchar(50) NOT NULL DEFAULT '',
  `gender` varchar(50) DEFAULT '',
  `height` float unsigned DEFAULT (0),
  `weight` float unsigned DEFAULT (0),
  `userName` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.membership 구조 내보내기
CREATE TABLE IF NOT EXISTS `membership` (
  `membershipId` int unsigned NOT NULL AUTO_INCREMENT,
  `gymId` int unsigned DEFAULT NULL,
  `attendanceDate` int unsigned DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `userId` int unsigned DEFAULT NULL,
  PRIMARY KEY (`membershipId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.notice 구조 내보내기
CREATE TABLE IF NOT EXISTS `notice` (
  `noticeId` int unsigned NOT NULL AUTO_INCREMENT,
  `gymId` int unsigned DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content` varchar(50) DEFAULT NULL,
  `createAt` date DEFAULT NULL,
  PRIMARY KEY (`noticeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.product 구조 내보내기
CREATE TABLE IF NOT EXISTS `product` (
  `productId` int unsigned NOT NULL AUTO_INCREMENT,
  `gymId` int unsigned DEFAULT NULL,
  `day` int unsigned DEFAULT NULL,
  `price` int unsigned DEFAULT '0',
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.record 구조 내보내기
CREATE TABLE IF NOT EXISTS `record` (
  `recordId` int unsigned NOT NULL,
  `userId` int unsigned DEFAULT NULL,
  `equipmentId` int unsigned DEFAULT NULL,
  `count` int unsigned DEFAULT NULL,
  `set` int unsigned DEFAULT NULL,
  `weight` int unsigned DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `successRate` float unsigned DEFAULT NULL,
  PRIMARY KEY (`recordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
