-- --------------------------------------------------------
-- Base de Dados: Reserva Segura
-- SGBD: MySQL
-- Descrição: Script de criação e povoamento da base de dados.
-- --------------------------------------------------------

CREATE DATABASE IF NOT EXISTS reserva_segura;
USE reserva_segura;

-- --------------------------------------------------------
-- 1. TABELA: usuario
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario (
    id VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    username VARCHAR(255) UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    data_nascimento DATE,
    nivel_xp_total INT NOT NULL DEFAULT 0,
    pontos_premio INT NOT NULL DEFAULT 0,
    liga_atual_id INT,
    criado_em DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- --------------------------------------------------------
-- 2. TABELA: meta_reserva
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS meta_reserva (
    id VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(45),
    valor_alvo DOUBLE NOT NULL,
    valor_atual DOUBLE NOT NULL DEFAULT 0.0,
    criado_em DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

-- --------------------------------------------------------
-- 3. TABELA: missao
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS missao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(120) NOT NULL,
    descricao TEXT,
    tipo VARCHAR(255),
    xp_recompensa INT NOT NULL,
    pontos_premio INT NOT NULL,
    ativa BOOLEAN NOT NULL DEFAULT TRUE,
    periodo VARCHAR(255)
);

-- --------------------------------------------------------
-- 4. TABELA: progresso_missao
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS progresso_missao (
    id VARCHAR(36) PRIMARY KEY,
    usuario_id VARCHAR(36) NOT NULL,
    missao_id INT NOT NULL,
    progresso_atual INT NOT NULL DEFAULT 0,
    meta_progresso INT NOT NULL DEFAULT 1,
    concluida BOOLEAN NOT NULL DEFAULT FALSE,
    concluida_em DATETIME,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (missao_id) REFERENCES missao(id) ON DELETE CASCADE
);

-- --------------------------------------------------------
-- 5. TABELA: movimentacoes
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS movimentacoes (
    id VARCHAR(36) PRIMARY KEY,
    valor DOUBLE NOT NULL,
    tipo VARCHAR(45) NOT NULL, -- 'deposito' ou 'saque'
    criado_em DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    meta_reserva_id VARCHAR(36),
    usuario_id VARCHAR(36) NOT NULL,
    status VARCHAR(45) NOT NULL DEFAULT 'pendente', -- 'pendente', 'concluida', 'cancelada'
    processado_em DATETIME,
    FOREIGN KEY (meta_reserva_id) REFERENCES meta_reserva(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

-- ========================================================
-- POVOAMENTO DA BASE DE DADOS (INSERTS)
-- ========================================================

-- Inserir Utilizadores
INSERT INTO usuario (id, nome, email, cpf, username, senha_hash, data_nascimento, nivel_xp_total, pontos_premio, criado_em, atualizado_em) VALUES
(UUID(), 'Allan Falcão', 'allan@email.com', '111.222.333-44', 'allanf', '$2a$12$TC0xJnDi9bUPze7fNvMDC.h/8HfM5FAEGEZi.MVr.HIW.TwcCB5zS', '1995-05-10', 2450, 1850, NOW(), NOW()),
(UUID(), 'Maria Silva', 'maria@email.com', '555.666.777-88', 'mariasilva', '$2a$12$TC0xJnDi9bUPze7fNvMDC.h/8HfM5FAEGEZi.MVr.HIW.TwcCB5zS', '1990-12-20', 500, 250, NOW(), NOW()),
(UUID(), 'João Costa', 'joao@email.com', '999.888.777-66', 'joaoc', '$2a$12$TC0xJnDi9bUPze7fNvMDC.h/8HfM5FAEGEZi.MVr.HIW.TwcCB5zS', '1985-08-15', 1200, 600, NOW(), NOW());

-- Capturar os IDs gerados para usar nas outras tabelas
SET @user1_id = (SELECT id FROM usuario WHERE email = 'allan@email.com');
SET @user2_id = (SELECT id FROM usuario WHERE email = 'maria@email.com');
SET @user3_id = (SELECT id FROM usuario WHERE email = 'joao@email.com');

-- Inserir Missões
INSERT INTO missao (titulo, descricao, tipo, xp_recompensa, pontos_premio, ativa, periodo) VALUES
('Primeira Poupança', 'Guarde o seu primeiro valor numa caixinha', 'deposito', 100, 50, TRUE, 'sempre'),
('Disciplina de Ferro', 'Poupe durante 7 dias seguidos', 'disciplina', 500, 200, TRUE, 'semanal'),
('Grande Meta', 'Alcance 10.000 de saldo', 'acumulo', 1000, 500, TRUE, 'mensal');

-- Inserir Metas (Caixinhas)
INSERT INTO meta_reserva (id, nome, valor_alvo, valor_atual, usuario_id) VALUES
(UUID(), 'Viagem a Portugal', 15000.0, 5000.0, @user1_id),
(UUID(), 'Fundo de Emergência', 10000.0, 10000.0, @user1_id),
(UUID(), 'Comprar Carro', 40000.0, 2500.0, @user2_id),
(UUID(), 'Curso de Inglês', 3000.0, 500.0, @user3_id);

SET @meta1_id = (SELECT id FROM meta_reserva WHERE nome = 'Viagem a Portugal');
SET @meta2_id = (SELECT id FROM meta_reserva WHERE nome = 'Fundo de Emergência');
SET @meta3_id = (SELECT id FROM meta_reserva WHERE nome = 'Comprar Carro');
SET @meta4_id = (SELECT id FROM meta_reserva WHERE nome = 'Curso de Inglês');

-- Inserir Progresso de Missões
INSERT INTO progresso_missao (id, usuario_id, missao_id, progresso_atual, meta_progresso, concluida, concluida_em) VALUES
-- Allan concluiu duas missões
(UUID(), @user1_id, 1, 1, 1, TRUE, NOW()),
(UUID(), @user1_id, 2, 7, 7, TRUE, NOW()),
-- Maria completou a primeira mas ainda está a tentar a Grande Meta
(UUID(), @user2_id, 1, 1, 1, TRUE, NOW()),
(UUID(), @user2_id, 3, 2500, 10000, FALSE, NULL),
-- João começou agora
(UUID(), @user3_id, 1, 0, 1, FALSE, NULL);

-- Inserir Movimentações (Transações)
INSERT INTO movimentacoes (id, valor, tipo, status, meta_reserva_id, usuario_id, criado_em, processado_em) VALUES
(UUID(), 3000.0, 'deposito', 'concluida', @meta1_id, @user1_id, '2026-09-01 10:00:00', '2026-09-01 10:05:00'),
(UUID(), 2000.0, 'deposito', 'concluida', @meta1_id, @user1_id, NOW(), NOW()),
(UUID(), 10000.0, 'deposito', 'concluida', @meta2_id, @user1_id, '2026-01-15 14:00:00', '2026-01-15 14:00:00'),
(UUID(), 2500.0, 'deposito', 'concluida', @meta3_id, @user2_id, NOW(), NOW()),
(UUID(), 1000.0, 'deposito', 'concluida', @meta4_id, @user3_id, NOW(), NOW()),
(UUID(), 500.0, 'saque', 'concluida', @meta4_id, @user3_id, NOW(), NOW());
