CREATE SCHEMA biblioteca;

CREATE TABLE biblioteca.autor(
    id BIGINT PRIMARY KEY ,
    nome VARCHAR NOT NULL
);

CREATE TABLE biblioteca.livro(
     id BIGINT PRIMARY KEY ,
     titulo VARCHAR NOT NULL ,
     num_paginas INTEGER NOT NULL ,
     isbn VARCHAR NOT NULL UNIQUE ,
     sinopse TEXT NOT NULL,
     autor_id BIGINT NOT NULL REFERENCES biblioteca.autor(id)
);