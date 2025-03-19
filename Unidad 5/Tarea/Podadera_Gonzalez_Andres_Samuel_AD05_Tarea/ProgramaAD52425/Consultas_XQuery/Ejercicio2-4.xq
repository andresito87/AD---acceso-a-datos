(: Nombre y apellidos de todos los mecánicos de vehículos. :)
for $r in collection(databaseName)//reparacion
let $nombre := $r/mecanico/nombre/text()
let $apellidos := $r/mecanico/apellidos/text()
return concat('Mecánico/a: ', $nombre, ' ', $apellidos)