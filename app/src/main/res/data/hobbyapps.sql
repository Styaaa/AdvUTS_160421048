-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 20, 2024 at 05:09 PM
-- Server version: 10.4.24-MariaDB-log
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hobbyapps`
--

-- --------------------------------------------------------

--
-- Table structure for table `berita`
--

CREATE TABLE `berita` (
  `id` int(11) NOT NULL,
  `judul` varchar(255) NOT NULL,
  `deskripsi` varchar(255) NOT NULL,
  `gambar` varchar(255) NOT NULL,
  `kreator` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `berita`
--

INSERT INTO `berita` (`id`, `judul`, `deskripsi`, `gambar`, `kreator`) VALUES
(3, 'Basket', 'Bermain basket adalah kegiatan yang melibatkan berbagai keterampilan fisik dan mental. Dalam basket, pemain berusaha untuk mencetak poin dengan melempar bola ke dalam keranjang lawan sambil mencegah lawan mencetak poin. Ini melibatkan dribbling, passing, ', 'https://loremflickr.com/320/240/basket?lock=4', '@Stya'),
(4, 'Mendaki', 'Mendaki adalah kegiatan penjelajahan alam yang melibatkan perjalanan ke pegunungan atau daerah alam lainnya dengan berjalan kaki. Ini melibatkan menaklukkan medan yang beragam, termasuk tanjakan curam, lembah, dan sungai.', 'https://loremflickr.com/320/240/mountain?lock=4', '@alxx');

-- --------------------------------------------------------

--
-- Table structure for table `paragraf`
--

CREATE TABLE `paragraf` (
  `id` int(11) NOT NULL,
  `subjudul` varchar(255) NOT NULL,
  `konten` varchar(255) NOT NULL,
  `berita_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `paragraf`
--

INSERT INTO `paragraf` (`id`, `subjudul`, `konten`, `berita_id`) VALUES
(1, 'satu', 'ini paragraf satuuuuuu', 3),
(2, 'dua', 'ini paragraff duuuaaaa', 3),
(3, 'tgigaa', 'inii paragraff satuu di beritaa duaaa', 4),
(4, 'empatta', 'Ini paragraff duaa di beritaa keduaaaa', 4);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nama_depan` varchar(255) NOT NULL,
  `nama_belakang` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `nama_depan`, `nama_belakang`, `email`) VALUES
(6, 'a', 'a', 'a', 'b', 'a');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `berita`
--
ALTER TABLE `berita`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `paragraf`
--
ALTER TABLE `paragraf`
  ADD PRIMARY KEY (`id`),
  ADD KEY `berita_id` (`berita_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `berita`
--
ALTER TABLE `berita`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `paragraf`
--
ALTER TABLE `paragraf`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `paragraf`
--
ALTER TABLE `paragraf`
  ADD CONSTRAINT `berita_id` FOREIGN KEY (`berita_id`) REFERENCES `berita` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
