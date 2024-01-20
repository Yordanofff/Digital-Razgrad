-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: db:3306
-- Generation Time: Jan 19, 2024 at 04:15 PM
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
-- Database: `real_estate`
--

-- --------------------------------------------------------

--
-- Table structure for table `broker`
--

CREATE TABLE `broker` (
  `broker_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `broker`
--

INSERT INTO `broker` (`broker_id`, `first_name`, `last_name`, `email`) VALUES
(2, 'Ivan', 'Ivanov', 'ivan@abv.bg'),
(3, 'Georgi', 'Marinov', 'goshom@abv.bg'),
(4, 'Penka', 'Ivanova', 'penito@abv.bg');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`client_id`, `first_name`, `last_name`, `email`, `phone_number`) VALUES
(1, 'Penka', 'Ivanova', 'penito@abv.bg', '1233'),
(2, 'Goshka', 'Ivanova', 'goshka@abv.bg', NULL),
(3, 'Minka', 'Minkova', NULL, '111222');

-- --------------------------------------------------------

--
-- Table structure for table `neighborhood`
--

CREATE TABLE `neighborhood` (
  `neighborhood_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `neighborhood`
--

INSERT INTO `neighborhood` (`neighborhood_id`, `name`) VALUES
(1, 'Center'),
(2, 'Orel'),
(3, 'Park');

-- --------------------------------------------------------

--
-- Table structure for table `property`
--

CREATE TABLE `property` (
  `property_id` int(11) NOT NULL,
  `neighborhood_id` int(11) NOT NULL,
  `property_type_id` int(11) NOT NULL,
  `address` text NOT NULL,
  `area_sqm` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `rental_price_pcm` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `property`
--

INSERT INTO `property` (`property_id`, `neighborhood_id`, `property_type_id`, `address`, `area_sqm`, `price`, `rental_price_pcm`) VALUES
(1, 1, 1, 'ul. Ivan Ivanov 2', 60, 120000, 500),
(2, 1, 2, 'ul. Peter Ivanov 22', 80, 160000, 700),
(3, 2, 3, 'bl.1 vh.A ap.2', 64, 80000, 0),
(4, 3, 2, 'ul. Parkova 54', 82, 140000, 640);

-- --------------------------------------------------------

--
-- Table structure for table `property_type`
--

CREATE TABLE `property_type` (
  `property_type_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `property_type`
--

INSERT INTO `property_type` (`property_type_id`, `name`) VALUES
(1, '1 bedroom'),
(2, '2 bedroom'),
(3, '3 bedroom');

-- --------------------------------------------------------

--
-- Table structure for table `property_view`
--

CREATE TABLE `property_view` (
  `property_view_id` int(11) NOT NULL,
  `property_id` int(11) NOT NULL,
  `broker_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `property_view`
--

INSERT INTO `property_view` (`property_view_id`, `property_id`, `broker_id`, `client_id`, `date`) VALUES
(1, 3, 3, 2, '2024-01-19'),
(2, 1, 2, 2, '2024-01-18'),
(3, 4, 2, 1, '2024-01-17');

-- --------------------------------------------------------

--
-- Table structure for table `rental`
--

CREATE TABLE `rental` (
  `rental_id` int(11) NOT NULL,
  `property_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `broker_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `security_deposit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rental`
--

INSERT INTO `rental` (`rental_id`, `property_id`, `client_id`, `broker_id`, `status_id`, `start_date`, `end_date`, `security_deposit`) VALUES
(1, 3, 2, 2, 2, '2023-12-01', '2023-12-31', 1000),
(2, 2, 2, 3, 4, '2023-10-01', '2024-03-31', 800),
(3, 2, 2, 4, 4, '2023-01-01', '2023-05-31', 550);

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `sale_id` int(11) NOT NULL,
  `property_id` int(11) NOT NULL,
  `broker_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `date_of_sale` date NOT NULL,
  `sold_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`sale_id`, `property_id`, `broker_id`, `client_id`, `date_of_sale`, `sold_price`) VALUES
(1, 2, 4, 3, '2024-01-20', 140000);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`id`, `name`) VALUES
(2, 'With furniture'),
(3, 'Not suitable for living'),
(4, 'without furniture');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `broker`
--
ALTER TABLE `broker`
  ADD PRIMARY KEY (`broker_id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `neighborhood`
--
ALTER TABLE `neighborhood`
  ADD PRIMARY KEY (`neighborhood_id`);

--
-- Indexes for table `property`
--
ALTER TABLE `property`
  ADD PRIMARY KEY (`property_id`),
  ADD KEY `neighborhood_id` (`neighborhood_id`),
  ADD KEY `property_type_id` (`property_type_id`);

--
-- Indexes for table `property_type`
--
ALTER TABLE `property_type`
  ADD PRIMARY KEY (`property_type_id`);

--
-- Indexes for table `property_view`
--
ALTER TABLE `property_view`
  ADD PRIMARY KEY (`property_view_id`),
  ADD KEY `property_id` (`property_id`),
  ADD KEY `broker_id` (`broker_id`,`client_id`),
  ADD KEY `client_id` (`client_id`);

--
-- Indexes for table `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`rental_id`),
  ADD KEY `property_id` (`property_id`,`client_id`,`broker_id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `broker_id` (`broker_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`sale_id`),
  ADD KEY `property_id` (`property_id`),
  ADD KEY `broker_id` (`broker_id`),
  ADD KEY `client_id` (`client_id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `broker`
--
ALTER TABLE `broker`
  MODIFY `broker_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `neighborhood`
--
ALTER TABLE `neighborhood`
  MODIFY `neighborhood_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `property`
--
ALTER TABLE `property`
  MODIFY `property_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `property_type`
--
ALTER TABLE `property_type`
  MODIFY `property_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `property_view`
--
ALTER TABLE `property_view`
  MODIFY `property_view_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `rental`
--
ALTER TABLE `rental`
  MODIFY `rental_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `sale_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `property`
--
ALTER TABLE `property`
  ADD CONSTRAINT `property_ibfk_1` FOREIGN KEY (`neighborhood_id`) REFERENCES `neighborhood` (`neighborhood_id`),
  ADD CONSTRAINT `property_ibfk_2` FOREIGN KEY (`property_type_id`) REFERENCES `property_type` (`property_type_id`);

--
-- Constraints for table `property_view`
--
ALTER TABLE `property_view`
  ADD CONSTRAINT `property_view_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  ADD CONSTRAINT `property_view_ibfk_3` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`),
  ADD CONSTRAINT `property_view_ibfk_4` FOREIGN KEY (`broker_id`) REFERENCES `broker` (`broker_id`);

--
-- Constraints for table `rental`
--
ALTER TABLE `rental`
  ADD CONSTRAINT `rental_ibfk_1` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`),
  ADD CONSTRAINT `rental_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  ADD CONSTRAINT `rental_ibfk_3` FOREIGN KEY (`broker_id`) REFERENCES `broker` (`broker_id`),
  ADD CONSTRAINT `rental_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`);

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`),
  ADD CONSTRAINT `sales_ibfk_3` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  ADD CONSTRAINT `sales_ibfk_4` FOREIGN KEY (`broker_id`) REFERENCES `broker` (`broker_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
