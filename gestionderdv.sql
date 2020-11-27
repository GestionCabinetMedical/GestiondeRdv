-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 26 Novembre 2020 à 17:41
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `gestionderdv`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id_admin` bigint(20) NOT NULL AUTO_INCREMENT,
  `mot_de_passe` varchar(255) NOT NULL,
  `identifiant` varchar(255) NOT NULL,
  PRIMARY KEY (`id_admin`),
  UNIQUE KEY `UK_aewqgocb84dsjustaql3qhcpv` (`mot_de_passe`),
  UNIQUE KEY `UK_otoa30ph0w59idaa4e3atktew` (`identifiant`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `admin`
--

INSERT INTO `admin` (`id_admin`, `mot_de_passe`, `identifiant`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `consultation`
--

CREATE TABLE IF NOT EXISTS `consultation` (
  `id_consultation` bigint(20) NOT NULL AUTO_INCREMENT,
  `fk_reservation` bigint(20) NOT NULL,
  `fk_medecin` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_consultation`),
  UNIQUE KEY `UK_sh6wty2xld3ckr5xl3mdk6hwg` (`fk_reservation`),
  KEY `FK1ju2fy4rmiyeb7hg9wge2fmmd` (`fk_medecin`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `consultation`
--

INSERT INTO `consultation` (`id_consultation`, `fk_reservation`, `fk_medecin`) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 3, 1),
(4, 4, 2);

-- --------------------------------------------------------

--
-- Structure de la table `fiches_medicales`
--

CREATE TABLE IF NOT EXISTS `fiches_medicales` (
  `id_fiche_medical` bigint(20) NOT NULL AUTO_INCREMENT,
  `commentaires` varchar(255) DEFAULT NULL,
  `pathologie` varchar(255) NOT NULL,
  `traitement` varchar(255) NOT NULL,
  `fk_consultation` bigint(20) NOT NULL,
  PRIMARY KEY (`id_fiche_medical`),
  UNIQUE KEY `UK_2mskuusl47toj2qev147ihp4b` (`fk_consultation`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `fiches_medicales`
--

INSERT INTO `fiches_medicales` (`id_fiche_medical`, `commentaires`, `pathologie`, `traitement`, `fk_consultation`) VALUES
(1, 'Ne pas rester toute la journée sur l''ordi !', 'Mal aux yeux', 'Vacances', 1),
(2, 'C''est la mewde', 'Erreur de CORS POLICY', 'Changer d''ordidnateur', 3);

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

CREATE TABLE IF NOT EXISTS `medecin` (
  `id_medecin` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresse_cabinet` varchar(255) NOT NULL,
  `id_formule` bigint(20) DEFAULT NULL,
  `identifiant` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `specialite` varchar(255) NOT NULL,
  PRIMARY KEY (`id_medecin`),
  UNIQUE KEY `UK_o1l9yt6m0lwch3yg0akasmsr2` (`identifiant`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `medecin`
--

INSERT INTO `medecin` (`id_medecin`, `adresse_cabinet`, `id_formule`, `identifiant`, `mot_de_passe`, `nom`, `prenom`, `specialite`) VALUES
(1, 'localhost', 1, 'Ismail', '1234', 'Jebali', 'Ismail', 'Java JEE'),
(2, 'Nantes', 2, 'Mehdi', '1234', 'Ben Salha', 'Mehdi', 'Java JEE');

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `id_patient` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) NOT NULL,
  `identifiant` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `numero_securite_sociale` bigint(20) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  PRIMARY KEY (`id_patient`),
  UNIQUE KEY `UK_71xu830g0ruoe2uopns8yh295` (`identifiant`),
  UNIQUE KEY `UK_owka9io1li505gsiheph2v50p` (`numero_securite_sociale`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `patient`
--

INSERT INTO `patient` (`id_patient`, `adresse`, `identifiant`, `mot_de_passe`, `nom`, `numero_securite_sociale`, `prenom`) VALUES
(1, 'Dijon', 'maxime', '1234', 'Rembert', 1930570, 'Maxime'),
(2, 'Aix en provence', 'pauline', '1234', 'Humbert', 90, 'Pauline'),
(3, 'Nice', 'Sophie', '1234', 'Lahmar', 91, 'Sophie'),
(4, 'Lyon', 'Jonathan', '1234', 'inconnu', 191, 'jonathan');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `id_reservation` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_reservation` varchar(255) NOT NULL,
  `heure_rdv` int(11) DEFAULT NULL,
  `consultation_en_urgence` tinyint(1) DEFAULT '0',
  `status` tinyint(1) DEFAULT '0',
  `fk_patient` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_reservation`),
  KEY `FKcqohqj5v2r5n80h2m0so0afuo` (`fk_patient`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `reservation`
--

INSERT INTO `reservation` (`id_reservation`, `date_reservation`, `heure_rdv`, `consultation_en_urgence`, `status`, `fk_patient`) VALUES
(1, '2020-11-27', 2, 0, 0, 1),
(2, '2020-11-27', 4, 0, 1, 2),
(3, '2020-11-27', NULL, 1, 1, 3),
(4, '2020-11-27', 6, 0, 0, 4);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
