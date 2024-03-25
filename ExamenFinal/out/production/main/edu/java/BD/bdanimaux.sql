-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 05 mars 2024 à 17:08
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bdanimaux`
--
CREATE DATABASE IF NOT EXISTS `bdanimaux` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bdanimaux`;

-- --------------------------------------------------------

--
-- Structure de la table `animaux`
--

CREATE TABLE `animaux` (
  `nom` varchar(40) NOT NULL,
  `classe` varchar(30) NOT NULL,
  `longevite` int(11) NOT NULL,
  `habitat` varchar(20) NOT NULL,
  `fait` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `animaux`
--

INSERT INTO `animaux` (`nom`, `classe`, `longevite`, `habitat`, `fait`) VALUES
('Aigle royal', 'Oiseaux', 30, 'Montagnes', 'Ils peuvent atteindre une vitesse de 240 km/h en piqué.'),
('Axolotl', 'Amphibiens', 15, 'Lacs', 'Les axolotls peuvent régénérer des membres perdus.'),
('Baleine bleue', 'Mammifères', 90, 'Océans', 'La baleine bleue est le plus grand animal vivant sur Terre.'),
('Caméléon', 'Reptiles', 5, 'Forêts tropicales', 'Les caméléons peuvent changer de couleur pour communiquer ou réguler leur température.'),
('Carpe koi', 'Poissons', 35, 'Étangs', 'Les carpes koi sont un symbole de chance et de prospérité.'),
('Colibri', 'Oiseaux', 5, 'Forêts tropicales', 'Les colibris sont les seuls oiseaux capables de voler en arrière.'),
('Crapaud commun', 'Amphibiens', 10, 'Jardins, forêts', 'Les crapauds ont une peau qui sécrète des substances pour se protéger des prédateurs.'),
('Crocodile du Nil', 'Reptiles', 45, 'Rivières', 'Les crocodiles du Nil sont parmi les plus grands reptiles du monde.'),
('Dauphin', 'Mammifères', 40, 'Océans', 'Les dauphins utilisent l\'écholocation pour trouver leur nourriture.'),
('Éléphant d\'Afrique', 'Mammifères', 70, 'Savane', 'Les éléphants ont une mémoire exceptionnelle.'),
('Faucon pèlerin', 'Oiseaux', 15, 'Divers habitats', 'Le faucon pèlerin est l\'oiseau le plus rapide du monde en piqué.'),
('Gorille de montagne', 'Mammifères', 35, 'Forêts de montagne', 'Les gorilles de montagne sont des animaux très sociaux.'),
('Grenouille arboricole', 'Amphibiens', 5, 'Forêts humides', 'Les grenouilles arboricoles passent la majorité de leur vie dans les arbres.'),
('Grenouille de verre', 'Amphibiens', 10, 'Forêts tropicales', 'La peau transparente de la grenouille de verre permet de voir ses organes internes.'),
('Grenouille venimeuse', 'Amphibiens', 10, 'Forêts tropicales', 'Leur peau produit un poison puissant.'),
('Hibou grand-duc', 'Oiseaux', 25, 'Forêts', 'Les hiboux grand-duc n\'ont pas de véritables oreilles.'),
('Iguane vert', 'Reptiles', 20, 'Forêts tropicales', 'Les iguanes verts peuvent survivre à des chutes de plus de 15 mètres.'),
('Lion', 'Mammifères', 14, 'Savane', 'Les lions sont les seuls félins qui vivent en groupes appelés troupes.'),
('Mouette rieuse', 'Oiseaux', 15, 'Côtes, lacs, villes', 'Les mouettes peuvent boire de l\'eau de mer grâce à des glandes spécialisées qui filtrent le sel.'),
('Murène', 'Poissons', 30, 'Récifs coralliens', 'Les murènes ont deux ensembles de mâchoires.'),
('nom', 'classe', 0, 'habitat', 'fait'),
('Orang-outan', 'Mammifères', 30, 'Forêts tropicales', 'Les orangs-outans sont extrêmement intelligents et capables d\'utiliser des outils.'),
('Panda géant', 'Mammifères', 20, 'Forêts de bambous', 'Les pandas géants se nourrissent presque exclusivement de bambou.'),
('Perroquet', 'Oiseaux', 50, 'Forêts tropicales', 'Les perroquets sont capables d\'imiter les sons humains.'),
('Pingouin empereur', 'Oiseaux', 20, 'Antarctique', 'Les pingouins empereurs peuvent plonger jusqu\'à 500 mètres de profondeur.'),
('Poisson-clown', 'Poissons', 6, 'Récifs coralliens', 'Les poissons-clowns vivent en symbiose avec les anémones de mer.'),
('Python royal', 'Reptiles', 20, 'Savanes, forêts', 'Les pythons royaux sont connus pour leur nature docile et sont souvent des animaux de compagnie.'),
('Requin blanc', 'Poissons', 70, 'Océans', 'Les requins blancs sont au sommet de la chaîne alimentaire marine.'),
('Salamandre', 'Amphibiens', 20, 'Zones humides', 'Certaines salamandres peuvent régénérer des parties de leur corps.'),
('Serpent boa', 'Reptiles', 30, 'Forêts tropicales', 'Les boas étranglent leurs proies pour les tuer.'),
('Thon rouge', 'Poissons', 40, 'Océan ouvert', 'Le thon rouge peut nager à des vitesses dépassant les 70 km/h.'),
('Tortue géante', 'Reptiles', 100, 'Îles', 'Certaines tortues géantes peuvent peser jusqu\'à 400 kg.'),
('Triton', 'Amphibiens', 12, 'Eaux douces', 'Les tritons retournent à l\'eau pour se reproduire.'),
('Varan', 'Reptiles', 20, 'Forêts tropicales', 'Les varans sont parmi les plus grands lézards au monde.');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `animaux`
--
ALTER TABLE `animaux`
  ADD PRIMARY KEY (`nom`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
