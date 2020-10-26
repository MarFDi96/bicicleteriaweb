
CREATE DATABASE IF NOT EXISTS `bicicleteria` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bicicleteria`;

CREATE TABLE `personas` (
  `rol` varchar(30) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `password` varchar(12) NOT NULL,
  `usuario` varchar(20) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `piezas` (
  `pieza` varchar(30) NOT NULL,
  `codigo` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `personas` (`rol`, `nombre`, `usuario`, `password`) VALUES
('bicicletero', 'Matías', 'mati','123'),
('encargado', 'Gonzalo', 'gonza','123'),
('vendedor', 'Diego', 'dcorsi','123');
