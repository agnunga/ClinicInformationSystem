-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2017 at 03:37 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cis`
--

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employeeno` varchar(15) NOT NULL,
  `nationalid` int(11) NOT NULL,
  `dateemployed` varchar(30) NOT NULL,
  `salary` varchar(11) NOT NULL,
  `password` varchar(50) NOT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employeeno`, `nationalid`, `dateemployed`, `salary`, `password`, `title`) VALUES
('.54.54', 8736476, '354354354', '3434.', '8736476', 'd'),
('343543', 3543543, '343543', '354354', '3543543', 'd'),
('4', 24, '54', '54', '24', '-Select Role-'),
('45420542', 35434, '45425', '4254205', '35434', 'd'),
('54', 354364, '25', '45', '354364', '-Select Role-'),
('54543', 6986, '435405', '45356543', '6986', 'd'),
('874386742', 4653453, '54257', '2', '4653453', 'l'),
('A001', 31254883, '27/05/2014', '1286000', '31254883', 'a'),
('D001', 30123456, '12/08/2016', '630000', '30123456', 'd'),
('D3333', 88888881, '12/08/2016', '125000', '88888881', 'd'),
('D771', 11111119, '12/08/2016', '2548000', '11111119', 'd'),
('L001', 21458796, '12/12/2015', '365000', '21458796', 'l'),
('N001', 54365438, '12/08/2016', '125000', '54365438', 'n'),
('R001', 12345678, '12/12/2014', '40000', '12345678', 'r');

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE `patients` (
  `id` int(11) NOT NULL,
  `patientid` varchar(11) NOT NULL,
  `nationalid` int(11) NOT NULL,
  `checkin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `checkout` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `addedby` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`id`, `patientid`, `nationalid`, `checkin`, `checkout`, `addedby`) VALUES
(1, 'P001', 12548563, '2017-03-07 05:38:34', '0000-00-00 00:00:00', 'RF4525'),
(2, 'P002', 14525878, '2017-03-07 05:40:25', '0000-00-00 00:00:00', 'RF4525'),
(5, 'P0015', 3546843, '2017-03-27 19:00:19', '0000-00-00 00:00:00', 'RF4525'),
(7, '84676', 6368735, '2017-03-27 19:53:37', '0000-00-00 00:00:00', 'RF4525'),
(8, '24', 354356, '2017-03-27 19:55:58', '0000-00-00 00:00:00', 'RF4525'),
(9, '676876876', 5436842, '2017-03-28 09:22:58', '0000-00-00 00:00:00', 'RF4525');

-- --------------------------------------------------------

--
-- Table structure for table `persons`
--

CREATE TABLE `persons` (
  `nationalid` varchar(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `dob` varchar(15) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `sex` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `persons`
--

INSERT INTO `persons` (`nationalid`, `name`, `dob`, `phone`, `sex`) VALUES
('11111119', 'PPPPPPPPPPPPPP', '12/12/2000', '0788888', 'female'),
('12345678', 'Marry Njuguna Mwangi', '12/12/2000', '0712548745', 'female'),
('12548563', 'Karume Kiamba', '12/12/1990', '+254785253652', 'female'),
('142', 'jhkjknkj', '12/25/2366', '84687468', 'male'),
('14525878', 'Dan Mboya', '15/25/4058', '+2563569856', 'female'),
('21458796', 'Karanja Njuguna', '14/02/2000', '+254732569856', 'male'),
('24', '554', '4', '5', 'male'),
('30123456', 'Benerd Nyaoke', '31/12/1993', '+254706325698', 'male'),
('31254883', 'Godfey Agunga', '27/07/1994', '0706091094', 'male'),
('35434', '34245255', '5425425', '436453', 'male'),
('3543543', '435434', '4354354', '43541354', 'male'),
('354356', 'FAIMA MWAMI', '54254', '43543564', 'male'),
('354364', 'bjhbj', '5', '3864684', 'male'),
('3546843', 'Oloo Palase', '12/12/2000', '364368738', 'male'),
('4653453', 'hjmghmjhg', '254385742', '43545', 'female'),
('475896', 'Paul', '45/85/7589', '0712121212', 'female'),
('54365438', 'gjhgmjg', '12/12/2000', '6843435', 'female'),
('5436842', 'DAN SIMIYU', '8436876', '685436483', 'female'),
('6368735', 'nsuuta', '87768768', '643646', 'male'),
('6986', 'khkjhk,', '542545', '687687', 'male'),
('76374386', '48648', '5838787', '368436436', 'male'),
('8736476', '4687463', '643854.354', '47343684', 'male'),
('88888881', 'LLLLLLLLLLLLLLl', '12/12/2000', '465465', 'female');

-- --------------------------------------------------------

--
-- Table structure for table `receptionists`
--

CREATE TABLE `receptionists` (
  `employeeno` varchar(15) NOT NULL,
  `assignment` varchar(255) NOT NULL,
  `dateassigned` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `receptionists`
--

INSERT INTO `receptionists` (`employeeno`, `assignment`, `dateassigned`) VALUES
('R001', 'Front Office', '2017-03-01 03:05:29');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employeeno`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `persons`
--
ALTER TABLE `persons`
  ADD PRIMARY KEY (`nationalid`);

--
-- Indexes for table `receptionists`
--
ALTER TABLE `receptionists`
  ADD PRIMARY KEY (`employeeno`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `patients`
--
ALTER TABLE `patients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
