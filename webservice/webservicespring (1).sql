-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Apr 22, 2022 alle 13:09
-- Versione del server: 10.4.6-MariaDB
-- Versione PHP: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `webservicespring`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `string`
--

CREATE TABLE `string` (
  `Id` int(11) NOT NULL,
  `chiave` varchar(128) NOT NULL,
  `testo` text NOT NULL,
  `idUtente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `string`
--

INSERT INTO `string` (`Id`, `chiave`, `testo`, `idUtente`) VALUES
(16, '2', 'ciao', 12),
(20, '4', 'ciao', 12),
(21, '5', 'ciao', 12);

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE `utente` (
  `Id` int(11) NOT NULL,
  `Username` varchar(128) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `Token` varchar(33) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`Id`, `Username`, `Password`, `Token`) VALUES
(10, 'ciao2', 'ciao1', 'OAuX6vg-1FXk65h9KQqFH80r7L8lMc1m'),
(12, 'skoro', 'skoro', 'tW18aLyosEqU7W_QrRsgtzAK6OgngS3N');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `string`
--
ALTER TABLE `string`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `chiave` (`chiave`),
  ADD KEY `FK1` (`idUtente`);

--
-- Indici per le tabelle `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Username` (`Username`),
  ADD UNIQUE KEY `Token` (`Token`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `string`
--
ALTER TABLE `string`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT per la tabella `utente`
--
ALTER TABLE `utente`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `string`
--
ALTER TABLE `string`
  ADD CONSTRAINT `FK1` FOREIGN KEY (`idUtente`) REFERENCES `utente` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
