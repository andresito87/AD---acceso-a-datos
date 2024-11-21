-- Alumno: Andrés Samuel Podadera González
-- Ciclo: Desarrollo de aplicaciones multiplataforma
-- Módulo: Acceso a datos
-- Curso: 2024/2025
-- Prodecimento 3: Este procedimiento incrementa el salario de los educadores de una guardería en un porcentaje dado.
use centroseducacioninfantil;
DELIMITER //
CREATE PROCEDURE Incremento_Salarial_Porcentaje(
	IN codigo_guarderia_p varchar(10),
    IN porcentaje int
    )
BEGIN
	UPDATE educador_infantil
	SET salario = salario + (salario * (porcentaje / 100))
	WHERE codigo_guarderia=codigo_guarderia_p;
END //
DELIMITER ;