-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 27-Jul-2023 às 17:45
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
  `extra_category` enum('Personal Damage Insurance','Comprehensive Insurance','Anti-theft Insurance') NOT NULL,
  `plate` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `reason` enum('Speeding Ticket','Red Light Violation','Illegal Parking','Reckless Driving','Driving Under the Influence') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

CREATE TABLE `user` (
  `driver_license_number` int(8) NOT NULL,
  `license_type` enum('A','B','C','D') NOT NULL,
  `start_date` date NOT NULL,
  `expiry_date` date NOT NULL,
  `nif` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `category` enum('Light Commercial Vehicle','Light Passenger Vehicle','Heavy-duty Passenger Vehicle','Heavy-duty Goods Vehicle','Motorcycle','Moped') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `vehicle`
--

INSERT INTO `vehicle` (`brand`, `model`, `color`, `registration_date`, `plate`, `vin`, `category`) VALUES
('Mercedes Benz', 'A8', 'Black', '2023-07-13', '13-24-GD', '23423424FSS', 'Light Commercial Vehicle'),
('Audi Coup', 'C6', 'Azul Escuro', '2023-07-11', 'AS-8F-BB', '23423424FSGGG', 'Heavy-duty Goods Vehicle'),
('BMW', 'I8', 'Branco', '2023-07-12', 'FF-L9-09', '661576866', 'Heavy-duty Goods Vehicle');

--
-- Índices para tabelas despejadas
--

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
-- Índices para tabela `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`driver_license_number`),
  ADD UNIQUE KEY `nif` (`nif`);

--
-- Índices para tabela `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`plate`),
  ADD UNIQUE KEY `vin` (`vin`);

--
-- Restrições para despejos de tabelas
--

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
  ADD CONSTRAINT `Driver_In_Ticket_FK` FOREIGN KEY (`driver_license_number`) REFERENCES `user` (`driver_license_number`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Plate_Number_Ticket_FK` FOREIGN KEY (`plate`) REFERENCES `vehicle` (`plate`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `nif_user` FOREIGN KEY (`nif`) REFERENCES `person` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
