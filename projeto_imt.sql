-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 11-Ago-2023 às 12:08
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
                            `license_type` int(1) NOT NULL,
                            `start_date` date NOT NULL,
                            `expiration_date` date NOT NULL,
                            `nif` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `customer`
--

INSERT INTO `customer` (`driver_license_number`, `license_type`, `start_date`, `expiration_date`, `nif`) VALUES
                                                                                                             (10000000, 1, '2022-01-01', '2025-01-01', 200000000),
                                                                                                             (10000001, 2, '2022-02-01', '2026-02-01', 200000001),
                                                                                                             (10000002, 3, '2022-03-01', '2025-03-01', 200000002),
                                                                                                             (10000003, 4, '2022-04-01', '2026-04-01', 200000003),
                                                                                                             (10000004, 1, '2022-05-01', '2025-05-01', 200000004),
                                                                                                             (10000005, 2, '2022-06-01', '2026-06-01', 200000005),
                                                                                                             (10000006, 3, '2022-07-01', '2025-07-01', 200000006),
                                                                                                             (10000007, 4, '2022-08-01', '2026-08-01', 200000007),
                                                                                                             (10000008, 1, '2022-09-01', '2025-09-01', 200000008),
                                                                                                             (10000009, 2, '2022-10-01', '2026-10-01', 200000009),
                                                                                                             (10012345, 3, '2022-11-01', '2025-11-01', 200123456),
                                                                                                             (11098536, 4, '2022-12-01', '2026-12-01', 210985367),
                                                                                                             (11098765, 1, '2023-01-01', '2025-01-01', 210987654),
                                                                                                             (11234567, 2, '2023-02-01', '2026-02-01', 211234567),
                                                                                                             (12345678, 3, '2023-03-01', '2025-03-01', 212345678),
                                                                                                             (13045987, 4, '2023-04-01', '2026-04-01', 213045987),
                                                                                                             (13456789, 1, '2023-05-01', '2025-05-01', 213456789),
                                                                                                             (14320987, 2, '2023-06-01', '2026-06-01', 214320987),
                                                                                                             (14321876, 3, '2023-07-01', '2025-07-01', 214321876),
                                                                                                             (14509678, 4, '2023-08-01', '2026-08-01', 214509678),
                                                                                                             (14567890, 1, '2023-09-01', '2025-09-01', 214567890),
                                                                                                             (15432198, 2, '2023-10-01', '2026-10-01', 215432198),
                                                                                                             (15678923, 3, '2023-11-01', '2025-11-01', 215678923),
                                                                                                             (16809245, 4, '2023-12-01', '2026-12-01', 216809245),
                                                                                                             (17903456, 1, '2024-01-01', '2025-01-01', 217890345),
                                                                                                             (17904321, 2, '2024-02-01', '2026-02-01', 217890432),
                                                                                                             (17904567, 3, '2024-03-01', '2025-03-01', 217890456),
                                                                                                             (18763095, 4, '2024-04-01', '2026-04-01', 218763095),
                                                                                                             (18765432, 1, '2024-05-01', '2025-05-01', 218765432),
                                                                                                             (18903765, 2, '2024-06-01', '2026-06-01', 218903765),
                                                                                                             (19654078, 3, '2024-07-01', '2025-07-01', 219654078),
                                                                                                             (19676543, 4, '2024-08-01', '2026-08-01', 219876543),
                                                                                                             (20256348, 1, '2024-09-01', '2025-09-01', 225078634),
                                                                                                             (20567159, 2, '2024-10-01', '2026-10-01', 228370159),
                                                                                                             (20765439, 3, '2024-11-01', '2025-11-01', 228765439),
                                                                                                             (20934567, 4, '2024-12-01', '2026-12-01', 229345671),
                                                                                                             (21019876, 1, '2025-01-01', '2025-01-01', 230198765),
                                                                                                             (21095482, 2, '2025-02-01', '2026-02-01', 230954823),
                                                                                                             (21098765, 3, '2025-03-01', '2025-03-01', 230987654),
                                                                                                             (21123456, 4, '2025-04-01', '2026-04-01', 231234567);

-- --------------------------------------------------------

--
-- Estrutura da tabela `employee`
--

CREATE TABLE `employee` (
                            `nif` int(9) NOT NULL,
                            `access_level` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `insurance`
--

CREATE TABLE `insurance` (
                             `policy` int(9) NOT NULL,
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
                                                                                                          (100000000, '2025-08-14', 'AGEAS Seguros', '2023-08-10', 3, 'AB-12-CD'),
                                                                                                          (100000002, '2027-09-20', 'SafeGuard Insurers', '2023-03-05', 3, 'IJ-56-KL'),
                                                                                                          (100000003, '2025-12-05', 'SecureCover Corp', '2023-02-10', 1, 'MN-90-OP'),
                                                                                                          (100000004, '2026-11-10', 'TrustShield Insurance', '2023-01-20', 2, 'QR-90-ST'),
                                                                                                          (100000005, '2028-08-30', 'Fortress Insurances', '2023-05-01', 3, 'UV-34-WX'),
                                                                                                          (100000006, '2025-07-18', 'Guardian Assurance', '2023-04-15', 1, 'CD-78-EF'),
                                                                                                          (100000007, '2025-09-15', 'SafetyNet Ltd.', '2023-03-25', 2, 'GH-90-IJ'),
                                                                                                          (100000008, '2027-05-25', 'United Protectors', '2023-02-05', 3, 'KL-12-MN'),
                                                                                                          (100000009, '2028-04-12', 'SecureLife Insurance', '2023-01-15', 1, 'OP-34-QR'),
                                                                                                          (100000010, '2025-10-08', 'AssureGuard Inc.', '2023-05-05', 2, 'ST-56-UV'),
                                                                                                          (100000013, '2025-11-30', 'SecureCover Corp', '2023-02-01', 2, 'GH-IJ-KL'),
                                                                                                          (100000014, '2027-04-05', 'TrustShield Insurance', '2023-01-11', 3, 'KL-MN-OP'),
                                                                                                          (100000016, '2027-12-19', 'Guardian Assurance', '2023-04-20', 2, 'ST-56-UV'),
                                                                                                          (100000017, '2025-06-28', 'United Protectors', '2023-03-01', 3, 'WX-78-YZ'),
                                                                                                          (100000019, '2028-02-21', 'Peace of Mind Insurers', '2023-01-25', 2, 'EF-12-GH'),
                                                                                                          (100000020, '2025-09-02', 'AssureGuard Inc.', '2023-05-15', 3, 'IJ-78-KL'),
                                                                                                          (100000021, '2025-11-18', 'TrustShield Insurance', '2023-04-05', 1, 'MN-34-OP'),
                                                                                                          (100000022, '2026-08-07', 'Guardian Assurance', '2023-03-15', 2, 'QR-12-ST'),
                                                                                                          (100000023, '2027-07-03', 'Fortress Insurances', '2023-02-20', 3, 'ST-UV-WX'),
                                                                                                          (100000024, '2025-04-27', 'Guardian Assurance', '2023-01-30', 1, 'AB-90-CD'),
                                                                                                          (100000029, '2027-03-16', 'United Protectors', '2023-01-10', 3, 'UV-34-WX');

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
                                                                          (200000000, 'John Doe', '123 Main St', '1985-07-15', 'passwd01'),
                                                                          (200000001, 'Jane Smith', '456 Elm St', '1990-03-25', 'secure02'),
                                                                          (200000002, 'Michael Johnson', '789 Oak St', '1977-11-10', 'safety03'),
                                                                          (200000003, 'Emily Williams', '222 Pine St', '2000-06-03', 'secret04'),
                                                                          (200000004, 'William Brown', '555 Maple St', '1998-09-22', 'keylock0'),
                                                                          (200000005, 'Jessica Davis', '777 Birch St', '1983-02-18', 'protect0'),
                                                                          (200000006, 'Matthew Wilson', '999 Redwood St', '1995-04-30', 'safeguar'),
                                                                          (200000007, 'Olivia Jones', '111 Oak St', '1988-08-08', 'access08'),
                                                                          (200000008, 'David Lee', '444 Elm St', '1992-05-15', 'private0'),
                                                                          (200000009, 'Sophia Martin', '777 Pine St', '1979-12-30', 'hidden10'),
                                                                          (200123456, 'Aria Lewis', '111 Oak St', '2006-06-30', 'secret20'),
                                                                          (210985367, 'Alice Johnson', '123 Main St', '1989-07-15', 'passwd01'),
                                                                          (210987654, 'Isabella Harris', '678 Elm St', '1985-09-12', 'secure12'),
                                                                          (211234567, 'Oliver Johnson', '123 Main St', '1996-06-15', 'password'),
                                                                          (212345678, 'Liam Brown', '222 Pine St', '1998-06-03', 'secret04'),
                                                                          (213045987, 'Charlotte Wilson', '987 Maple St', '1987-07-07', 'protecte'),
                                                                          (213456789, 'Harper Lewis', '123 Birch St', '2002-07-07', 'protecte'),
                                                                          (214320987, 'Emma Davis', '555 Maple St', '1982-09-22', 'keylock0'),
                                                                          (214321876, 'Aria Lewis', '111 Oak St', '2004-06-30', 'secret20'),
                                                                          (214509678, 'Ethan Anderson', '567 Oak St', '1990-01-20', 'locked11'),
                                                                          (214567890, 'Emma Davis', '555 Maple St', '1987-09-22', 'keylock0'),
                                                                          (215432198, 'Sophia Wilson', '999 Redwood St', '1996-04-30', 'safeguar'),
                                                                          (215678923, 'Olivia Brown', '222 Pine St', '2002-06-03', 'secret04'),
                                                                          (216809245, 'Ava Martin', '444 Elm St', '1990-05-15', 'private0'),
                                                                          (217890345, 'Noah Jones', '777 Birch St', '1993-02-18', 'protect0'),
                                                                          (217890432, 'Harper Lewis', '123 Birch St', '2002-07-07', 'protecte'),
                                                                          (217890456, 'Aiden White', '789 Oak St', '1998-03-01', 'safe13'),
                                                                          (218763095, 'Aria Lewis', '111 Oak St', '2005-06-30', 'secret20'),
                                                                          (218765432, 'Sophia Johnson', '123 Main St', '1998-07-15', 'passwd01'),
                                                                          (218903765, 'Liam Robinson', '567 Oak St', '2006-11-11', 'pass16'),
                                                                          (219654078, 'Aria Lewis', '111 Oak St', '2003-06-30', 'secret20'),
                                                                          (219876543, 'Isabella White', '789 Oak St', '1989-03-01', 'safe13'),
                                                                          (225078634, 'Emma Williams', '789 Oak St', '1982-11-10', 'safety03'),
                                                                          (228370159, 'Lucas Taylor', '777 Pine St', '2002-12-30', 'hidden10'),
                                                                          (228765439, 'Ella Walker', '789 Elm St', '1996-02-02', 'secure18'),
                                                                          (229345671, 'Liam Robinson', '567 Oak St', '1976-10-25', 'key17'),
                                                                          (230198765, 'Lucas Martin', '444 Elm St', '1999-05-15', 'private0'),
                                                                          (230954823, 'Noah Jones', '777 Birch St', '1986-02-18', 'protect0'),
                                                                          (230987654, 'Charlotte Johnson', '890 Pine St', '1976-05-05', 'code14'),
                                                                          (231234567, 'Liam Robinson', '567 Oak St', '2005-11-11', 'pass16'),
                                                                          (231409875, 'Jackson Smith', '456 Elm St', '1991-03-25', 'secure02'),
                                                                          (231456789, 'Noah Jones', '777 Birch St', '1994-02-18', 'protect0'),
                                                                          (234567812, 'Liam Johnson', '123 Main St', '1997-06-15', 'passwd01'),
                                                                          (234567890, 'Oliver Johnson', '123 Main St', '1996-06-15', 'password'),
                                                                          (235467890, 'Ava Taylor', '777 Pine St', '1977-12-30', 'hidden10'),
                                                                          (235678912, 'Mia Lee', '111 Oak St', '2000-08-08', 'access08'),
                                                                          (235679840, 'Olivia Davis', '555 Maple St', '2001-09-22', 'keylock0'),
                                                                          (241390875, 'Lucas Martin', '444 Elm St', '1991-05-15', 'private0'),
                                                                          (242109583, 'Jackson Lee', '111 Oak St', '1995-08-08', 'access08'),
                                                                          (242345678, 'Sophia Smith', '456 Elm St', '1990-03-25', 'secure02'),
                                                                          (243567890, 'Mason Turner', '890 Pine St', '1987-04-15', 'safe19'),
                                                                          (244536710, 'Mason Turner', '890 Pine St', '1973-04-15', 'safe19'),
                                                                          (245678901, 'Mia Lee', '111 Oak St', '2001-08-08', 'access08'),
                                                                          (246710954, 'Robert Smith', '456 Elm St', '1993-03-25', 'secure02'),
                                                                          (246789032, 'Harper Lewis', '123 Birch St', '2003-07-07', 'protecte'),
                                                                          (248901234, 'Ella Walker', '789 Elm St', '1993-02-02', 'secure18'),
                                                                          (249785634, 'Olivia Williams', '789 Oak St', '1975-11-10', 'safety03'),
                                                                          (249871356, 'Sophia Smith', '456 Elm St', '1989-03-25', 'secure02'),
                                                                          (253456789, 'Mason Turner', '890 Pine St', '1983-04-15', 'safe19'),
                                                                          (253487610, 'Mia Lee', '111 Oak St', '2001-08-08', 'access08'),
                                                                          (253487619, 'Mason Turner', '890 Pine St', '1986-04-15', 'safe19'),
                                                                          (254679083, 'Ethan Anderson', '567 Oak St', '1992-01-20', 'locked11'),
                                                                          (254983126, 'Mia Anderson', '567 Oak St', '1981-01-20', 'locked11'),
                                                                          (256789012, 'Mia Lee', '111 Oak St', '2001-08-08', 'access08'),
                                                                          (256789014, 'Ava Taylor', '777 Pine St', '1978-12-30', 'hidden10'),
                                                                          (257890123, 'Lucas Martin', '444 Elm St', '2000-05-15', 'private0'),
                                                                          (263415792, 'Harper Martinez', '123 Birch St', '2004-11-11', 'pass16'),
                                                                          (263456789, 'Lucas Martin', '444 Elm St', '2000-05-15', 'private0'),
                                                                          (267890123, 'Charlotte Johnson', '890 Pine St', '1977-05-05', 'code14'),
                                                                          (268901234, 'Charlotte Johnson', '890 Pine St', '1977-05-05', 'code14'),
                                                                          (269876543, 'Aria Lewis', '111 Oak St', '2006-06-30', 'secret20'),
                                                                          (271698342, 'Sophia Wilson', '999 Redwood St', '1977-04-30', 'safeguar'),
                                                                          (275678901, 'Jackson Williams', '789 Oak St', '1978-11-10', 'safety03'),
                                                                          (276540391, 'Ella Walker', '789 Elm St', '1994-02-02', 'secure18'),
                                                                          (276543091, 'Jackson Williams', '789 Oak St', '1974-11-10', 'safety03'),
                                                                          (276543210, 'Charlotte Johnson', '890 Pine St', '1973-05-05', 'code14'),
                                                                          (276543219, 'Jackson Williams', '789 Oak St', '1978-11-10', 'safety03'),
                                                                          (276854903, 'Ella Walker', '789 Elm St', '2000-02-02', 'secure18'),
                                                                          (277654321, 'Ava Taylor', '777 Pine St', '1979-12-30', 'hidden10'),
                                                                          (280145679, 'Isabella Harris', '678 Elm St', '1983-09-12', 'secure12'),
                                                                          (281234567, 'Isabella Harris', '678 Elm St', '1985-09-12', 'secure12'),
                                                                          (285432109, 'Isabella Harris', '678 Elm St', '1985-09-12', 'secure12'),
                                                                          (287654321, 'Liam Robinson', '567 Oak St', '2005-11-11', 'pass16'),
                                                                          (288760943, 'Aiden Harris', '890 Pine St', '1997-05-05', 'code14'),
                                                                          (289076543, 'Aiden White', '789 Oak St', '2000-03-01', 'safe13'),
                                                                          (289345671, 'Sophia Wilson', '999 Redwood St', '1995-04-30', 'safeguar'),
                                                                          (289567432, 'Noah Jones', '777 Birch St', '1990-02-18', 'protect0'),
                                                                          (290123456, 'Mason Turner', '890 Pine St', '1983-04-15', 'safe19'),
                                                                          (290145672, 'Emma Davis', '555 Maple St', '1985-09-22', 'keylock0'),
                                                                          (292345678, 'Aiden White', '789 Oak St', '2001-03-01', 'safe13'),
                                                                          (295634128, 'Ethan Thomas', '678 Elm St', '1996-09-12', 'secure12'),
                                                                          (295678901, 'Sophia Wilson', '999 Redwood St', '1994-04-30', 'safeguar'),
                                                                          (296789012, 'Olivia Brown', '222 Pine St', '2004-06-03', 'secret04'),
                                                                          (298765432, 'Olivia Brown', '222 Pine St', '2004-06-03', 'secret04');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ticket`
--

CREATE TABLE `ticket` (
                          `nif` int(9) NOT NULL,
                          `plate` char(8) NOT NULL,
                          `date` date NOT NULL,
                          `expiry_date` date NOT NULL,
                          `value` double NOT NULL,
                          `reason` int(1) NOT NULL,
                          `paid` tinyint(1) NOT NULL DEFAULT 0
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
-- Extraindo dados da tabela `vehicle`
--

INSERT INTO `vehicle` (`brand`, `model`, `color`, `registration_date`, `plate`, `vin`, `category`, `nif`) VALUES
                                                                                                              ('Audi', 'A4', 'Black', '2022-10-01', '34-56-78', 'A3456789012BCDE34', 4, 200000009),
                                                                                                              ('Lexus', 'RX', 'White', '2022-11-01', 'A8-CD-EF', 'B4567890123CDEF56', 5, 200123456),
                                                                                                              ('Toyota', 'Camry', 'Blue', '2023-08-15', 'AB-12-CD', 'JTDKN3DU2E0386141', 3, 200000000),
                                                                                                              ('Toyota', 'Sienna', 'Blue', '2025-04-03', 'AB-90-CD', '5TDZZ3DC2KS123456', 1, 212345678),
                                                                                                              ('Toyota', 'Corolla', 'Blue', '2022-01-01', 'AB-CD-EF', '1ABC23456789DEF01', 1, 200000000),
                                                                                                              ('Chevrolet', 'Equinox', 'Silver', '2022-09-21', 'CD-78-EF', '2GNAXUEV8J6234567', 3, 200000008),
                                                                                                              ('Nissan', 'Sentra', 'Silver', '2023-09-12', 'EF-12-GH', '3N1AB7AP2KY123456', 2, 213045987),
                                                                                                              ('Ford', 'F-150', 'Black', '2023-05-18', 'EF-56-GH', '1FTFW1E82MKD12345', 4, 200000002),
                                                                                                              ('Nissan', 'Altima', 'Gray', '2022-06-01', 'EF-GH-IJ', '6EFG78901234HIJ89', 6, 200000005),
                                                                                                              ('Hyundai', 'Elantra', 'Red', '2022-12-01', 'G2-IJ-KL', 'C5678901234DEFG67', 6, 210985367),
                                                                                                              ('Toyota', 'Highlander', 'Black', '2022-07-02', 'GH-90-IJ', '5TDBZRFH4JS123456', 4, 200000009),
                                                                                                              ('Honda', 'Civic', 'Red', '2022-02-01', 'GH-IJ-KL', '2GHI34567890JKL12', 2, 200000001),
                                                                                                              ('Ford', 'Mustang', 'Red', '2022-12-29', 'IJ-56-KL', '1FA6P8TH0H5123456', 5, 213456789),
                                                                                                              ('Chevrolet', 'Malibu', 'Red', '2022-11-30', 'IJ-78-KL', '1G1ZD5E08JF123456', 3, 200000003),
                                                                                                              ('Nissan', 'Rogue', 'Red', '2023-04-17', 'KL-12-MN', '5N1AT2MT7KC123456', 3, 200123456),
                                                                                                              ('Subaru', 'Outback', 'Green', '2022-07-01', 'KL-MN-OP', '7KLM90123456NOP01', 1, 200000006),
                                                                                                              ('Chevrolet', 'Cruze', 'White', '2025-07-08', 'MN-34-OP', '1G1BE5SM0K1234567', 2, 214320987),
                                                                                                              ('Toyota', 'Corolla', 'White', '2025-02-09', 'MN-90-OP', '2T1BURHE2KC123456', 1, 200000004),
                                                                                                              ('Ford', 'Focus', 'Silver', '2022-03-01', 'MN-OP-QR', '3MNO45678901PQR23', 3, 200000002),
                                                                                                              ('Honda', 'CR-V', 'White', '2022-11-08', 'OP-34-QR', '2HKRW1H57DA123456', 2, 210985367),
                                                                                                              ('Nissan', 'Altima', 'Gray', '2022-06-12', 'QR-12-ST', '1N4AL3AP9FC123456', 2, 200000005),
                                                                                                              ('Toyota', 'RAV4', 'Gray', '2023-03-20', 'QR-90-ST', 'JTMBFREV5JD123456', 4, 214321876),
                                                                                                              ('Mercedes-Benz', 'E-Class', 'Silver', '2022-08-01', 'QR-ST-UV', '8QRS01234567TUV23', 2, 200000007),
                                                                                                              ('Ford', 'Edge', 'Gray', '2022-05-19', 'ST-56-UV', '2FMPK3G96GB123456', 3, 210987654),
                                                                                                              ('Chevrolet', 'Cruze', 'Black', '2022-04-01', 'ST-UV-WX', '4STU56789012WX45', 4, 200000003),
                                                                                                              ('Honda', 'Accord', 'Blue', '2022-12-15', 'UV-34-WX', '1HGCR2F32EA123456', 2, 200000006),
                                                                                                              ('Chevrolet', 'Silverado', 'Black', '2025-08-30', 'WX-78-YZ', '1GCVKREH9FZ123456', 5, 211234567),
                                                                                                              ('BMW', '3 Series', 'Blue', '2022-09-01', 'WX-YZ-12', '9WXY12345678ZAB01', 3, 200000008),
                                                                                                              ('Honda', 'Civic', 'Silver', '2022-07-25', 'XY-34-ZW', '1HGCM82633A912345', 2, 200000001),
                                                                                                              ('Volkswagen', 'Golf', 'White', '2022-05-01', 'YZ-12-34', '5YZA67890123BCD67', 5, 200000004),
                                                                                                              ('Ford', 'Escape', 'Green', '2023-10-08', 'YZ-56-AB', '1FMCU0J94DUD12345', 3, 200000007);

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
    ADD PRIMARY KEY (`plate`,`nif`),
    ADD KEY `Nif_Offender` (`nif`);

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
    ADD CONSTRAINT `Nif_Offender` FOREIGN KEY (`nif`) REFERENCES `customer` (`nif`),
    ADD CONSTRAINT `Plate_Number_Ticket_FK` FOREIGN KEY (`plate`) REFERENCES `vehicle` (`plate`);

--
-- Limitadores para a tabela `vehicle`
--
ALTER TABLE `vehicle`
    ADD CONSTRAINT `Owner_NIF` FOREIGN KEY (`nif`) REFERENCES `customer` (`nif`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
