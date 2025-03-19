(: Matrícula de los vehículos cuyo año de fabricación (afabricación) sea el 2024 :)
for $v in collection(databaseName)//vehiculo
let $matricula := $v/matricula/text()
where $v/@afabricacion = 2024
return concat('Matrícula: ', $matricula)
