(: Sustituir el valor del nodo kilómetros a 110000 en el producto cuya matrícula=6666FFF. :)
(:let $v := collection(databaseName)//vehiculo[matricula = "6666FFF"]
return concat("Kilómetros antes: ", $v/kilometros/text()):)

let $v := collection(databaseName)//vehiculo[matricula = "6666FFF"]
return replace node $v/kilometros/text() with 110000

(:let $v := collection(databaseName)//vehiculo[matricula = "6666FFF"]
return concat("Kilómetros después: ", $v/kilometros/text()):)
