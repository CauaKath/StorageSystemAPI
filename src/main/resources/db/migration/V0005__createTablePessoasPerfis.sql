CREATE TABLE pessoas_perfis (
    id INT NOT NULL AUTO_INCREMENT,
    pessoas_codigo INT NOT NULL,
    perfis_nome VARCHAR(50) NOT NULL,

    PRIMARY KEY(id)
);

ALTER TABLE pessoas_perfis ADD CONSTRAINT fk_pessoas_codigo
FOREIGN KEY(pessoas_codigo) REFERENCES pessoas(codigo);

ALTER TABLE pessoas_perfis ADD CONSTRAINT fk_perfis_nome
FOREIGN KEY(perfis_nome) REFERENCES perfis(nome);