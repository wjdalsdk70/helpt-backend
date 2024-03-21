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
  `admin_id` bigint unsigned NOT NULL DEFAULT '0',
  `login_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.equipment 구조 내보내기
CREATE TABLE IF NOT EXISTS `equipment` (
  `user_id` bigint unsigned NOT NULL DEFAULT '0',
  `password` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.gym 구조 내보내기
CREATE TABLE IF NOT EXISTS `gym` (
  `gym_id` bigint unsigned NOT NULL DEFAULT (0),
  `address` varchar(50) NOT NULL,
  `gym_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`gym_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.manager 구조 내보내기
CREATE TABLE IF NOT EXISTS `manager` (
  `manager_id` bigint unsigned NOT NULL DEFAULT (0),
  `token` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0',
  `gym_id` bigint unsigned NOT NULL DEFAULT (0),
  PRIMARY KEY (`manager_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `user_id` bigint unsigned NOT NULL DEFAULT (0),
  `gym_id` bigint unsigned DEFAULT NULL,
  `access_token` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `refresh_token` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `gender` varchar(50) DEFAULT '',
  `height` float unsigned DEFAULT (0),
  `weight` float unsigned DEFAULT (0),
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.membership 구조 내보내기
CREATE TABLE IF NOT EXISTS `membership` (
  `membership_id` bigint unsigned NOT NULL DEFAULT (0),
  `gym_id` bigint unsigned NOT NULL DEFAULT (0),
  `attendance_date` int unsigned NOT NULL DEFAULT (0),
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `user_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`membership_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.notice 구조 내보내기
CREATE TABLE IF NOT EXISTS `notice` (
  `notice_id` bigint unsigned NOT NULL DEFAULT (0),
  `gym_id` bigint unsigned DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content` varchar(50) DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.product 구조 내보내기
CREATE TABLE IF NOT EXISTS `product` (
  `product_id` bigint unsigned NOT NULL DEFAULT (0),
  `gym_id` bigint unsigned DEFAULT NULL,
  `day` int unsigned DEFAULT NULL,
  `price` int unsigned DEFAULT '0',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 helptdb.record 구조 내보내기
CREATE TABLE IF NOT EXISTS `record` (
  `record_id` bigint unsigned NOT NULL DEFAULT (0),
  `user_id` bigint unsigned DEFAULT NULL,
  `equipment_id` bigint unsigned DEFAULT NULL,
  `count` int unsigned DEFAULT NULL,
  `set` int unsigned DEFAULT NULL,
  `weight` int unsigned DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `success_rate` float unsigned DEFAULT NULL,
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
