CREATE DATABASE IF NOT EXISTS meu_sobrinho;
USE meu_sobrinho;

CREATE TABLE Usuario (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nomeCompleto VARCHAR(200) NOT NULL,
                         email VARCHAR(150) NOT NULL UNIQUE,
                         senha VARCHAR(255) NOT NULL,
                         fotoPerfil VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE Categoria (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nome VARCHAR(100) NOT NULL,
                           descricao VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE Cliente (
                         id BIGINT PRIMARY KEY,
                         cpf VARCHAR(20) NOT NULL,
                         FOREIGN KEY (id) REFERENCES Usuario(id)
) ENGINE=InnoDB;

CREATE TABLE Prestador (
                           id BIGINT PRIMARY KEY,
                           descricao TEXT NOT NULL,
                           valorMedio DECIMAL(10,2) DEFAULT 0.00,
                           perfilPublico BOOLEAN NOT NULL DEFAULT TRUE,
                           portfolio TEXT,
                           cpfCnpj VARCHAR(20) NOT NULL,
                           celular VARCHAR(20) NOT NULL,
                           categoriaId BIGINT NOT NULL,
                           FOREIGN KEY (id) REFERENCES Usuario(id),
                           FOREIGN KEY (categoriaId) REFERENCES Categoria(id)
) ENGINE=InnoDB;

CREATE TABLE Contratacao (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             clienteId BIGINT NOT NULL,
                             prestadorId BIGINT NOT NULL,
                             descricao TEXT NOT NULL,
                             preco DECIMAL(10,2),
                             dataSolicitada DATE,
                             status ENUM(
        'PENDENTE',
        'ACEITA',
        'RECUSADA',
        'CONTRAPROPOSTA',
        'CONCLUIDA'
    ) NOT NULL DEFAULT 'PENDENTE',
                             valorContraproposta DECIMAL(10,2),
                             mensagemContraproposta TEXT,
                             FOREIGN KEY (clienteId) REFERENCES Cliente(id),
                             FOREIGN KEY (prestadorId) REFERENCES Prestador(id)
) ENGINE=InnoDB;

CREATE TABLE Avaliacao (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           contratacaoId BIGINT NOT NULL UNIQUE,
                           clienteId BIGINT NOT NULL,
                           prestadorId BIGINT NOT NULL,
                           nota INT NOT NULL,
                           comentario TEXT,
                           dataAvaliacao DATE,
                           FOREIGN KEY (contratacaoId) REFERENCES Contratacao(id),
                           FOREIGN KEY (clienteId) REFERENCES Cliente(id),
                           FOREIGN KEY (prestadorId) REFERENCES Prestador(id),
                           CHECK (nota >= 1 AND nota <= 5)
) ENGINE=InnoDB;