DROP DATABASE IF EXISTS matriculas;

CREATE DATABASE IF NOT EXISTS matriculas;
USE matriculas;

CREATE TABLE IF NOT EXISTS UNIVERSIDAD(
codigo INTEGER,
nombre VARCHAR(40),
provincia_uni VARCHAR(30),
privada BOOLEAN,
PRIMARY KEY (codigo)
);

CREATE TABLE IF NOT EXISTS ESTUDIANTE(
NIF VARCHAR(9),
nombre VARCHAR(30),
apellidos VARCHAR(70),
fecha_nacimiento DATE,
direccion VARCHAR(50),
provincia VARCHAR(30),
importe_matricula FLOAT(6,2),
becado BOOLEAN,
codigo_uni INTEGER,
PRIMARY KEY (NIF),
  CONSTRAINT fk_est_cod
    FOREIGN KEY (codigo_uni)
    REFERENCES UNIVERSIDAD (codigo)
);