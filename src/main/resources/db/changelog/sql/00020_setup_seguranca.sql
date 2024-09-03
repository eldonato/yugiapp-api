--liquidbase formatted sql
--changeset Donato:Criando coluna de usuário e permissão, adicionando ligacao de torneio com usuario
create table Usuario (
	id UUID primary key,
	id_pessoa int,
	username varchar(30) not null,
	senha varchar(250) not null
);
create table Permissao(
    id serial primary key,
    descricao varchar(100) not null
);
create table Permissao_Usuario(
    id serial primary key,
    id_usuario UUID not null,
    id_permissao int not null
);
alter table torneio add id_usuario UUID;

--changeset Donato:configurando chaves estrangeiras de usuario e permissao_usuario
alter table usuario add constraint fk_pessoa foreign key (id_pessoa) references pessoa(id);
alter table torneio add constraint fk_usuario foreign key (id_usuario) references usuario(id);
alter table permissao_usuario add constraint fk_usuario foreign key (id_usuario) references usuario(id);
alter table permissao_usuario add constraint fk_permissao foreign key (id_permissao) references permissao(id);

--changeset Donato:populando tabela de permissoes
insert into permissao (descricao) values ('ADMIN');
insert into permissao (descricao) values ('ORGANIZADOR');
