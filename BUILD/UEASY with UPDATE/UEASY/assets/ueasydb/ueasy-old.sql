-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 24, 2015 at 01:52 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ueasy`
--

-- --------------------------------------------------------

--
-- Table structure for table `building`
--

CREATE TABLE IF NOT EXISTS `building` (
`b_id` int(11) NOT NULL,
  `c_id` int(11) NOT NULL,
  `b_name` int(11) NOT NULL,
  `b_desc` int(11) NOT NULL,
  `b_pic` int(11) NOT NULL,
  `b_latitude` int(11) NOT NULL,
  `b_longitude` int(11) NOT NULL,
  `b_catName` int(11) NOT NULL,
  `sync` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `campus`
--

CREATE TABLE IF NOT EXISTS `campus` (
  `c_id` int(11) NOT NULL,
  `c_name` int(11) NOT NULL,
  `c_addr` int(11) NOT NULL,
  `c_desc` int(11) NOT NULL,
  `sync` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `classroom`
--

CREATE TABLE IF NOT EXISTS `classroom` (
`cr_id` int(11) NOT NULL,
  `c_id` int(11) NOT NULL,
  `b_id` int(11) NOT NULL,
  `cr_name` text NOT NULL,
  `cr_pic` text NOT NULL,
  `cr_sched` text NOT NULL,
  `cr_desc` text NOT NULL,
  `cr_latitude` text NOT NULL,
  `cr_longitude` text NOT NULL,
  `cr_catName` text NOT NULL,
  `cr_blevelNum` int(11) NOT NULL,
  `sync` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classroom`
--

INSERT INTO `classroom` (`cr_id`, `c_id`, `b_id`, `cr_name`, `cr_pic`, `cr_sched`, `cr_desc`, `cr_latitude`, `cr_longitude`, `cr_catName`, `cr_blevelNum`, `sync`) VALUES
(5, 1, 1, 'LB401', 'Pic1', '9:00 AM - 10:30 PM', 'Lecture Room', '10.3232', '10.323232', 'Classroom', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `otheramenities`
--

CREATE TABLE IF NOT EXISTS `otheramenities` (
`oa_id` int(11) NOT NULL,
  `c_id` int(11) NOT NULL,
  `b_id` int(11) NOT NULL,
  `oa_name` text NOT NULL,
  `oa_desc` text NOT NULL,
  `oa_pic` text NOT NULL,
  `oa_type` text NOT NULL,
  `oa_BLevelNum` int(11) NOT NULL,
  `oa_latitude` text NOT NULL,
  `oa_longitude` text NOT NULL,
  `oa_catName` int(11) NOT NULL,
  `sync` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `roomutility`
--

CREATE TABLE IF NOT EXISTS `roomutility` (
`ru_id` int(11) NOT NULL,
  `cr_id` int(11) NOT NULL,
  `ru_day` int(11) NOT NULL,
  `ru_time` time NOT NULL,
  `ru_teacher_name` text NOT NULL,
  `ru_subj_code` text NOT NULL,
  `sync` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `building`
--
ALTER TABLE `building`
 ADD PRIMARY KEY (`b_id`);

--
-- Indexes for table `campus`
--
ALTER TABLE `campus`
 ADD PRIMARY KEY (`c_id`);

--
-- Indexes for table `classroom`
--
ALTER TABLE `classroom`
 ADD PRIMARY KEY (`cr_id`);

--
-- Indexes for table `otheramenities`
--
ALTER TABLE `otheramenities`
 ADD PRIMARY KEY (`oa_id`);

--
-- Indexes for table `roomutility`
--
ALTER TABLE `roomutility`
 ADD PRIMARY KEY (`ru_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `building`
--
ALTER TABLE `building`
MODIFY `b_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `classroom`
--
ALTER TABLE `classroom`
MODIFY `cr_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `otheramenities`
--
ALTER TABLE `otheramenities`
MODIFY `oa_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `roomutility`
--
ALTER TABLE `roomutility`
MODIFY `ru_id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
