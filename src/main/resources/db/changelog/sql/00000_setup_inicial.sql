--liquidbase formatted sql
--changeset Donato:criando tabelas
create table Pessoa (
	id SERIAL primary key,
	nome varchar(100) not null
);
create table Jogador (
	id serial primary key,
	id_pessoa int not null,
	kossy varchar(10) not null
);
create table Liga (
	id SERIAL primary key,
	descricao varchar(100),
	dt_inicio DATE not null,
	dt_fim DATE not null
);
create table Torneio (
	id SERIAL primary key,
	id_liga int not null,
	id_loja int not null,
	dt_realizacao DATE not null
);
create table Loja (
	id SERIAL primary key,
	nome varchar(100) not null
);
create table Classificacao (
	id SERIAL primary key,
	id_torneio int not null,
	id_jogador int not null,
	pontuacao int not null,
	deck varchar(30)
);

--changeset Donato:Adicionando chaves estrangeiras
alter table jogador add constraint fk_pessoa foreign key (id_pessoa) references Pessoa(id);
alter table classificacao add constraint fk_jogador foreign key (id_jogador) references Jogador(id);
alter table classificacao add constraint fk_torneio foreign key (id_torneio) references Torneio(id);
alter table torneio add constraint fk_liga foreign key (id_liga) references Liga(id);
alter table torneio add constraint fk_loja foreign key (id_loja) references Loja(id);

--changeset Donato:Adicionando constraints
alter table jogador add constraint uk_jogador_kossy unique (kossy);
alter table loja add constraint uk_loja_nome unique (nome);
alter table classificacao add constraint uk_torneio_jogador unique (id_torneio, id_jogador);
