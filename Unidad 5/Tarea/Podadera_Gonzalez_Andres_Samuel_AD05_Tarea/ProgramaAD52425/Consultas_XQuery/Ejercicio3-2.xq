(: Modificar el nombre del nodo 'kilometros' de cada vehículo por 'kms' :)
for $v in collection(databaseName)//vehiculo
let $kilometros := $v/kilometros/text()
let $nodoKilometro := $v/kilometros
return replace node $nodoKilometro with <kms>{$kilometros}</kms>

(: Guardamos los cambios en un archivo diferente :)
(: let $new-doc := collection(databaseName)  
return put($new-doc, "taller_kms_modificado.xml") :)

(: Revertimos los cambios de la BD en BaseX :)
(:for $v in collection(databaseName)//vehiculo
let $kilometros := $v/kms/text()
let $nodoKilometro := $v/kms
return replace node $nodoKilometro with <kilometros>{$kilometros}</kilometros>:)