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
    where salario between minIntervalo and maxIntervalo;
END //
DELIMITER ;