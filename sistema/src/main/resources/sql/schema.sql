-- Configuração inicial
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS meubanco CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE meubanco;

-- 1. Usuários e autenticação
CREATE TABLE IF NOT EXISTS app_user (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(255),
    cpf_cnpj VARCHAR(30),
    phone VARCHAR(50),
    user_type ENUM('CLIENT','PROVIDER','ADMIN') NOT NULL DEFAULT 'CLIENT',
    profile_public BOOLEAN NOT NULL DEFAULT FALSE,
    average_price DECIMAL(12,2) DEFAULT NULL,
    portfolio_url VARCHAR(1000) DEFAULT NULL,
    security_question VARCHAR(255) DEFAULT NULL,
    security_answer_hash VARCHAR(255) DEFAULT NULL,
    enabled TINYINT(1) NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. Categorias e associação prestador->categoria
CREATE TABLE IF NOT EXISTS service_category (
                                                id INT AUTO_INCREMENT PRIMARY KEY,
                                                name VARCHAR(150) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS provider_category (
                                                 provider_id BIGINT NOT NULL,
                                                 category_id INT NOT NULL,
                                                 PRIMARY KEY (provider_id, category_id),
    CONSTRAINT fk_provcat_provider FOREIGN KEY (provider_id) REFERENCES app_user(id) ON DELETE CASCADE,
    CONSTRAINT fk_provcat_category FOREIGN KEY (category_id) REFERENCES service_category(id) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Serviços (ofertas do prestador)
CREATE TABLE IF NOT EXISTS service (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       provider_id BIGINT NOT NULL,
                                       title VARCHAR(255) NOT NULL,
    description TEXT,
    duration_minutes INT NOT NULL DEFAULT 60,
    base_price DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    active TINYINT(1) NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_service_provider FOREIGN KEY (provider_id) REFERENCES app_user(id) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. Endereços
CREATE TABLE IF NOT EXISTS address (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       user_id BIGINT NOT NULL,
                                       label VARCHAR(100),
    street VARCHAR(255),
    number VARCHAR(50),
    complement VARCHAR(255),
    neighborhood VARCHAR(255),
    city VARCHAR(150),
    state VARCHAR(100),
    postal_code VARCHAR(30),
    country VARCHAR(100) DEFAULT 'Brasil',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_address_user FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 5. Contratações (fluxo)
CREATE TABLE IF NOT EXISTS contratacao (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           cliente_id BIGINT NOT NULL,
                                           prestador_id BIGINT NOT NULL,
                                           service_id BIGINT NULL,
                                           requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           scheduled_start DATETIME NULL,
                                           scheduled_end DATETIME NULL,
                                           status ENUM('PENDENTE','CONFIRMADA','EM_ANDAMENTO','CONCLUIDA','CANCELADA') NOT NULL DEFAULT 'PENDENTE',
    cliente_confirmed BOOLEAN DEFAULT FALSE,
    prestador_confirmed BOOLEAN DEFAULT FALSE,
    price DECIMAL(12,2) DEFAULT NULL,
    notes TEXT,
    CONSTRAINT fk_contratacao_cliente FOREIGN KEY (cliente_id) REFERENCES app_user(id) ON DELETE SET NULL,
    CONSTRAINT fk_contratacao_prestador FOREIGN KEY (prestador_id) REFERENCES app_user(id) ON DELETE SET NULL,
    CONSTRAINT fk_contratacao_service FOREIGN KEY (service_id) REFERENCES service(id) ON DELETE SET NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 6. Avaliações
CREATE TABLE IF NOT EXISTS avaliacao (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         contratacao_id BIGINT NOT NULL,
                                         reviewer_id BIGINT NOT NULL,
                                         rating TINYINT NOT NULL,
                                         comment TEXT,
                                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         CONSTRAINT fk_avaliacao_contratacao FOREIGN KEY (contratacao_id) REFERENCES contratacao(id) ON DELETE CASCADE,
    CONSTRAINT fk_avaliacao_user FOREIGN KEY (reviewer_id) REFERENCES app_user(id) ON DELETE SET NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 7. Mensagens
CREATE TABLE IF NOT EXISTS message_thread (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              cliente_id BIGINT NOT NULL,
                                              prestador_id BIGINT NOT NULL,
                                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                              CONSTRAINT fk_thread_cliente FOREIGN KEY (cliente_id) REFERENCES app_user(id) ON DELETE CASCADE,
    CONSTRAINT fk_thread_prestador FOREIGN KEY (prestador_id) REFERENCES app_user(id) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS message (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       thread_id BIGINT NOT NULL,
                                       sender_id BIGINT NOT NULL,
                                       content TEXT NOT NULL,
                                       sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       read_at TIMESTAMP NULL,
                                       CONSTRAINT fk_message_thread FOREIGN KEY (thread_id) REFERENCES message_thread(id) ON DELETE CASCADE,
    CONSTRAINT fk_message_sender FOREIGN KEY (sender_id) REFERENCES app_user(id) ON DELETE SET NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 8. Disponibilidade do prestador
CREATE TABLE IF NOT EXISTS provider_availability (
                                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                     provider_id BIGINT NOT NULL,
                                                     weekday TINYINT NOT NULL,
                                                     start_time TIME NOT NULL,
                                                     end_time TIME NOT NULL,
                                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                     CONSTRAINT fk_avail_provider FOREIGN KEY (provider_id) REFERENCES app_user(id) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 9. Portfólio
CREATE TABLE IF NOT EXISTS portfolio_item (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              provider_id BIGINT NOT NULL,
                                              title VARCHAR(255),
    description TEXT,
    file_url VARCHAR(1000),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_portfolio_provider FOREIGN KEY (provider_id) REFERENCES app_user(id) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 10. Recuperação de senha
CREATE TABLE IF NOT EXISTS password_reset_request (
                                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                      user_id BIGINT NOT NULL,
                                                      token VARCHAR(255) NOT NULL UNIQUE,
    expires_at DATETIME NOT NULL,
    used BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pr_user FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Índices (MySQL não aceita IF NOT EXISTS em CREATE INDEX)
CREATE INDEX idx_user_type ON app_user(user_type);
CREATE INDEX idx_user_email ON app_user(email);
CREATE INDEX idx_service_provider ON service(provider_id);
CREATE INDEX idx_contratacao_cliente ON contratacao(cliente_id);
CREATE INDEX idx_contratacao_prestador ON contratacao(prestador_id);
CREATE INDEX idx_avaliacao_contratacao ON avaliacao(contratacao_id);
CREATE INDEX idx_thread_participants ON message_thread(cliente_id, prestador_id);

SET FOREIGN_KEY_CHECKS = 1;

-- Dados de exemplo mínimos (substitua HASH_PLACEHOLDER por hash real)
INSERT IGNORE INTO service_category (name, description) VALUES
  ('TI / Suporte','Serviços de suporte e pequenos projetos de TI'),
  ('Desenvolvimento Web','Criação e manutenção de sites e sistemas web'),
  ('Aulas Particulares','Aulas e reforço em tecnologia');

INSERT IGNORE INTO app_user (username, email, password_hash, full_name, cpf_cnpj, phone, user_type, profile_public)
VALUES
  ('admin','admin@local','HASH_PLACEHOLDER','Administrador','00000000000','', 'ADMIN', TRUE),
  ('joao','joao@cliente.local','HASH_PLACEHOLDER','João Cliente','11111111111','+55 21 99999-0001','CLIENT', FALSE),
  ('maria','maria@prestador.local','HASH_PLACEHOLDER','Maria Prestadora','22222222222','+55 21 98888-0002','PROVIDER', TRUE);

-- associar Maria a categoria TI
INSERT IGNORE INTO provider_category (provider_id, category_id)
SELECT p.id, c.id FROM app_user p JOIN service_category c ON c.name='TI / Suporte' WHERE p.username='maria' LIMIT 1;

-- serviço exemplo
INSERT IGNORE INTO service (provider_id, title, description, duration_minutes, base_price)
SELECT p.id, 'Suporte remoto 1h', 'Atendimento remoto para resolução de problemas e configuração', 60, 80.00
FROM app_user p WHERE p.username='maria' LIMIT 1;

-- endereço exemplo para João
INSERT IGNORE INTO address (user_id, label, street, number, neighborhood, city, state, postal_code)
SELECT id, 'Casa', 'Rua Exemplo', '123', 'Bairro', 'Seropédica', 'RJ', '23890-000' FROM app_user WHERE username='joao' LIMIT 1;
