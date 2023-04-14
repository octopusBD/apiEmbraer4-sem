DELETE FROM CADASTRO;
DELETE FROM SAMPLE;
DELETE FROM USUARIO;
DELETE FROM PERMISSAO;
DELETE FROM PDF;
DELETE FROM CHASSI;
DELETE FROM BOLETIM;
DELETE FROM UPDATE_SAMPLE;


-- Tabela BOLETIM
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (1,  'SB FAT-21-CG12', 'Vidros e travas elétricas');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (2,  'SB FAT-23-CG01', 'Sistema modernizado de som');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (3,  'SB FAT-23-CG04', 'Cruise Control');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (4, 'SB FAT-23-CG06', 'Sensor de proximidade');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (5, 'SB FAT-23-CG08', 'Calha de chuva');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (6, 'SB FAT-23-CG02', 'Controle de tração');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (7, 'SB FAT-23-CG03', 'Pintura anti-corrosão');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (8, 'SB FAT-25-CG23', 'Função Economic/Sport');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (9, 'SB FAT-25-CG30', 'Bluetooth');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (10, 'SB FAT-25-CG04', 'Câmera traseira');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (11, 'SB FAT-25-CG31', 'Freio ABS');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (12, 'SB FAT-27-CG11', 'Vidros com fechamento em um toque');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (13, 'SB FAT-27-CG18', 'Partida remota');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (14, 'SB FAT-30-CG04', 'Aquecimento nos bancos');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (15, 'SB FAT-31-CG03', 'Controle de estabilidade');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (16, 'SB FAT-31-CG19', 'Roda de liga leve');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (17, 'SB FAT-31-CG02', 'Câmbio automático');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (18, 'SB FAT-34-CG23', 'Farol de neblina');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (19, 'SB FAT-23-CG05', 'Cruise CXontrol');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (20,  'SB FAT-31-CG05', 'Cruise Control');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (21,  'SB FAT-31-CG08', 'Sensor de proximidade');
INSERT INTO BOLETIM (ID_BOLETIM, BOLETIM, ITEM) VALUES (22, 'SB FAT-25-CG11', 'Câmera traseira');



-- Tabela CHASSI
INSERT INTO CHASSI (ID_CHASSI, CHASSI) VALUES (1, '10000076');
INSERT INTO CHASSI (ID_CHASSI, CHASSI) VALUES (2, '10000153');
INSERT INTO CHASSI (ID_CHASSI, CHASSI) VALUES (3, '10000218');
INSERT INTO CHASSI (ID_CHASSI, CHASSI) VALUES (4, '10000270');
INSERT INTO CHASSI (ID_CHASSI, CHASSI) VALUES (5, '10000348');
INSERT INTO CHASSI (ID_CHASSI, CHASSI) VALUES (6, '10000432');
INSERT INTO CHASSI (ID_CHASSI, CHASSI) VALUES (7, '10000437');
INSERT INTO CHASSI (ID_CHASSI, CHASSI) VALUES (8, '10000470');

-- Tabela PERMISSAO
INSERT INTO PERMISSAO (ID_PERMISSAO, PERMISSAO) VALUES (1, 'Administrador');
INSERT INTO PERMISSAO (ID_PERMISSAO, PERMISSAO) VALUES (2, 'Editor');
INSERT INTO PERMISSAO (ID_PERMISSAO, PERMISSAO) VALUES (3, 'Consultor');

-- Tabela USUARIO
INSERT INTO USUARIO (ID_USUARIO, NOME_USUARIO, SENHA_USUARIO, ID_PERMISSAO) VALUES (1, 'Aldrik Alvaro', '123', 1);
INSERT INTO USUARIO (ID_USUARIO, NOME_USUARIO, SENHA_USUARIO, ID_PERMISSAO) VALUES (2, 'Ana Leal', '321', 2);
INSERT INTO USUARIO (ID_USUARIO, NOME_USUARIO, SENHA_USUARIO, ID_PERMISSAO) VALUES (3, 'Michael Felipe', '822', 3);

-- Tabela SAMPLE
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 1,  'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 2,  'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 3,  'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 4,  'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 5,  'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 6,  'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 7,  'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 8,  'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 9,  'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 10, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 11, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 12, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 13, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 14, 'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (1, 15, 'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);

INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 11, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 12, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 13, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 14, 'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 15, 'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 16, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 17, 'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 18, 'INCORPORATED', 2, CURRENT_TIMESTAMP);


INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (3,  1, 'NOT INCORPORATED', 1, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (3,  2, 'INCORPORATED', 1, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (3,  3, 'INCORPORATED', 1, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (3,  4, 'INCORPORATED', 1, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (3,  5, 'NOT INCORPORATED', 1, CURRENT_TIMESTAMP);

INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (4, 1, 'NOT INCORPORATED', 1, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (4, 2, 'NOT INCORPORATED', 1, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (4, 3, 'INCORPORATED', 1, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (4, 4, 'INCORPORATED', 1, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (4, 5, 'NOT INCORPORATED', 1, CURRENT_TIMESTAMP);

INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (5, 6,  'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (5, 7,  'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (5, 8,  'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (5, 9,  'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (5, 10, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (5, 11, 'INCORPORATED', 2, CURRENT_TIMESTAMP);

INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (6, 11,  'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (6, 12,  'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (6, 13,  'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (6, 14,  'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (6, 15, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (6, 16, 'INCORPORATED', 2, CURRENT_TIMESTAMP);


INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI) VALUES (1, 1);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI) VALUES (1, 4);

INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI) VALUES (2, 2);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI) VALUES (2, 3);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI) VALUES (2, 1);

INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI)VALUES (3, 3);

INSERT INTO UPDATE_SAMPLE (ID_CHASSI, ID_BOLETIM, BOLETIM, MENSAGEM, ID_USUARIO_ALTERANTE, NOME_USUARIO_ALTERANTE) VALUES (2, 7, 'Partida remota', 'Partida remota available to chassi 10000153', 1, 'Aplicação');
INSERT INTO UPDATE_SAMPLE (ID_CHASSI, ID_BOLETIM, BOLETIM, MENSAGEM, ID_USUARIO_ALTERANTE, NOME_USUARIO_ALTERANTE) VALUES (2, 8, 'Aquecimento nos bancos', 'Aquecimento nos bancos available to chassi 10000153', 1, 'Aplicação');
INSERT INTO UPDATE_SAMPLE (ID_CHASSI, ID_BOLETIM, BOLETIM, MENSAGEM, ID_USUARIO_ALTERANTE, NOME_USUARIO_ALTERANTE) VALUES (2, 9, 'Controle de estabilidade', 'Controle de estabilidade available to chassi 10000153', 1, 'Aplicação');
INSERT INTO UPDATE_SAMPLE (ID_CHASSI, ID_BOLETIM, BOLETIM, MENSAGEM, ID_USUARIO_ALTERANTE, NOME_USUARIO_ALTERANTE) VALUES (2, 10, 'Roda de liga leve', 'Roda de liga leve available to chassi 10000153', 1, 'Aplicação');
INSERT INTO UPDATE_SAMPLE (ID_CHASSI, ID_BOLETIM, BOLETIM, MENSAGEM, ID_USUARIO_ALTERANTE, NOME_USUARIO_ALTERANTE) VALUES (2, 11, 'Câmbio automático', 'Câmbio automático available to chassi 10000153', 1, 'Aplicação');

INSERT INTO UPDATE_SAMPLE (ID_CHASSI, ID_BOLETIM, BOLETIM, MENSAGEM, ID_USUARIO_ALTERANTE, NOME_USUARIO_ALTERANTE) VALUES (1, 7, 'Partida remota', 'Partida remota available to chassi 10000153', 1, 'Aplicação');
INSERT INTO UPDATE_SAMPLE (ID_CHASSI, ID_BOLETIM, BOLETIM, MENSAGEM, ID_USUARIO_ALTERANTE, NOME_USUARIO_ALTERANTE) VALUES (1, 8, 'Aquecimento nos bancos', 'Aquecimento nos bancos available to chassi 10000153', 1, 'Aplicação');
INSERT INTO UPDATE_SAMPLE (ID_CHASSI, ID_BOLETIM, BOLETIM, MENSAGEM, ID_USUARIO_ALTERANTE, NOME_USUARIO_ALTERANTE) VALUES (1, 9, 'Controle de estabilidade', 'Controle de estabilidade available to chassi 10000153', 1, 'Aplicação');
INSERT INTO UPDATE_SAMPLE (ID_CHASSI, ID_BOLETIM, BOLETIM, MENSAGEM, ID_USUARIO_ALTERANTE, NOME_USUARIO_ALTERANTE) VALUES (1, 10, 'Roda de liga leve', 'Roda de liga leve available to chassi 10000153', 1, 'Aplicação');
INSERT INTO UPDATE_SAMPLE (ID_CHASSI, ID_BOLETIM, BOLETIM, MENSAGEM, ID_USUARIO_ALTERANTE, NOME_USUARIO_ALTERANTE) VALUES (1, 11, 'Câmbio automático', 'Câmbio automático available to chassi 10000153', 1, 'Aplicação');


