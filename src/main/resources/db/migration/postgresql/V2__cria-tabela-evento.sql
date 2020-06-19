CREATE TABLE evento (
	id bigserial PRIMARY KEY,
	id_usuario bigint NOT NULL,
	level varchar(10) NOT NULL,
	descricao varchar(500) NOT NULL,
	log text NOT NULL,
	origem varchar(100) NOT NULL,
	data date NOT NULL,
	quantidade integer NOT NULL CONSTRAINT chk_quantidade_positivo CHECK (quantidade > 0),
	foreign key(id_usuario) references usuario(id)
);