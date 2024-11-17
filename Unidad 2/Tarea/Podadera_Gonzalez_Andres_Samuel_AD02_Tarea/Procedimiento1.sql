use centroseducacioninfantil;
DELIMITER //
CREATE PROCEDURE Guarderias_Con_Maxima_Capacidad(
	IN capacidadLimite int,
    OUT cantidadGuarderias int
    )
BEGIN
	SELECT count(*) INTO cantidadGuarderias
    FROM guarderia
    where capacidad > capacidadLimite;
END //
DELIMITER ;