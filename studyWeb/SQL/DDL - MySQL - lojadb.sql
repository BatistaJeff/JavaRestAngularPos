create database lojadb;


use lojadb;


create table clientes(
codigo int not null auto_increment primary key,
nome varchar(50) not null,
inscricao_federal char(16)
);


create table pedidos (
numero_pedido int not null auto_increment primary key,
data_pedido date not null,
codigo_cliente int not null references clientes (codigo)
);



create table produtos(
codigo_produto char(10) not null primary key,
nome_produto varchar(50) not null
);


create table itens_pedidos(
numero_pedido int not null,
codigo_produto char(10) not null,
quantidade numeric(10,2) not null,
valor_unitario numeric(10,2) not null,
valor_total numeric(10,2) not null,
primary key (numero_pedido, codigo_produto),
foreign key (numero_pedido) references pedidos(numero_pedido),
foreign key (codigo_produto) references produtos(codigo_produto)
);


/*
drop table itens_pedidos;
drop table produtos;
drop table pedidos;
drop table clientes;
*/
