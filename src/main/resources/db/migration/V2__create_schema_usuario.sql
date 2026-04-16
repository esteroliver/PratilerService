CREATE SCHEMA usuario;

CREATE TABLE usuario.usuario(
    id BIGINT PRIMARY KEY ,
    email VARCHAR NOT NULL UNIQUE ,
    senha VARCHAR NOT NULL ,
    papel VARCHAR NOT NULL 
);

CREATE TABLE usuario.leitor(
    id BIGINT PRIMARY KEY ,
    nome VARCHAR NOT NULL ,
    biografia TEXT ,
    usuario_id BIGINT UNIQUE NOT NULL REFERENCES usuario.usuario(id)
);