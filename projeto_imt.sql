-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 01-Ago-2023 às 17:49
-- Versão do servidor: 10.4.28-MariaDB
-- versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `projeto_imt`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `customer`
--

CREATE TABLE `customer` (
  `driver_license_number` int(8) NOT NULL,
  `license_type` char(1) NOT NULL,
  `start_date` date NOT NULL,
  `expiry_date` date NOT NULL,
  `nif` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `customer`
--

INSERT INTO `customer` (`driver_license_number`, `license_type`, `start_date`, `expiry_date`, `nif`) VALUES
(10987654, 'C', '2023-01-27', '2026-01-27', 109876543),
(12345678, 'A', '2022-07-01', '2025-07-01', 123456789),
(23198765, 'B', '2022-09-10', '2025-09-10', 231987654),
(32109876, 'A', '2020-09-30', '2023-09-30', 321098765),
(43210987, 'A', '2023-11-03', '2026-11-03', 432109876),
(45678901, 'B', '2023-05-01', '2026-05-01', 456789012),
(45678912, 'C', '2023-03-20', '2026-03-20', 456789123),
(76543210, 'C', '2022-08-14', '2025-08-14', 765432109),
(78912345, 'A', '2020-12-05', '2023-12-05', 789123456),
(87654321, 'B', '2021-10-15', '2024-10-15', 987654321),
(90123456, 'B', '2022-04-18', '2025-04-18', 901234567),
(98701234, 'B', '2021-12-12', '2024-12-12', 987012345),
(98712345, 'A', '2021-11-18', '2024-11-18', 987123456),
(98765432, 'C', '2023-06-22', '2026-06-22', 876543219);

-- --------------------------------------------------------

--
-- Estrutura da tabela `employee`
--

CREATE TABLE `employee` (
  `nif` int(9) NOT NULL,
  `access_level` enum('0','1','2') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `insurance`
--

CREATE TABLE `insurance` (
  `policy` int(13) NOT NULL,
  `expiry_date` date NOT NULL,
  `company` varchar(45) NOT NULL,
  `start_date` date NOT NULL,
  `extra_category` int(1) NOT NULL,
  `plate` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `insurance`
--

INSERT INTO `insurance` (`policy`, `expiry_date`, `company`, `start_date`, `extra_category`, `plate`) VALUES
(2147483638, '2025-04-25', 'PQR Insurance', '2023-02-01', 1, 'MN-90-OP'),
(2147483639, '2025-11-10', 'LMN Insurance', '2023-03-15', 3, 'IJ-78-KL'),
(2147483640, '2026-04-01', 'XYZ Insurance', '2023-03-25', 2, 'EF-56-GH'),
(2147483641, '2025-08-18', 'ABC Insurance', '2022-12-20', 1, 'AB-34-CD'),
(2147483642, '2025-10-27', 'PQR Insurance', '2023-03-10', 3, 'UV-12-XY'),
(2147483643, '2026-01-22', 'XYZ Insurance', '2023-02-20', 2, 'QR-90-ST'),
(2147483644, '2025-07-05', 'LMN Insurance', '2023-03-05', 1, 'MN-78-OP'),
(2147483645, '2026-02-28', 'PQR Insurance', '2022-12-10', 3, 'IJ-56-KL'),
(2147483646, '2025-09-20', 'XYZ Insurance', '2023-01-15', 2, 'EF-34-GH'),
(2147483647, '2025-06-15', 'ABC Insurance', '2023-01-01', 1, 'AB-12-CD');

-- --------------------------------------------------------

--
-- Estrutura da tabela `person`
--

CREATE TABLE `person` (
  `nif` int(9) NOT NULL,
  `name` varchar(45) NOT NULL,
  `address` varchar(60) NOT NULL,
  `b_date` date NOT NULL,
  `password` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `person`
--

INSERT INTO `person` (`nif`, `name`, `address`, `b_date`, `password`) VALUES
(109876543, 'Ava Hernandez', '777 Maple Dr', '1986-09-27', 'qwertyui'),
(123456789, 'John Doe', '123 Main St', '1990-05-15', 'p@ssw0rd'),
(231987654, 'Robert Williams', '654 Maple Dr', '1992-09-30', 'random12'),
(234567890, 'Mia Moore', '222 Maple Dr', '2000-04-17', 'test123'),
(270666543, 'Osmar Mafarrico', 'Rua Sem Nome, Porta Inifinta', '2003-07-09', '123abc'),
(311393980, 'Mickey Mouse', 'Rua das Maravilhas, Sem FIm', '1994-07-13', 'abc123'),
(321098765, 'Sophia Martin', '555 Elm St', '1991-10-08', 'hello123'),
(432109876, 'Noah Lopez', '888 Pine Rd', '1996-11-03', 'passw0rd'),
(456789012, 'Olivia Davis', '333 Maple Dr', '1993-12-01', 'pass1234'),
(456789123, 'Michael Johnson', '789 Elm St', '1995-03-10', 'abc12345'),
(567890123, 'Abigail Campbell', '444 Elm St', '2002-12-10', 'securepa'),
(765432108, 'Daniel Rivera', '555 Oak Ave', '2003-03-08', 'qwerty12'),
(765432109, 'James Anderson', '444 Pine Rd', '1989-06-22', 'mysecret'),
(789123456, 'Sarah Lee', '321 Pine Rd', '1985-11-25', 'testpass'),
(876543210, 'Ethan Edwards', '111 Oak Ave', '1999-08-30', 'iloveyou'),
(876543219, 'Emily Johnson', '111 Elm St', '1998-07-05', 'password'),
(890123456, 'Alexander Morgan', '333 Pine Rd', '2001-07-25', 'abc123'),
(901234567, 'Liam Garcia', '666 Oak Ave', '1987-02-14', 'abcxyz12'),
(987012345, 'Isabella Martinez', '999 Elm St', '1997-01-12', 'mypasswo'),
(987123456, 'David Wilson', '222 Oak Ave', '1994-04-18', 'secure12'),
(987654321, 'Jane Smith', '456 Oak Ave', '1988-08-20', 'qwerty12');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ticket`
--

CREATE TABLE `ticket` (
  `driver_license_number` int(9) NOT NULL,
  `plate` char(8) NOT NULL,
  `date` date NOT NULL,
  `expiry_date` date NOT NULL,
  `value` double NOT NULL,
  `reason` int(1) NOT NULL,
  `paid` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `ticket`
--

INSERT INTO `ticket` (`driver_license_number`, `plate`, `date`, `expiry_date`, `value`, `reason`, `paid`) VALUES
(12345678, 'AB-12-CD', '2023-01-05', '2023-03-05', 250, 2, 0),
(87654321, 'EF-34-GH', '2023-01-12', '2023-04-12', 180.5, 3, 0),
(45678912, 'IJ-56-KL', '2023-02-20', '2023-05-20', 300.75, 1, 0),
(78912345, 'MN-78-OP', '2023-03-15', '2023-05-15', 400.25, 4, 0),
(23198765, 'QR-90-ST', '2023-02-28', '2023-04-28', 120, 5, 0),
(98765432, 'UV-12-XY', '2023-03-10', '2023-05-10', 280.5, 3, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `vehicle`
--

CREATE TABLE `vehicle` (
  `brand` varchar(45) NOT NULL,
  `model` varchar(45) NOT NULL,
  `color` varchar(45) NOT NULL,
  `registration_date` date NOT NULL,
  `plate` char(8) NOT NULL,
  `vin` char(17) NOT NULL,
  `category` int(1) NOT NULL,
  `nif` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `vehicle`
--

INSERT INTO `vehicle` (`brand`, `model`, `color`, `registration_date`, `plate`, `vin`, `category`, `nif`) VALUES
('Toyota', 'Camry', 'Blue', '2022-07-15', 'AB-12-CD', '1HGBH41JXMN109186', 3, 123456789),
('Toyota', 'Rav4', 'Blue', '2021-11-20', 'AB-34-CD', '1G1JD6SH7E4146835', 3, 987123456),
('Toyota', 'Highlander', 'Blue', '2023-11-05', 'AB-78-CD', '5J6RE4H72AL123456', 3, 432109876),
('Honda', 'Civic', 'Red', '2021-10-20', 'EF-34-GH', '2G1WC5E3XF1234567', 4, 987654321),
('Ford', 'Focus', 'Red', '2023-05-05', 'EF-56-GH', 'JN1AJ0HPXCM123456', 4, 456789012),
('Ford', 'Escape', 'Red', '2021-12-25', 'EF-90-GH', '1FTEX1C81FK123456', 4, 987012345),
('Ford', 'Fiesta', 'Silver', '2023-03-25', 'IJ-56-KL', '3FA6P0HD8FR123456', 2, 456789123),
('Chevrolet', 'Impala', 'Black', '2022-08-20', 'IJ-78-KL', '1HGCM82633A123456', 2, 765432109),
('Chevrolet', 'Malibu', 'Black', '2020-12-10', 'MN-78-OP', '1FTFW1EF3CF123456', 5, 789123456),
('Nissan', 'Maxima', 'Silver', '2020-09-30', 'MN-90-OP', 'JHMGE8H40CC123456', 5, 321098765),
('Toyota', 'Corolla', 'White', '2022-04-22', 'QR-56-ST', '3MZBM1L78FM123456', 6, 901234567),
('Nissan', 'Altima', 'White', '2022-09-15', 'QR-90-ST', '1FM5K8GT4FG123456', 6, 231987654),
('Honda', 'Accord', 'Gray', '2023-06-27', 'UV-12-XY', '5FNRL3H24FB123456', 1, 876543219),
('Honda', 'Pilot', 'Gray', '2023-01-30', 'UV-34-XY', '1FADP3F24EL123456', 1, 109876543);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`driver_license_number`),
  ADD UNIQUE KEY `nif` (`nif`),
  ADD UNIQUE KEY `nif_2` (`nif`);

--
-- Índices para tabela `employee`
--
ALTER TABLE `employee`
  ADD UNIQUE KEY `nif` (`nif`);

--
-- Índices para tabela `insurance`
--
ALTER TABLE `insurance`
  ADD PRIMARY KEY (`policy`),
  ADD KEY `Plate_Insurance_FK` (`plate`);

--
-- Índices para tabela `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`nif`);

--
-- Índices para tabela `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`plate`,`driver_license_number`),
  ADD KEY `Driver_In_Ticket_FK` (`driver_license_number`);

--
-- Índices para tabela `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`plate`),
  ADD UNIQUE KEY `vin` (`vin`),
  ADD KEY `Owner_NIF` (`nif`);

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `nif_user` FOREIGN KEY (`nif`) REFERENCES `person` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `nif_employee` FOREIGN KEY (`nif`) REFERENCES `person` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `insurance`
--
ALTER TABLE `insurance`
  ADD CONSTRAINT `Plate_Insurance_FK` FOREIGN KEY (`plate`) REFERENCES `vehicle` (`plate`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `Driver_In_Ticket_FK` FOREIGN KEY (`driver_license_number`) REFERENCES `customer` (`driver_license_number`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Plate_Number_Ticket_FK` FOREIGN KEY (`plate`) REFERENCES `vehicle` (`plate`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `vehicle`
--
ALTER TABLE `vehicle`
  ADD CONSTRAINT `Owner_NIF` FOREIGN KEY (`nif`) REFERENCES `customer` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
