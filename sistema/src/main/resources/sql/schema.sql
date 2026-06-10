-- Tabela Usuario
CREATE TABLE Usuario (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nomeCompleto VARCHAR(200) NOT NULL,
                         email VARCHAR(150) NOT NULL UNIQUE,
                         senha VARCHAR(100) NOT NULL,
                         perguntaSeguranca VARCHAR(150),
                         respostaSeguranca VARCHAR(150),
                         fotoPerfil VARCHAR(255)
) ENGINE=InnoDB;

-- Tabela Cliente
CREATE TABLE Cliente (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         endereco VARCHAR(200),
                         telefone VARCHAR(20)
) ENGINE=InnoDB;

-- Tabela Prestador
CREATE TABLE Prestador (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           especialidade VARCHAR(100),
                           experiencia VARCHAR(200),
                           categoriaId BIGINT,
                           FOREIGN KEY (categoriaId) REFERENCES Categoria(id)
) ENGINE=InnoDB;

-- Tabela Categoria
CREATE TABLE Categoria (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nome VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

-- Tabela Contratacao
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

-- Tabela Avaliacao
CREATE TABLE Avaliacao (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nota INT NOT NULL,
                           comentario VARCHAR(255),
                           dataAvaliacao DATE,
                           clienteId INT,
                           prestadorId INT,
                           contratacaoId INT,
                           FOREIGN KEY (clienteId) REFERENCES Cliente(id),
                           FOREIGN KEY (prestadorId) REFERENCES Prestador(id),
                           FOREIGN KEY (contratacaoId) REFERENCES Contratacao(id)
) ENGINE=InnoDB;

-- Tabela Servico (se ainda não existir)
CREATE TABLE Servico (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         descricao VARCHAR(255),
                         valor DOUBLE,
                         prestadorId INT,
                         FOREIGN KEY (prestadorId) REFERENCES Prestador(id)
) ENGINE=InnoDB;
