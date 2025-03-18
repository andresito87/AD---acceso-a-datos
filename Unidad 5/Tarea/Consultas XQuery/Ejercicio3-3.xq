(: Inserta una nueva reparación con los siguientes datos: matrícula:3333CCC,  inicio:02/03/2025, mecánico: Lucas García -950456987. :)

let $entrada := parse-xml(
  string-join(
    (
    "<entrada>",
    "<matricula>3333CCC</matricula>",
    "<reparacion>",
    "<inicio>2025-03-02</inicio>",
    "<mecanico>",
    "<nombre>Lucas</nombre>",
    "<apellidos>Garcia</apellidos>",
    "<telefono>950456987</telefono>",
    "</mecanico>",
    "</reparacion>",
    "</entrada>"
), "&#10;"
  )
)

let $reparaciones := collection(
  databaseName
)//reparaciones
return (
    insert node $entrada into $reparaciones,
    insert node text {
     "&#10;" (: coloca un salto de línea al final del nodo :)
  } into $reparaciones
)
