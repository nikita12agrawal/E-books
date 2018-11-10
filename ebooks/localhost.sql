-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 10, 2018 at 12:31 PM
-- Server version: 5.5.24-log
-- PHP Version: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `customebooks`
--
CREATE DATABASE `customebooks` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `customebooks`;

-- --------------------------------------------------------

--
-- Table structure for table `bookinfo`
--

CREATE TABLE IF NOT EXISTS `bookinfo` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `bookName` text NOT NULL,
  `price` float NOT NULL,
  `totalPages` int(11) NOT NULL,
  `publisherId` int(11) NOT NULL,
  `description` text,
  `book` longblob NOT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `bookinfo`
--

<br />
<font size='1'><table class='xdebug-error xe-fatal-error' dir='ltr' border='1' cellspacing='0' cellpadding='1'>
<tr><th align='left' bgcolor='#f57900' colspan="5"><span style='background-color: #cc0000; color: #fce94f; font-size: x-large;'>( ! )</span> Fatal error: Allowed memory size of 134217728 bytes exhausted (tried to allocate 68542205 bytes) in C:\wamp\apps\phpmyadmin3.5.1\libraries\export\sql.php on line <i>1218</i></th></tr>
<tr><th align='left' bgcolor='#e9b96e' colspan='5'>Call Stack</th></tr>
<tr><th align='center' bgcolor='#eeeeec'>#</th><th align='left' bgcolor='#eeeeec'>Time</th><th align='left' bgcolor='#eeeeec'>Memory</th><th align='left' bgcolor='#eeeeec'>Function</th><th align='left' bgcolor='#eeeeec'>Location</th></tr>
<tr><td bgcolor='#eeeeec' align='center'>1</td><td bgcolor='#eeeeec' align='center'>0.0269</td><td bgcolor='#eeeeec' align='right'>267504</td><td bgcolor='#eeeeec'>{main}(  )</td><td title='C:\wamp\apps\phpmyadmin3.5.1\export.php' bgcolor='#eeeeec'>..\export.php<b>:</b>0</td></tr>
<tr><td bgcolor='#eeeeec' align='center'>2</td><td bgcolor='#eeeeec' align='center'>1.4212</td><td bgcolor='#eeeeec' align='right'>3937000</td><td bgcolor='#eeeeec'>PMA_exportData(  )</td><td title='C:\wamp\apps\phpmyadmin3.5.1\export.php' bgcolor='#eeeeec'>..\export.php<b>:</b>467</td></tr>
</table></font>
