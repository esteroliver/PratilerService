CREATE SCHEMA pessoa;

CREATE TABLE pessoa.usuario(
    id BIGINT PRIMARY KEY ,
    email VARCHAR NOT NULL ,
    senha VARCHAR NOT NULL
);

CREATE TABLE pessoa.leitor(
    id BIGINT PRIMARY KEY ,
    nome VARCHAR NOT NULL ,
    biografia VARCHAR ,
    usuario_id BIGINT UNIQUE NOT NULL REFERENCES pessoa.usuario(id)
);