-- Alumno: Andrés Samuel Podadera González
-- Ciclo: Desarrollo de aplicaciones multiplataforma
-- Módulo: Acceso a datos
-- Curso: 2024/2025
-- Prodecimento 1: Este procedimiento obtiene la cantidad de guarderías que superan un límite de capacidad dado.
use centroseducacioninfantil;
DELIMITER //
CREATE PROCEDURE Guarderias_Con_Maxima_Capacidad(
	IN capacidadLimite int,
    OUT cantidadGuarderias int
    )
BEGIN
	SELECT count(*) INTO cantidadGuarderias
    FROM guarderia
    WHERE capacidad > capacidadLimite;
END //
DELIMITER ;