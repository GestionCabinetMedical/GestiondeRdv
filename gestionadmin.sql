-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 26 Novembre 2020 à 17:44
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `gestionadmin`
--

-- --------------------------------------------------------

--
-- Structure de la table `formule`
--

CREATE TABLE IF NOT EXISTS `formule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `frais_service` float NOT NULL,
  `prix_consultation` float NOT NULL,
  `prixttc` float NOT NULL,
  `specialite` varchar(255) NOT NULL,
  `taxe` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `formule`
--

INSERT INTO `formule` (`id`, `frais_service`, `prix_consultation`, `prixttc`, `specialite`, `taxe`) VALUES
(1, 25.3, 32.3, 54.3, 'Java JEE', 10),
(2, 11.3, 33.3, 25.5, 'Java JEE', 5);

-- --------------------------------------------------------

--
-- Structure de la table `gains_appli`
--

CREATE TABLE IF NOT EXISTS `gains_appli` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `gain` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `gains_appli`
--

INSERT INTO `gains_appli` (`id`, `date`, `gain`) VALUES
(1, '2020-11-27 00:00:00', 32),
(2, '2020-11-28 00:00:00', 54);

-- --------------------------------------------------------

--
-- Structure de la table `gains_medecin`
--

CREATE TABLE IF NOT EXISTS `gains_medecin` (
  `id_gains_medecin` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `gains` float DEFAULT NULL,
  `id_medecin` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_gains_medecin`),
  UNIQUE KEY `UK_bib510wr7p9g4ljj1cby5nfn1` (`date`),
  UNIQUE KEY `UK_cs4fay8vd91kmos3suy23sfux` (`id_medecin`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `gains_medecin`
--

INSERT INTO `gains_medecin` (`id_gains_medecin`, `date`, `gains`, `id_medecin`) VALUES
(1, '2020-11-27 00:00:00', 35.6, 2),
(2, '2020-11-28 00:00:00', 50.3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `questionnaire_satisfaction`
--

CREATE TABLE IF NOT EXISTS `questionnaire_satisfaction` (
  `id_questionnaire` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_consultation` bigint(20) DEFAULT NULL,
  `id_patient` bigint(20) DEFAULT NULL,
  `nom_questionnaire` varchar(255) NOT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`id_questionnaire`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `questionnaire_satisfaction`
--

INSERT INTO `questionnaire_satisfaction` (`id_questionnaire`, `id_consultation`, `id_patient`, `nom_questionnaire`, `status`) VALUES
(1, 2, 2, 'Mon 1er questionnaire', b'0'),
(2, 3, 3, 'Mon 2nd questionnaire', b'0');

-- --------------------------------------------------------

--
-- Structure de la table `question_reponse`
--

CREATE TABLE IF NOT EXISTS `question_reponse` (
  `id_question` bigint(20) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) NOT NULL,
  `reponse` varchar(255) DEFAULT NULL,
  `fk_questionnaire` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_question`),
  KEY `FKn61j3rjbaw6qf9hq2yocuqg6q` (`fk_questionnaire`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `question_reponse`
--

INSERT INTO `question_reponse` (`id_question`, `question`, `reponse`, `fk_questionnaire`) VALUES
(1, 'Etes vous heureux dans la vie ?', NULL, 1),
(2, 'Content du projet ?', NULL, 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
