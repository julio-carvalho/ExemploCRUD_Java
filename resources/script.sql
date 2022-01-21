create database bdclientes;

create table clientes(

id int not null auto_increment primary key,
nome varchar(50),
idade int,
dataCadastro date)