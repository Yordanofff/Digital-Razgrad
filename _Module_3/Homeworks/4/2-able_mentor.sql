-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: db:3306
-- Generation Time: Jan 19, 2024 at 01:10 PM
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
-- Database: `able_mentor`
--

-- --------------------------------------------------------

--
-- Table structure for table `able_program`
--

CREATE TABLE `able_program` (
  `id` int(11) NOT NULL,
  `hours_per_week_for_project_id` int(11) NOT NULL,
  `reason_to_apply_id` int(11) NOT NULL,
  `project_to_work_with_a_mentor_id` int(11) NOT NULL,
  `convenient_participation_format_id` int(11) NOT NULL,
  `why_i_want_to_be_part_of_the_program` text NOT NULL,
  `goals_i_want_to_achive_in_this_program` text NOT NULL,
  `how_i_learned_about_program` text NOT NULL,
  `other_programs` text NOT NULL,
  `allow_my_data_to_be_used` tinyint(1) NOT NULL DEFAULT 0,
  `allow_photo_video_of_myself` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `able_program`
--

INSERT INTO `able_program` (`id`, `hours_per_week_for_project_id`, `reason_to_apply_id`, `project_to_work_with_a_mentor_id`, `convenient_participation_format_id`, `why_i_want_to_be_part_of_the_program`, `goals_i_want_to_achive_in_this_program`, `how_i_learned_about_program`, `other_programs`, `allow_my_data_to_be_used`, `allow_photo_video_of_myself`) VALUES
(1, 1, 4, 3, 3, 'I don\'t know', 'I wanna make a lot of friends', 'From a friend', 'No', 1, 1),
(2, 1, 2, 1, 3, 'Because I love new challenges', 'I want to learn new stuff', 'From a friend\'s friend', 'No, this is the first one', 1, 0),
(3, 1, 6, 2, 1, 'I have to do it', 'I want to learn new stuff and meet new people', 'From my parents', 'Softuni, Vratsa soft and others', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `area_i_wish_to_develop`
--

CREATE TABLE `area_i_wish_to_develop` (
  `id` int(11) NOT NULL,
  `area_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `area_i_wish_to_develop`
--

INSERT INTO `area_i_wish_to_develop` (`id`, `area_name`) VALUES
(1, 'Aviation'),
(2, 'Automobile industry'),
(3, 'Animation'),
(4, 'Architecture'),
(5, 'Astronomy');

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`id`, `name`) VALUES
(1, 'Razgrad'),
(2, 'Ruse'),
(3, 'Varna'),
(4, 'Sofia');

-- --------------------------------------------------------

--
-- Table structure for table `convenient_participation_format`
--

CREATE TABLE `convenient_participation_format` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `convenient_participation_format`
--

INSERT INTO `convenient_participation_format` (`id`, `name`) VALUES
(1, 'meeting in Sofia'),
(2, 'online'),
(3, 'both');

-- --------------------------------------------------------

--
-- Table structure for table `current_study`
--

CREATE TABLE `current_study` (
  `id` int(11) NOT NULL,
  `school_id` int(11) NOT NULL,
  `english_level_id` int(11) NOT NULL,
  `last_finished_grade_id` int(4) NOT NULL,
  `favourite_school_subjects` varchar(200) NOT NULL,
  `personal_challenge_and_solution_for_it` text NOT NULL,
  `plans_after_graduation` text NOT NULL,
  `personal_qualities_i_want_to_improve` text NOT NULL,
  `how_i_have_fun_in_my_free_time` text NOT NULL,
  `what_i_wish_to_improve` text NOT NULL,
  `interests_outside_of_school` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `current_study`
--

INSERT INTO `current_study` (`id`, `school_id`, `english_level_id`, `last_finished_grade_id`, `favourite_school_subjects`, `personal_challenge_and_solution_for_it`, `plans_after_graduation`, `personal_qualities_i_want_to_improve`, `how_i_have_fun_in_my_free_time`, `what_i_wish_to_improve`, `interests_outside_of_school`) VALUES
(1, 3, 2, 2, 'Math', 'I did a lot of stuff ', 'I want to be rich', 'improve my driving ', 'I play games', 'I want to improve some stuff', 'None - just school'),
(2, 1, 6, 3, 'Math', 'I did a lot of stuff ', 'I want to be VERY rich', 'improve my driving and swimming', 'I watch movies', 'I want to improve some very important stuff', 'Play piano'),
(3, 1, 3, 2, 'Math and Music', 'I did a lot of stuff ', 'I want to be VERY rich', 'improve my driving and swimming', 'I watch movies', 'I want to improve some very important stuff', 'Play piano and guitar');

-- --------------------------------------------------------

--
-- Table structure for table `english_level`
--

CREATE TABLE `english_level` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `english_level`
--

INSERT INTO `english_level` (`id`, `name`) VALUES
(1, 'A1'),
(2, 'A2'),
(3, 'B1'),
(4, 'B2'),
(5, 'C1'),
(6, 'C2');

-- --------------------------------------------------------

--
-- Table structure for table `fav_sport`
--

CREATE TABLE `fav_sport` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fav_sport`
--

INSERT INTO `fav_sport` (`id`, `name`) VALUES
(1, 'football'),
(2, 'swimming'),
(3, 'basketball'),
(4, 'armwrestling');

-- --------------------------------------------------------

--
-- Table structure for table `hours_per_week_for_project`
--

CREATE TABLE `hours_per_week_for_project` (
  `id` int(11) NOT NULL,
  `hours` tinyint(4) NOT NULL COMMENT '1-4, 5 = more than 4'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hours_per_week_for_project`
--

INSERT INTO `hours_per_week_for_project` (`id`, `hours`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `last_finished_grade`
--

CREATE TABLE `last_finished_grade` (
  `id` int(11) NOT NULL,
  `grade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `last_finished_grade`
--

INSERT INTO `last_finished_grade` (`id`, `grade`) VALUES
(1, 9),
(2, 10),
(3, 11);

-- --------------------------------------------------------

--
-- Table structure for table `project_to_work_with_a_mentro`
--

CREATE TABLE `project_to_work_with_a_mentro` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `project_to_work_with_a_mentro`
--

INSERT INTO `project_to_work_with_a_mentro` (`id`, `name`) VALUES
(1, 'social project'),
(2, 'bussiness project'),
(3, 'project for personal development');

-- --------------------------------------------------------

--
-- Table structure for table `reason_to_apply`
--

CREATE TABLE `reason_to_apply` (
  `id` int(11) NOT NULL,
  `reason_description` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reason_to_apply`
--

INSERT INTO `reason_to_apply` (`id`, `reason_description`) VALUES
(1, 'university/proffession'),
(2, 'new skills'),
(3, 'develop personal project'),
(4, 'find new friends'),
(5, 'advice from a teacher'),
(6, 'advice from a parent');

-- --------------------------------------------------------

--
-- Table structure for table `school`
--

CREATE TABLE `school` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `school`
--

INSERT INTO `school` (`id`, `name`) VALUES
(1, 'Mehano'),
(2, 'Ikonomika'),
(3, 'Himiq');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  `current_study_id` int(11) NOT NULL,
  `able_program_id` int(11) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `city_id`, `current_study_id`, `able_program_id`, `first_name`, `last_name`, `email`, `phone`) VALUES
(1, 1, 1, 2, 'Ivan', 'Petrov', 'ivan@abv.bg', '123'),
(2, 2, 3, 3, 'Georgi', 'Georgiev', 'gosho@abv.bg', '234'),
(3, 3, 3, 1, 'Peter', 'Petrov', 'pesho12@abv.bg', '222');

-- --------------------------------------------------------

--
-- Table structure for table `student_area_to_develop`
--

CREATE TABLE `student_area_to_develop` (
  `student_id` int(11) NOT NULL,
  `area_i_wish_to_develop_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_area_to_develop`
--

INSERT INTO `student_area_to_develop` (`student_id`, `area_i_wish_to_develop_id`) VALUES
(3, 1),
(2, 3),
(1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `student_fav_sport`
--

CREATE TABLE `student_fav_sport` (
  `student_id` int(11) NOT NULL,
  `fav_sport_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_fav_sport`
--

INSERT INTO `student_fav_sport` (`student_id`, `fav_sport_id`) VALUES
(2, 1),
(3, 2),
(1, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `able_program`
--
ALTER TABLE `able_program`
  ADD PRIMARY KEY (`id`),
  ADD KEY `hours_per_week_for_project_id` (`hours_per_week_for_project_id`,`reason_to_apply_id`,`project_to_work_with_a_mentor_id`),
  ADD KEY `reason_to_apply_id` (`reason_to_apply_id`),
  ADD KEY `project_to_work_with_a_mentor_id` (`project_to_work_with_a_mentor_id`),
  ADD KEY `convenient_participation_format_id` (`convenient_participation_format_id`);

--
-- Indexes for table `area_i_wish_to_develop`
--
ALTER TABLE `area_i_wish_to_develop`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `convenient_participation_format`
--
ALTER TABLE `convenient_participation_format`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `current_study`
--
ALTER TABLE `current_study`
  ADD PRIMARY KEY (`id`),
  ADD KEY `school_id` (`school_id`,`english_level_id`),
  ADD KEY `english_level_id` (`english_level_id`),
  ADD KEY `last_finished_grade_id` (`last_finished_grade_id`);

--
-- Indexes for table `english_level`
--
ALTER TABLE `english_level`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fav_sport`
--
ALTER TABLE `fav_sport`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hours_per_week_for_project`
--
ALTER TABLE `hours_per_week_for_project`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `last_finished_grade`
--
ALTER TABLE `last_finished_grade`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `project_to_work_with_a_mentro`
--
ALTER TABLE `project_to_work_with_a_mentro`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reason_to_apply`
--
ALTER TABLE `reason_to_apply`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `school`
--
ALTER TABLE `school`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `city_id` (`city_id`),
  ADD KEY `current_study_id` (`current_study_id`),
  ADD KEY `able_program_id` (`able_program_id`);

--
-- Indexes for table `student_area_to_develop`
--
ALTER TABLE `student_area_to_develop`
  ADD PRIMARY KEY (`student_id`,`area_i_wish_to_develop_id`),
  ADD KEY `area_i_wish_to_develop_id` (`area_i_wish_to_develop_id`);

--
-- Indexes for table `student_fav_sport`
--
ALTER TABLE `student_fav_sport`
  ADD PRIMARY KEY (`student_id`,`fav_sport_id`),
  ADD KEY `fav_sport_id` (`fav_sport_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `able_program`
--
ALTER TABLE `able_program`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `area_i_wish_to_develop`
--
ALTER TABLE `area_i_wish_to_develop`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `convenient_participation_format`
--
ALTER TABLE `convenient_participation_format`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `current_study`
--
ALTER TABLE `current_study`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `english_level`
--
ALTER TABLE `english_level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `fav_sport`
--
ALTER TABLE `fav_sport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `hours_per_week_for_project`
--
ALTER TABLE `hours_per_week_for_project`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `last_finished_grade`
--
ALTER TABLE `last_finished_grade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `project_to_work_with_a_mentro`
--
ALTER TABLE `project_to_work_with_a_mentro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `reason_to_apply`
--
ALTER TABLE `reason_to_apply`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `school`
--
ALTER TABLE `school`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `able_program`
--
ALTER TABLE `able_program`
  ADD CONSTRAINT `able_program_ibfk_1` FOREIGN KEY (`hours_per_week_for_project_id`) REFERENCES `hours_per_week_for_project` (`id`),
  ADD CONSTRAINT `able_program_ibfk_2` FOREIGN KEY (`reason_to_apply_id`) REFERENCES `reason_to_apply` (`id`),
  ADD CONSTRAINT `able_program_ibfk_3` FOREIGN KEY (`project_to_work_with_a_mentor_id`) REFERENCES `project_to_work_with_a_mentro` (`id`),
  ADD CONSTRAINT `able_program_ibfk_4` FOREIGN KEY (`convenient_participation_format_id`) REFERENCES `convenient_participation_format` (`id`);

--
-- Constraints for table `current_study`
--
ALTER TABLE `current_study`
  ADD CONSTRAINT `current_study_ibfk_1` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`),
  ADD CONSTRAINT `current_study_ibfk_2` FOREIGN KEY (`english_level_id`) REFERENCES `english_level` (`id`),
  ADD CONSTRAINT `current_study_ibfk_3` FOREIGN KEY (`last_finished_grade_id`) REFERENCES `last_finished_grade` (`id`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_10` FOREIGN KEY (`able_program_id`) REFERENCES `able_program` (`id`),
  ADD CONSTRAINT `student_ibfk_8` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  ADD CONSTRAINT `student_ibfk_9` FOREIGN KEY (`current_study_id`) REFERENCES `current_study` (`id`);

--
-- Constraints for table `student_area_to_develop`
--
ALTER TABLE `student_area_to_develop`
  ADD CONSTRAINT `student_area_to_develop_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  ADD CONSTRAINT `student_area_to_develop_ibfk_2` FOREIGN KEY (`area_i_wish_to_develop_id`) REFERENCES `area_i_wish_to_develop` (`id`);

--
-- Constraints for table `student_fav_sport`
--
ALTER TABLE `student_fav_sport`
  ADD CONSTRAINT `student_fav_sport_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  ADD CONSTRAINT `student_fav_sport_ibfk_2` FOREIGN KEY (`fav_sport_id`) REFERENCES `fav_sport` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
