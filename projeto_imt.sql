-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 31-Jul-2023 às 17:07
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
                                                                                                         (1234556778, '4', '2023-07-19', '2028-09-20', 270666543),
                                                                                                         (1235566766, '1', '2023-07-11', '2029-07-18', 311393980);

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
                                                                          (270666543, 'Osmar Mafarrico', 'Rua Sem Nome, Porta Inifinta', '2003-07-09', '123abc'),
                                                                          (311393980, 'Mickey Mouse', 'Rua das Maravilhas, Sem FIm', '1994-07-13', 'abc123');

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
                          `reason` int(1) NOT NULL
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
                           `category` int(1) NOT NULL,
                           `nif` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
