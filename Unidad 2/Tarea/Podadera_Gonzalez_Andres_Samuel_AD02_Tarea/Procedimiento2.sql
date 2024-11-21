-- Alumno: Andrés Samuel Podadera González
-- Ciclo: Desarrollo de aplicaciones multiplataforma
-- Módulo: Acceso a datos
-- Curso: 2024/2025
-- Prodecimento 2: Este procedimiento obtiene la cantidad de educadores con un salario dentro de un intervalo específico.
use centroseducacioninfantil;
DELIMITER //
CREATE PROCEDURE Educadores_Infantiles(
	IN minIntervalo int,
    IN maxIntervalo int,
    OUT cantidadEducadores int
    )
BEGIN
	SELECT count(*) INTO cantidadEducadores
    FROM educador_infantil
    WHERE salario BETWEEN minIntervalo AND maxIntervalo;
END //
DELIMITER ;