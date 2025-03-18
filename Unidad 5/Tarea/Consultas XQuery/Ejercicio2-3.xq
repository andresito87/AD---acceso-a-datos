(: Nombre y apellidos de los propietarios que tengan un Golf. :)
for $v in collection(databaseName)//vehiculo
let $nombre := $v/propietario/nombre/text()
let $apellidos := $v/propietario/apellidos/text()
let $modelo := $v/modelo/text()
where $modelo = 'Golf'
return concat('Propietario/a: ', $nombre, ' ', $apellidos)