-- Autor: Andrés Samuel Podadera González
-- Ciclo: CFGS Desarrollo de aplicaciones multiplataforma
-- Curso: 2024/2025

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

-- -----------------------------------------------------
-- Crear la base de datos matriculas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `matriculas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `matriculas`;

-- -----------------------------------------------------
-- Tabla `matriculas`.`universidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matriculas`.`universidad` (
  `codigo` INT NOT NULL,
  `nombre` VARCHAR(40) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `provincia_uni` VARCHAR(30) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `privada` BOOLEAN NOT NULL,
  PRIMARY KEY (`codigo`)
) 
ENGINE = InnoDB 
DEFAULT CHARACTER SET = utf8mb4 
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabla `matriculas`.`estudiante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `matriculas`.`estudiante` (
  `NIF` VARCHAR(9) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `nombre` VARCHAR(30) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `apellidos` VARCHAR(70) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `direccion` VARCHAR(50) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `provincia` VARCHAR(30) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `importe_matricula` FLOAT(6,2) NOT NULL,
  `becado` BOOLEAN NOT NULL,
  `codigo_uni` INT NOT NULL,
  PRIMARY KEY (`NIF`),
  CONSTRAINT `uni_estudiante` 
    FOREIGN KEY (`codigo_uni`) 
    REFERENCES `matriculas`.`universidad` (`codigo`) 
    ON DELETE RESTRICT 
    ON UPDATE CASCADE
) 
ENGINE = InnoDB 
DEFAULT CHARACTER SET = utf8mb4 
COLLATE = utf8mb4_0900_ai_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Insertar registros en la tabla `matriculas`.`universidad`
-- -----------------------------------------------------
INSERT INTO `matriculas`.`universidad` (`codigo`, `nombre`, `provincia_uni`, `privada`) VALUES
(1001, 'Universidad de Málaga', 'MALAGA', FALSE),
(1002, 'Universidad de Sevilla', 'SEVILLA', FALSE),
(1003, 'Universidad de Granada', 'GRANADA', FALSE),
(1004, 'Universidad de Jaén', 'JAEN', FALSE),
(1005, 'Universidad Loyola', 'CORDOBA', TRUE);

-- -----------------------------------------------------
-- Insertar registros en la tabla `matriculas`.`estudiante`
-- -----------------------------------------------------
INSERT INTO `matriculas`.`estudiante` (`NIF`, `nombre`, `apellidos`, `fecha_nacimiento`, `direccion`, `provincia`, `importe_matricula`, `becado`, `codigo_uni`) VALUES
('11111111A', 'Pepe', 'López Marín', '1984-03-02', 'Calle Falsa 123', 'MALAGA', 2500.50, FALSE, 1001),
('22222222B', 'Lola', 'Pérez Pérez', '1988-01-01', 'Avenida Principal 45', 'SEVILLA', 2000.00, TRUE, 1002),
('33333333C', 'Remedios', 'Costa Rubio', '1996-03-03', 'Plaza Central 33', 'GRANADA', 1800.75, TRUE, 1003),
('44444444D', 'Laura', 'Gómez Gómez', '1978-01-20', 'Calle Real 56', 'JAEN', 2750.30, FALSE, 1004),
('55555555F', 'Juan', 'López López', '1988-05-01', 'Barrio La Paz 99', 'CORDOBA', 5000.00, FALSE, 1005),
('66666666G', 'Ana', 'Marín Marín', '2000-03-02', 'Residencia 200', 'MALAGA', 2300.50, TRUE, 1001),
('77777777H', 'Carmen', 'Casas Cava', '1975-09-25', 'Centro Histórico 77', 'GRANADA', 3200.00, FALSE, 1003),
('88888888J', 'Marcos', 'Luna Pío', '1989-08-15', 'Calle Sol 88', 'MALAGA', 2100.75, TRUE, 1001),
('99999999K', 'Juana', 'Navarro Marín', '1998-04-05', 'Paseo Marítimo 99', 'GRANADA', 2600.00, TRUE, 1003),
('10101010L', 'Elisa', 'López Fuentes', '1990-03-02', 'Avenida Universitaria 10', 'SEVILLA', 1800.00, TRUE, 1002);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
