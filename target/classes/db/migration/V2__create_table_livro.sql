CREATE TABLE biblioteca.livro(
     id BIGINT PRIMARY KEY ,
     titulo VARCHAR NOT NULL ,
     num_paginas INTEGER NOT NULL ,
     isbn VARCHAR NOT NULL ,
     sinopse VARCHAR,
     autor_id BIGINT references biblioteca.autor(id)
);