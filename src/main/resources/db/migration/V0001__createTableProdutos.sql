CREATE TABLE produtos (
    codigo INT NOT NULL AUTO_INCREMENT,
    produto VARCHAR(150) NOT NULL,
    quantidade INT NOT NULL,
    valor_unitario DECIMAL(10, 2) NOT NULL,
    valor_total_em_estoque DECIMAL(10, 2) NOT NULL,

    PRIMARY KEY(codigo)
);