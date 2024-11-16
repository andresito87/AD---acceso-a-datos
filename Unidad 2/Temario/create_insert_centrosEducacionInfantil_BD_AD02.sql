SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `centrosEducacionInfantil` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE `centrosEducacionInfantil` ;

-- -----------------------------------------------------
-- Table `centrosEducacionInfantil`.`guarderia`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `centrosEducacionInfantil`.`guarderia` (
  `codigo` VARCHAR(5) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL ,
  `nombre` VARCHAR(40) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL ,
  `capacidad` INTEGER,
  `presupuesto` FLOAT,
  PRIMARY KEY (`codigo`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `centrosEducacionInfantil`.`educador_infantil`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `centrosEducacionInfantil`.`educador_infantil` (
  `dni` VARCHAR(9) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci',
  `nombre` VARCHAR(30) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL ,
  `apellidos` VARCHAR(70) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL ,
  `fecha_nacimiento` DATE NULL DEFAULT NULL ,
  `fecha_alta` DATE NULL DEFAULT NULL ,
  `salario` FLOAT NULL DEFAULT NULL ,
  `codigo_guarderia` VARCHAR(5) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  PRIMARY KEY (`dni`) ,
  CONSTRAINT `edu_cod_gua`
    FOREIGN KEY (`codigo_guarderia`)
    REFERENCES `centrosEducacionInfantil`.`guarderia` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- -----------------------------------------------------
-- Inserta los guarderias
-- -----------------------------------------------------
INSERT INTO guarderia VALUES
('ABC01','Centro Aventura', 50, 20000.00),
('ABC02','Los arrecifes', 70, 45000.00),
('CDE03','Barcos de Papel', 30, 15000.00),
('DFG04','La casita de Noa', 40, 40000.00);

-- -----------------------------------------------------
-- Inserta los educadores_infantiles
-- -----------------------------------------------------
INSERT INTO educador_infantil VALUES
('11111111A','PEPE','LOPEZ MARIN',19840302, 20100301, 35000.00, 'ABC01'),
('22222222B','LOLA','PEREZ PEREZ',19880101, 20100301, 32000.00, 'ABC01'),
('33333333C','REMEDIOS','COSTA RUBIO',19960303, 20150101, 25000.00, 'ABC01'),
('44444444D','LAURA','GOMEZ GOMEZ',19780120, 20100801, 40000.00, 'ABC02'),
('55555555F','JUAN','LOPEZ LOPEZ',19880501, 20080503, 25000.00, 'ABC02'),
('66666666G','ANA','MARIN MARIN',20000302, 20200901, 18000.00, 'ABC02'),
('77777777H','CARMEN','CASAS CAVA',19750925, 20000508, 35000.00, 'CDE03'),
('88888888J','MARCOS','LUNA PIO',19890815, 20100101, 38000.00, 'CDE03'),
('99999999K','JUANA','NAVARRO MARIN',19980405, 20150406, 29500.00, 'DFG04'),
('10101010L','ELISA','LOPEZ FUENTES',19900302, 20150704, 35000.00, 'DFG04');