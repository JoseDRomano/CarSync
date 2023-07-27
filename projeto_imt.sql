-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 25-Jul-2023 às 13:30
-- Versão do servidor: 10.4.28-MariaDB
-- versão do PHP: 8.2.4

SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET
time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `projeto_imt`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `insurance`
--

CREATE TABLE `insurance`
(
    `policy`      int(13) NOT NULL,
    `expiry_date` date        NOT NULL,
    `company`     varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

CREATE TABLE `user`
(
    `nif`      int(9) NOT NULL,
    `name`     varchar(45) NOT NULL,
    `address`  varchar(60) NOT NULL,
    `b_date`   date        NOT NULL,
    `admin`    tinyint(1) NOT NULL,
    `password` varchar(8)  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `vehicle`
--

CREATE TABLE `vehicle`
(
    `brand`             varchar(45) NOT NULL,
    `model`             varchar(45) NOT NULL,
    `color`             varchar(45) NOT NULL,
    `registration_date` date        NOT NULL,
    `plate`             varchar(8)  NOT NULL,
    `vin`               varchar(17) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `ticket`
--

CREATE TABLE `ticket`
(
    `palte`             varchar(45) NOT NULL,
    `model`             varchar(45) NOT NULL,
    `color`             varchar(45) NOT NULL,
    `registration_date` date        NOT NULL,
    `plate`             varchar(8)  NOT NULL,
    `vin`               varchar(17) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `insurance`
--
ALTER TABLE `insurance`
    ADD PRIMARY KEY (`policy`);

--
-- Índices para tabela `user`
--
ALTER TABLE `user`
    ADD PRIMARY KEY (`nif`);

--
-- Índices para tabela `vehicle`
--
ALTER TABLE `vehicle`
    ADD PRIMARY KEY (`plate`),
  ADD UNIQUE KEY `vin` (`vin`);
COMMIT;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
