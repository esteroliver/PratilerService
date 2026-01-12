CREATE SCHEMA social;

CREATE TABLE social.leitura(
    id BIGINT PRIMARY KEY ,
    leitor_id BIGINT NOT NULL REFERENCES pessoa.leitor(id) ,
    livro_id BIGINT NOT NULL REFERENCES biblioteca.livro(id) ,
    status_leitura VARCHAR NOT NULL
);

CREATE TABLE social.comentario(
    id BIGINT PRIMARY KEY ,
    pagina_final INTEGER NOT NULL ,
    data_hora TIMESTAMP NOT NULL ,
    conteudo TEXT ,
    leitura_id BIGINT NOT NULL REFERENCES social.leitura
);

CREATE TABLE social.avaliacao(
    id BIGINT PRIMARY KEY ,
    nota SMALLINT NOT NULL ,
    conteudo TEXT ,
    leitura_id BIGINT NOT NULL REFERENCES social.leitura
);

