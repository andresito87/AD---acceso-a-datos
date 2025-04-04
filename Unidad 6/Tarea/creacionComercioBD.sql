CREATE SCHEMA IF NOT EXISTS COMERCIO;
USE COMERCIO;


create table if not exists Productos (
	referencia		char(6) primary key,
	nombre		varchar(20) not null,
	descripcion	text(1000),
	precio		float  not null,
	descuento		float not null
);


insert into Productos values ('AC0001', 'Abrigo Caballero', 'Piel Color Marrón',  120.50, 15);
insert into Productos values ('AS0001', 'Abrigo Señora', 'Piel Color Marrón',  110.75, 25);
insert into Productos values ('CC0001', 'Camisa Caballero', 'Cuadros',  35.99, 10);
insert into Productos values ('PC0001', 'Pantalón Caballero', 'Vaquero',  34.90, 35);
insert into Productos values ('PC0002', 'Pantalón Caballero', 'Pana',  25.90, 0);
insert into Productos values ('AC0002', 'Abrigo Caballero', 'Piel Color Negro',  120.50, 15);
insert into Productos values ('CC0002', 'Camisa Caballero', 'Lisa Color Blanco',  35.99, 10);
insert into Productos values ('CC0003', 'Camisa Caballero', 'Lisa Color Azul',  35.99, 10);
insert into Productos values ('AS0002', 'Abrigo Señora', 'Piel Color Negro',  120.75, 15);
insert into Productos values ('AS0003', 'Abrigo Señora', 'Ante  Color Marrón',  90.95, 35);
insert into Productos values ('PS0001', 'Pantalón Señora', 'Vaquero',  30.90, 30);
insert into Productos values ('PS0002', 'Pantalón Señora', 'Lino',  39.90, 40);

