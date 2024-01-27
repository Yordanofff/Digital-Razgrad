-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: db:3306
-- Generation Time: Jan 21, 2024 at 02:13 PM
-- Server version: 11.2.2-MariaDB-1:11.2.2+maria~ubu2204
-- PHP Version: 8.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `active_varna`
--

-- --------------------------------------------------------

--
-- Table structure for table `account_category`
--

CREATE TABLE `account_category` (
  `category_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account_category`
--

INSERT INTO `account_category` (`category_id`, `user_id`) VALUES
(2, 2),
(4, 1),
(4, 2),
(4, 3),
(8, 1),
(8, 2),
(12, 1),
(12, 2);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `category_name`) VALUES
(1, 'проблеми на жените'),
(2, 'социални услуги'),
(3, 'работа с деца'),
(4, 'спорт'),
(5, 'социално предприятие'),
(6, 'здравеопазване'),
(7, 'насърчаване на дарителство/доброволчество'),
(8, 'защита на човешките права'),
(9, 'развитие на местни общности'),
(10, 'работа с младежи'),
(11, 'околна среда'),
(12, 'образование');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `organisation_name` varchar(40) NOT NULL,
  `password` varchar(50) NOT NULL,
  `logo_path` varchar(255) NOT NULL,
  `wallpaper_path` varchar(255) NOT NULL,
  `facebook_url` varchar(100) NOT NULL,
  `email` varchar(40) NOT NULL,
  `mission` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `organisation_name`, `password`, `logo_path`, `wallpaper_path`, `facebook_url`, `email`, `mission`) VALUES
(1, 'Kaufland', 'kf123', 'kf logo path', 'kf wallpaper path', 'kf fb page', 'kf@abv.bg', 'kf mission'),
(2, 'Lidl', 'ld123', 'lidl logo path', 'lidl wallpaper path', 'lidl fb page', 'lidl@abv.bg', 'lidl mission'),
(3, 'T-market', 'T-market123', 'T-market logo', 'T-market wall', 'T-market fb', 'T-market@abv.bg', 'T-market mission');

-- --------------------------------------------------------

--
-- Table structure for table `user_admin_data`
--

CREATE TABLE `user_admin_data` (
  `user_id` int(11) NOT NULL,
  `full_contact_name` varchar(50) NOT NULL,
  `phone_number` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_admin_data`
--

INSERT INTO `user_admin_data` (`user_id`, `full_contact_name`, `phone_number`) VALUES
(1, 'Kaulina Kaulinova', '1122'),
(2, 'Lidka Lidkova', '2233'),
(3, 'Temenujka Marketova', '3344');

-- --------------------------------------------------------

--
-- Table structure for table `user_non_mandatory`
--

CREATE TABLE `user_non_mandatory` (
  `user_id` int(11) NOT NULL,
  `address` varchar(150) DEFAULT NULL,
  `bulstat` varchar(40) DEFAULT NULL,
  `website_url` varchar(100) DEFAULT NULL,
  `donations_url` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_non_mandatory`
--

INSERT INTO `user_non_mandatory` (`user_id`, `address`, `bulstat`, `website_url`, `donations_url`) VALUES
(1, 'Razgrad', NULL, NULL, NULL),
(2, 'Varna', '12344123AA', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account_category`
--
ALTER TABLE `account_category`
  ADD KEY `category_id` (`category_id`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_admin_data`
--
ALTER TABLE `user_admin_data`
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user_non_mandatory`
--
ALTER TABLE `user_non_mandatory`
  ADD UNIQUE KEY `user_id_2` (`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account_category`
--
ALTER TABLE `account_category`
  ADD CONSTRAINT `account_category_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `account_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`);

--
-- Constraints for table `user_admin_data`
--
ALTER TABLE `user_admin_data`
  ADD CONSTRAINT `user_admin_data_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `user_non_mandatory`
--
ALTER TABLE `user_non_mandatory`
  ADD CONSTRAINT `user_non_mandatory_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
