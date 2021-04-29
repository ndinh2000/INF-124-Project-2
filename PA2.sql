DROP DATABASE IF EXISTS `petstore`; 
CREATE DATABASE IF NOT EXISTS `petstore`;
USE `petstore`;

SET FOREIGN_KEY_CHECKS=0;	-- ???

--
-- Table structure for table `Pet`
--

CREATE TABLE `Pet` (
  `pet_id` varchar(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `age` int NOT NULL,
  `gender` enum('male', 'female'),
  `price` float NOT NULL,
  `message` varchar(1024) NOT NULL,
  `profile_picture` varchar(2048) NOT NULL,
  PRIMARY KEY (`pet_id`)
);

INSERT INTO `Pet` VALUES ('D101', 'Lucky', '3', 'male', '2000', 'I am a beautiful boy.' , './pics/1.png');

--
-- Table structure for table `Order`
--

CREATE TABLE `Order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `pet_id` varchar(10) NOT NULL,
  `price` float NOT NULL,
  `name_first` varchar(50) NOT NULL,
  `name_last` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address_country` varchar(30) NOT NULL,
  `address_zipcode` varchar(30) DEFAULT NULL,
  `address_state` varchar(30) DEFAULT NULL,
  `address_city` varchar(30) NOT NULL,
  `address_street` varchar(30) NOT NULL,
  `order_time` datetime NOT NULL,
  `card_number` varchar(30) NOT NULL,
  `expiration_date` datetime NOT NULL,
  shipping_method enum('ground', 'first_overnight', 'two_days'),
  PRIMARY KEY (`order_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `Pet` (`pet_id`) ON DELETE NO ACTION
);