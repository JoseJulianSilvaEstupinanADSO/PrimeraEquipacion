-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 02, 2024 at 12:30 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_proyecto`
--

-- --------------------------------------------------------

--
-- Table structure for table `factura`
--

CREATE TABLE `factura` (
  `id_factura` int(11) NOT NULL,
  `fecha_facturacion` date DEFAULT NULL,
  `id_venta` int(11) DEFAULT NULL,
  `doc_cliente` varchar(10) DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `factura`
--

INSERT INTO `factura` (`id_factura`, `fecha_facturacion`, `id_venta`, `doc_cliente`, `total`) VALUES
(10, '2024-07-31', 10, '4109382765', 45000),
(11, '2024-07-31', 11, '6738491025', 133000),
(12, '2024-07-31', 12, '4109382765', 160000),
(13, '2024-08-01', 13, '1005231647', 600000),
(14, '2024-08-01', 14, '1005231647', 840000),
(15, '2024-08-01', 15, '4819273650', 500000);

-- --------------------------------------------------------

--
-- Table structure for table `factura_producto`
--

CREATE TABLE `factura_producto` (
  `id_factura_producto` int(11) NOT NULL,
  `id_factura` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `factura_producto`
--

INSERT INTO `factura_producto` (`id_factura_producto`, `id_factura`, `id_producto`, `cantidad`, `precio`) VALUES
(17, 10, 27, 1, 45000),
(18, 11, 31, 1, 13000),
(19, 11, 21, 2, 120000),
(20, 12, 23, 2, 160000),
(21, 13, 20, 5, 600000),
(22, 14, 20, 7, 840000),
(23, 15, 23, 4, 320000),
(24, 15, 25, 2, 30000),
(25, 15, 32, 1, 150000);

-- --------------------------------------------------------

--
-- Table structure for table `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `precio` double DEFAULT NULL,
  `estado` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre`, `precio`, `estado`) VALUES
(20, 'Pantaloneta PE Tottenham Hotspur 2011', 120000, 1),
(21, 'Pantaloneta SE Manchester United 2019', 65000, 1),
(22, 'Medias TE Juventus 2012', 26000, 1),
(23, 'Camisa PE Bayern Munich 2018', 80000, 1),
(24, 'Pantaloneta SE Chelsea 2020', 45000, 1),
(25, 'Medias TE Paris Saint-Germain 2015', 15000, 1),
(26, 'Camisa SE Liverpool 2021', 90000, 1),
(27, 'Pantaloneta TE AC Milan 2014', 45000, 1),
(28, 'Medias PE Arsenal 2017', 10000, 1),
(29, 'Camisa SE Inter Milan 2013', 100000, 1),
(30, 'Pantaloneta PE Tottenham Hotspur 2011', 110000, 1),
(31, 'Medias TE Atletico Madrid 2022', 13000, 1),
(32, 'Camisa PE Real Madrid 2014', 150000, 1),
(33, 'Camisa PE Borussia Dortmund 2023', 980000, 1),
(34, 'Camisa PE Borussia Dortmund 2023', 980000, 1),
(35, 'Camisa PE Borussia Dortmund 2023', 980000, 1),
(36, 'Pantaloneta SE Ajax 2010', 46000, 1),
(37, 'Pantaloneta PE Tottenham Hotspur 2011', 80000, 1),
(39, 'Medias PE Real Madrid 2016', 26000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `producto_desc`
--

CREATE TABLE `producto_desc` (
  `id_producto` int(11) NOT NULL,
  `talla` varchar(4) NOT NULL,
  `stock` int(11) DEFAULT NULL,
  `tela` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `producto_desc`
--

INSERT INTO `producto_desc` (`id_producto`, `talla`, `stock`, `tela`) VALUES
(20, 'M', 40, 'Microfibra'),
(21, 'X', 20, 'Poliester'),
(22, 'S', 10, 'Malla '),
(23, 'X', 16, 'Nylon'),
(24, 'M', 7, 'Nylon'),
(25, 'L', 10, 'Nylon'),
(26, 'M', 10, 'Nylon'),
(27, 'S', 12, 'Spandex '),
(28, 'X', 15, 'Malla '),
(29, 'XL', 6, 'Microfibra'),
(30, 'L', 15, 'Nylon'),
(31, 'M', 14, 'Malla '),
(32, 'S', 30, 'Nylon'),
(35, 'X', 14, 'Nylon'),
(36, 'L', 11, 'Spandex '),
(37, 'XL', 10, 'Microfibra'),
(39, 'L', 12, 'algondo');

-- --------------------------------------------------------

--
-- Table structure for table `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rol`
--

INSERT INTO `rol` (`id_rol`, `nombre`) VALUES
(1, 'admin'),
(2, 'ventas'),
(3, 'inventario'),
(4, 'cliente');

-- --------------------------------------------------------

--
-- Table structure for table `talla`
--

CREATE TABLE `talla` (
  `talla` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `talla`
--

INSERT INTO `talla` (`talla`) VALUES
('L'),
('M'),
('S'),
('X'),
('XL');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `usuario` varchar(30) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `id_rol` int(11) DEFAULT NULL,
  `documento` varchar(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `edad` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `usuario`, `contrasena`, `id_rol`, `documento`, `nombre`, `telefono`, `direccion`, `email`, `edad`) VALUES
(1, 'admin', '$2a$10$6tCGFkrSOtGzqRX64bGWvuJ24ByB14F9eA8/fm2ZzmKmCMJb0Atai', 1, '1005231647', 'Jose Julian', '3164579824', 'hola1234', 'hola@123.com', 22),
(5, NULL, NULL, 4, '3216549874', 'Pedro  Picapiedra', '3126459785', NULL, NULL, NULL),
(15, NULL, NULL, 4, '4567891234', 'Felipe Silva', '3164587921', NULL, NULL, NULL),
(21, NULL, NULL, 4, '7481293647', 'Ainhoa Cruz', '3211465487', NULL, NULL, NULL),
(22, NULL, NULL, 4, '8674913025', 'John Smith', '5203941867', NULL, NULL, NULL),
(23, NULL, NULL, 4, '4109382765', 'Emma Johnson', '7392805146', NULL, NULL, NULL),
(24, NULL, NULL, 4, '4819273650', 'Michael Brown', '5920361748', NULL, NULL, NULL),
(25, NULL, NULL, 4, '6738491025', 'James Jones', '3847152906', NULL, NULL, NULL),
(26, NULL, NULL, 4, '9501738264', 'Sophia Garcia', '2604891753', NULL, NULL, NULL),
(27, NULL, NULL, 4, '1948673520', 'William Miller', '5372806419', NULL, NULL, NULL),
(28, NULL, NULL, 4, '7290514386', 'Isabella Wilson', '8054169273', NULL, NULL, NULL),
(29, 'ventas_01', '$2a$10$aewsmoYUZ485PVFutIJ63unfAMgsQ5nbQI3YVefprm.DqGx04mLnO', 2, '6544987123', 'Pepe Grillo', '7894561231', '13774 Antwan Courts', 'batz.ernestine@towne.com', 28),
(30, 'inventario_01', '$2a$10$CBV1uF6AtWG8ryW0di0W.ec8GHC0a64ZQPn1EfxiE6RCNB2qaj8eW', 3, '6497584612', 'Elena Exposito', '2134567894', '1600 Amphitheatre Parkway', 'test@example.us', 31),
(32, NULL, NULL, 4, '1012345678', 'Omar Torres', '3164875614', NULL, NULL, NULL),
(34, NULL, NULL, 4, '1054612341', 'Carlos Alberto', '4651234567', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `venta`
--

CREATE TABLE `venta` (
  `id_venta` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `venta`
--

INSERT INTO `venta` (`id_venta`, `id_usuario`) VALUES
(10, 1),
(11, 1),
(12, 1),
(13, 1),
(14, 1),
(15, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id_factura`),
  ADD KEY `id_venta` (`id_venta`);

--
-- Indexes for table `factura_producto`
--
ALTER TABLE `factura_producto`
  ADD PRIMARY KEY (`id_factura_producto`),
  ADD KEY `id_factura` (`id_factura`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indexes for table `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indexes for table `producto_desc`
--
ALTER TABLE `producto_desc`
  ADD PRIMARY KEY (`id_producto`,`talla`),
  ADD KEY `talla` (`talla`);

--
-- Indexes for table `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indexes for table `talla`
--
ALTER TABLE `talla`
  ADD PRIMARY KEY (`talla`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `unique_documento` (`documento`),
  ADD KEY `id_rol` (`id_rol`);

--
-- Indexes for table `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `factura`
--
ALTER TABLE `factura`
  MODIFY `id_factura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `factura_producto`
--
ALTER TABLE `factura_producto`
  MODIFY `id_factura_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `venta`
--
ALTER TABLE `venta`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_2` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`);

--
-- Constraints for table `factura_producto`
--
ALTER TABLE `factura_producto`
  ADD CONSTRAINT `factura_producto_ibfk_1` FOREIGN KEY (`id_factura`) REFERENCES `factura` (`id_factura`),
  ADD CONSTRAINT `factura_producto_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`);

--
-- Constraints for table `producto_desc`
--
ALTER TABLE `producto_desc`
  ADD CONSTRAINT `producto_desc_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  ADD CONSTRAINT `producto_desc_ibfk_2` FOREIGN KEY (`talla`) REFERENCES `talla` (`talla`);

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`);

--
-- Constraints for table `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
