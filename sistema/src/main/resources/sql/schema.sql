CREATE DATABASE IF NOT EXISTS meu_sobrinho;
USE meu_sobrinho;

CREATE TABLE Usuario (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nomeCompleto VARCHAR(200) NOT NULL,
                         email VARCHAR(150) NOT NULL UNIQUE,
                         senha VARCHAR(100) NOT NULL,
                         perguntaSeguranca VARCHAR(150),
                         respostaSeguranca VARCHAR(150),
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
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             dataContratacao DATE NOT NULL,
                             status ENUM('PENDENTE','ACEITA','CONCLUIDA','RECUSADA','CONTRAPROPOSTA') NOT NULL,
                             prestadorId INT NOT NULL,
                             clienteId INT NOT NULL,
                             mensagemContraproposta VARCHAR(255),
                             preco DOUBLE,
                             descricao VARCHAR(255),
                             valorContraproposta DOUBLE,
                             FOREIGN KEY (prestadorId) REFERENCES Prestador(id),
                             FOREIGN KEY (clienteId) REFERENCES Cliente(id)
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
-- Tabela Servico (se ainda não existir)
CREATE TABLE Servico (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         descricao VARCHAR(255),
                         valor DOUBLE,
                         prestadorId INT,
                         FOREIGN KEY (prestadorId) REFERENCES Prestador(id)
) ENGINE=InnoDB;
