(: cambiar el nombre del nodo <edificio> de cada
documento de la colecci�n Aulas por <lugar>:)
for $x in collection("/Aulas/*")//aula/edificio
return
rename node $x as "lugar"