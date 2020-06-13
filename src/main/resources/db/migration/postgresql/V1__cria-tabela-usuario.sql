CREATE TABLE usuario (
	id bigserial PRIMARY KEY,
	nome varchar(255) NOT NULL,
	email varchar(255) UNIQUE NOT NULL,
	senha varchar NOT NULL,
	data_criacao TIMESTAMP NOT NULL
);