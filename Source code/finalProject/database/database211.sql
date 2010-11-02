-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.37-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema dbsa
--

CREATE DATABASE IF NOT EXISTS dbsa;
USE dbsa;

--
-- Definition of table `dbsa_pub`
--

DROP TABLE IF EXISTS `dbsa_pub`;
CREATE TABLE `dbsa_pub` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT 'Id cua bai bao duoc thu thap ve tu he thong DBSA',
  `sbj_id` int(8) DEFAULT NULL COMMENT 'Id cua tua de bai bao sau khi phan loai',
  `astract` longtext COMMENT 'Tom tat cua bai bao',
  `title` longtext COMMENT 'Tua de cua bai bao',
  `year` int(4) unsigned DEFAULT NULL COMMENT 'Nam xuat ban cua bai bao',
  `publisher` varchar(255) DEFAULT NULL COMMENT 'Nha xuat ban tai lieu',
  `authors` varchar(250) DEFAULT NULL COMMENT 'Ten cac tac gia cua bai bao',
  `links` longtext COMMENT 'Cac duong dan mo rong cua bai bao',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dbsa_pub`
--

/*!40000 ALTER TABLE `dbsa_pub` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbsa_pub` ENABLE KEYS */;


--
-- Definition of table `dbsa_pub_in_dblp`
--

DROP TABLE IF EXISTS `dbsa_pub_in_dblp`;
CREATE TABLE `dbsa_pub_in_dblp` (
  `id` int(8) DEFAULT NULL COMMENT 'id cua bai bao trong du lieu dblp',
  `sbj_id` int(8) DEFAULT NULL COMMENT 'id cua bang chu de bai bao',
  `link` varchar(250) DEFAULT NULL COMMENT 'Cac lien ket mo rong cua bai bao'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dbsa_pub_in_dblp`
--

/*!40000 ALTER TABLE `dbsa_pub_in_dblp` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbsa_pub_in_dblp` ENABLE KEYS */;


--
-- Definition of table `dbsa_sbj`
--

DROP TABLE IF EXISTS `dbsa_sbj`;
CREATE TABLE `dbsa_sbj` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id cua chu de bai bao',
  `subj_name` varchar(250) DEFAULT NULL COMMENT 'Ten cua chu de bai bao',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Luu thong tin chu de bai bao';

--
-- Dumping data for table `dbsa_sbj`
--

/*!40000 ALTER TABLE `dbsa_sbj` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbsa_sbj` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
