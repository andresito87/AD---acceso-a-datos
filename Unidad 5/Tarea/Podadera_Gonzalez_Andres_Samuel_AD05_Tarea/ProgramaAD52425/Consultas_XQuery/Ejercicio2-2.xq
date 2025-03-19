(: Marca y modelo de los vehículos que tengan menos de 75000 kilómetros. :)
for $v in collection(databaseName)//vehiculo
let $marca := $v/marca/text()
let $modelo := $v/modelo/text()
let $kilometros := $v/kilometros/text()
where $kilometros < 75000
return concat('Marca: ', $marca,' | Modelo: ', $modelo)