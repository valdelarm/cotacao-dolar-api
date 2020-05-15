CREATE TABLE IF NOT EXISTS cotacao (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    tempo_requisicao TIMESTAMP NOT NULL,
    data_cotacao DATE NOT NULL,
    valor_compra DECIMAL(2,4) NOT NULL,
    valor_venda DECIMAL(2,4) NOT NULL,
    PRIMARY KEY (id)
);