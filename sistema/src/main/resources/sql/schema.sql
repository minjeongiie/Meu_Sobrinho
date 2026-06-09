-- Criar banco de dados
CREATE DATABASE meusobrinho;
USE meusobrinho;

-- Tabela de usuários (base para cliente e prestador)
CREATE TABLE Usuario (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nomeCompleto VARCHAR(150) NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         senha VARCHAR(255) NOT NULL,
                         telefone VARCHAR(20), -- novo campo
                         perguntaSeguranca VARCHAR(150),
                         respostaSeguranca VARCHAR(150),
                         dataCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de clientes
CREATE TABLE Cliente (
                         id INT PRIMARY KEY,
                         endereco VARCHAR(200),
                         telefone VARCHAR(20),
                         FOREIGN KEY (id) REFERENCES Usuario(id) ON DELETE CASCADE
);

-- Tabela de prestadores
CREATE TABLE Prestador (
                           id INT PRIMARY KEY,
                           especialidade VARCHAR(100),
                           experiencia VARCHAR(200),
                           FOREIGN KEY (id) REFERENCES Usuario(id) ON DELETE CASCADE
);

-- Tabela de serviços
CREATE TABLE Servico (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         titulo VARCHAR(100) NOT NULL,
                         descricao TEXT,
                         cliente_id INT NOT NULL,
                         prestador_id INT,
                         status ENUM('Aberto','Em andamento','Concluído') DEFAULT 'Aberto',
                         dataCriacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE CASCADE,
                         FOREIGN KEY (prestador_id) REFERENCES Prestador(id) ON DELETE SET NULL
);

-- Tabela de avaliações
CREATE TABLE Avaliacao (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           servico_id INT NOT NULL,
                           cliente_id INT NOT NULL,
                           prestador_id INT NOT NULL,
                           nota INT CHECK (nota BETWEEN 1 AND 5),
                           comentario TEXT,
                           dataAvaliacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (servico_id) REFERENCES Servico(id) ON DELETE CASCADE,
                           FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE CASCADE,
                           FOREIGN KEY (prestador_id) REFERENCES Prestador(id) ON DELETE CASCADE
);
