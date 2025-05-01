(: Nombre y apellidos de todos los mecánicos de vehículos sin duplicados. :)
for $mecanico in distinct-values(
  for $r in collection(databaseName)//reparacion
  return concat($r/mecanico/nombre, ' ', $r/mecanico/apellidos)
)
return concat('Mecánico/a: ', $mecanico)