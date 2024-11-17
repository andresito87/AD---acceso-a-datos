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