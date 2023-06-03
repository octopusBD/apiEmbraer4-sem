-- Drop das chaves estrangeiras
ALTER TABLE CADASTRO DROP CONSTRAINT FK_CADASTRO_ID_CHASSI;
ALTER TABLE CADASTRO DROP CONSTRAINT FK_CADASTRO_ID_USUARIO;
ALTER TABLE FORMULA DROP CONSTRAINT FK_FORMULA_ID_ITEM;
ALTER TABLE LOGICA DROP CONSTRAINT FK_LOGICA_ID_CHASSI;
ALTER TABLE LOGICA DROP CONSTRAINT FK_LOGICA_ID_ITEM;
ALTER TABLE SAMPLE DROP CONSTRAINT FK_SAMPLE_ID_CHASSI;
ALTER TABLE SAMPLE DROP CONSTRAINT FK_SAMPLE_ID_BOLETIM;
ALTER TABLE USUARIO DROP CONSTRAINT FK_USUARIO_ID_PERMISSAO;

-- Drop das tabelas
DROP TABLE UPDATE_SAMPLE;
DROP TABLE CADASTRO;
DROP TABLE LOGICA;
DROP TABLE SAMPLE;
DROP TABLE FORMULA;
DROP TABLE ITEM;
DROP TABLE BOLETIM;
DROP TABLE CHASSI;
DROP TABLE USUARIO;
DROP TABLE PERMISSAO;

-- Drop das sequências
DROP SEQUENCE SEQ_UPDATE_SAMPLE;
DROP SEQUENCE SEQ_CADASTRO;
DROP SEQUENCE SEQ_LOGICA;
DROP SEQUENCE SEQ_SAMPLE;
DROP SEQUENCE SEQ_FORMULA;
DROP SEQUENCE SEQ_ITEM;
DROP SEQUENCE SEQ_BOLETIM;
DROP SEQUENCE SEQ_CHASSI;
DROP SEQUENCE SEQ_USUARIO;
DROP SEQUENCE SEQ_PERMISSAO;


--drop das views
DROP VIEW VIEW_CADASTRO;
DROP VIEW VIEW_SAMPLE;
DROP VIEW VIEW_UPDATE_SAMPLE;
DROP VIEW VIEW_ESTATISTICA_DISPONIBILIDADE;
DROP VIEW VIEW_ESTATISTICA_QTD_BOLETIM;
DROP VIEW VIEW_ESTATISTICA_QTD_CONTIDO;
DROP VIEW VIEW_ESTATISTICA_STATUS;
DROP VIEW VIEW_STATUS_USUARIO;
DROP VIEW VIEW_STATUS_CHASSI;
DROP VIEW VIEW_ESTATISTICA_USUARIO;
DROP VIEW VIEW_LISTAR_USUARIOS;
DROP VIEW VIEW_PERMISSAO_USUARIO;
DROP VIEW VIEW_ITEM_CHASSI;
DROP VIEW VIEW_QTD_STATUS;
DROP VIEW VIEW_LISTAR_ITEM;
DROP VIEW VIEW_LISTAR_BOLETIM;
DROP VIEW VIEW_LISTAR_FORMULA;
DROP VIEW VIEW_LISTAR_SAMPLE;
DROP VIEW VIEW_LISTAR_LOGICA;

CREATE TABLE PERMISSAO
(
  ID_PERMISSAO            INTEGER NOT NULL,
  PERMISSAO               VARCHAR(100),
  CONSTRAINT PK_PERMISSAO PRIMARY KEY (ID_PERMISSAO)
);

CREATE SEQUENCE SEQ_PERMISSAO START WITH 1 INCREMENT BY 1;
ALTER TABLE PERMISSAO MODIFY ID_PERMISSAO DEFAULT SEQ_PERMISSAO.NEXTVAL;

CREATE TABLE USUARIO
(
  ID_USUARIO            INTEGER NOT NULL,
  NOME_USUARIO          VARCHAR(100),
  LOGIN_USUARIO         VARCHAR(100),
  SENHA_USUARIO         VARCHAR(100),
  EMAIL                 VARCHAR(100),
  ID_PERMISSAO          INTEGER,
  CONSTRAINT PK_USUARIO PRIMARY KEY (ID_USUARIO),
  CONSTRAINT FK_USUARIO_ID_PERMISSAO FOREIGN KEY (ID_PERMISSAO) REFERENCES PERMISSAO (ID_PERMISSAO)
);

CREATE SEQUENCE SEQ_USUARIO START WITH 1 INCREMENT BY 1;
ALTER TABLE USUARIO MODIFY ID_USUARIO DEFAULT SEQ_USUARIO.NEXTVAL;

CREATE TABLE CHASSI
(
  ID_CHASSI            INTEGER NOT NULL,
  PARTE                VARCHAR(100),
  CHASSI               VARCHAR(100),
  CONSTRAINT PK_CHASSI PRIMARY KEY (ID_CHASSI)
);

CREATE SEQUENCE SEQ_CHASSI START WITH 1 INCREMENT BY 1;
ALTER TABLE CHASSI MODIFY ID_CHASSI DEFAULT SEQ_CHASSI.NEXTVAL;

CREATE TABLE BOLETIM
(
  ID_BOLETIM            INTEGER NOT NULL,
  BOLETIM               VARCHAR(100),
  ITEM                  VARCHAR(100),
  PARTE                VARCHAR(100),
  CONSTRAINT PK_BOLETIM PRIMARY KEY (ID_BOLETIM)
);

CREATE SEQUENCE SEQ_BOLETIM START WITH 1 INCREMENT BY 1;
ALTER TABLE BOLETIM MODIFY ID_BOLETIM DEFAULT SEQ_BOLETIM.NEXTVAL;

CREATE TABLE ITEM
(
  ID_ITEM            INTEGER NOT NULL,
  ITEM_NOME               VARCHAR(100),
  CONSTRAINT PK_ITEM PRIMARY KEY (ID_ITEM)
);

CREATE SEQUENCE SEQ_ITEM START WITH 1 INCREMENT BY 1;
ALTER TABLE ITEM MODIFY ID_ITEM DEFAULT SEQ_ITEM.NEXTVAL;


--ANTES
-- CREATE TABLE FORMULA
-- (
--   ID_FORMULA            INTEGER NOT NULL,
--   FORMULA               VARCHAR(100),
--   ID_ITEM               INTEGER,  
--   DT_CADASTRO           TIMESTAMP,
--   CONSTRAINT PK_FORMULA PRIMARY KEY (ID_FORMULA),
--   CONSTRAINT FK_FORMULA_ID_ITEM FOREIGN KEY (ID_ITEM) REFERENCES ITEM (ID_ITEM)
-- );

--DEPOIS
CREATE TABLE FORMULA
(
  ID_FORMULA            INTEGER NOT NULL,
  FORMULA               VARCHAR(100),
  ID_ITEM               INTEGER,  
  DT_CADASTRO           TIMESTAMP,
  CONSTRAINT PK_FORMULA PRIMARY KEY (ID_FORMULA)
);

CREATE SEQUENCE SEQ_FORMULA START WITH 1 INCREMENT BY 1;
ALTER TABLE FORMULA MODIFY ID_FORMULA DEFAULT SEQ_FORMULA.NEXTVAL;

CREATE TABLE SAMPLE
(
  ID_SAMPLE             INTEGER,
  ID_CHASSI             INTEGER,
  ID_BOLETIM            INTEGER,
  STATUS_SAMPLE         VARCHAR(100),
  ULT_USU_ALT           VARCHAR(100),
  DT_ULT_EDICAO         TIMESTAMP,
  CONSTRAINT PK_SAMPLE PRIMARY KEY (ID_SAMPLE),
  CONSTRAINT FK_SAMPLE_ID_CHASSI FOREIGN KEY (ID_CHASSI) REFERENCES CHASSI (ID_CHASSI),
  CONSTRAINT FK_SAMPLE_ID_BOLETIM FOREIGN KEY (ID_BOLETIM) REFERENCES BOLETIM (ID_BOLETIM)
);

CREATE SEQUENCE SEQ_SAMPLE START WITH 1 INCREMENT BY 1;
ALTER TABLE SAMPLE MODIFY ID_SAMPLE DEFAULT SEQ_SAMPLE.NEXTVAL;

--ANTES
-- CREATE TABLE LOGICA
-- (
--   ID_LOGICA            INTEGER NOT NULL,
--   ID_CHASSI            INTEGER,
--   ID_ITEM              INTEGER,  
--   CONSTRAINT PK_LOGICA PRIMARY KEY (ID_LOGICA),
--   CONSTRAINT FK_LOGICA_ID_CHASSI FOREIGN KEY (ID_CHASSI) REFERENCES CHASSI (ID_CHASSI),
--   CONSTRAINT FK_LOGICA_ID_ITEM FOREIGN KEY (ID_ITEM) REFERENCES ITEM (ID_ITEM)
-- );

--DEPOIS
CREATE TABLE LOGICA
(
  ID_LOGICA            INTEGER NOT NULL,
  ID_CHASSI            INTEGER,
  ID_ITEM              INTEGER,  
  CONSTRAINT PK_LOGICA PRIMARY KEY (ID_LOGICA)
);

CREATE SEQUENCE SEQ_LOGICA START WITH 1 INCREMENT BY 1;
ALTER TABLE LOGICA MODIFY ID_LOGICA DEFAULT SEQ_LOGICA.NEXTVAL;

CREATE TABLE CADASTRO
(
  ID_CADASTRO           INTEGER,
  ID_USUARIO            INTEGER,
  ID_CHASSI             INTEGER,
  CONSTRAINT PK_CADASTRO PRIMARY KEY (ID_CADASTRO),
  CONSTRAINT FK_CADASTRO_ID_CHASSI FOREIGN KEY (ID_CHASSI) REFERENCES CHASSI (ID_CHASSI),
  CONSTRAINT FK_CADASTRO_ID_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO (ID_USUARIO) ON DELETE CASCADE
);

CREATE SEQUENCE SEQ_CADASTRO START WITH 1 INCREMENT BY 1;
ALTER TABLE CADASTRO MODIFY ID_CADASTRO DEFAULT SEQ_CADASTRO.NEXTVAL;


CREATE TABLE UPDATE_SAMPLE
(
    ID_UPDATE_SAMPLE            INTEGER NOT NULL,
    ID_CHASSI                   INTEGER,
    ID_BOLETIM                  INTEGER,
    MENSAGEM                    VARCHAR(100),
    ID_USUARIO_ALTERANTE        INTEGER,
    DT_EDICAO               TIMESTAMP,
    CONSTRAINT PK_UPDATE_SAMPLE PRIMARY KEY (ID_UPDATE_SAMPLE)
);

CREATE SEQUENCE SEQ_UPDATE_SAMPLE START WITH 1 INCREMENT BY 1;
ALTER TABLE UPDATE_SAMPLE MODIFY ID_UPDATE_SAMPLE DEFAULT SEQ_UPDATE_SAMPLE.NEXTVAL;



--criação das views
CREATE VIEW VIEW_CADASTRO AS
SELECT usu.NOME_USUARIO, cha.CHASSI, per.PERMISSAO, cad.ID_CADASTRO, usu.ID_USUARIO, cha.ID_CHASSI, per.ID_PERMISSAO
FROM CADASTRO cad
INNER JOIN USUARIO usu 
ON usu.ID_USUARIO = cad.ID_USUARIO
INNER JOIN CHASSI cha 
ON cha.ID_CHASSI = cad.ID_CHASSI
INNER JOIN PERMISSAO per 
ON per.ID_PERMISSAO = usu.ID_PERMISSAO;

CREATE VIEW VIEW_SAMPLE AS
SELECT sam.ID_SAMPLE, usu.ID_USUARIO, sam.ID_CHASSI, sam.ID_BOLETIM, usu.NOME_USUARIO, bol.BOLETIM, bol.ITEM, sam.STATUS_SAMPLE, cha.CHASSI, sam.ULT_USU_ALT, sam.DT_ULT_EDICAO, usu_alt.NOME_USUARIO as ULTIMO_USUARIO_ALTERADO
FROM SAMPLE sam
INNER JOIN CHASSI cha 
ON cha.ID_CHASSI = sam.ID_CHASSI
INNER JOIN BOLETIM bol 
ON bol.ID_BOLETIM = sam.ID_BOLETIM
INNER JOIN CADASTRO cad 
ON cad.ID_CHASSI = sam.ID_CHASSI
INNER JOIN USUARIO usu 
ON usu.ID_USUARIO = cad.ID_USUARIO
INNER JOIN USUARIO usu_alt 
ON usu_alt.ID_USUARIO = sam.ULT_USU_ALT;

CREATE VIEW VIEW_UPDATE_SAMPLE AS
SELECT 
ups.ID_UPDATE_SAMPLE,
usu.ID_USUARIO,
usu.NOME_USUARIO,
ups.ID_CHASSI,
ups.ID_BOLETIM, 
bol.BOLETIM, 
ups.MENSAGEM, 
ups.ID_USUARIO_ALTERANTE,
usa.NOME_USUARIO AS NOME_USUARIO_ALTERANTE
FROM UPDATE_SAMPLE ups
INNER JOIN CHASSI cha 
ON ups.ID_CHASSI = cha.ID_CHASSI
INNER JOIN BOLETIM bol 
ON ups.ID_BOLETIM = bol.ID_BOLETIM
INNER JOIN USUARIO usa
ON ups.ID_USUARIO_ALTERANTE = usa.ID_USUARIO
INNER JOIN CADASTRO cad 
ON cad.ID_CHASSI = ups.ID_CHASSI 
INNER JOIN USUARIO usu 
ON usu.ID_USUARIO = cad.ID_USUARIO
ORDER BY usu.ID_USUARIO, ups.ID_CHASSI, ups.ID_BOLETIM ASC;

CREATE VIEW VIEW_ESTATISTICA_DISPONIBILIDADE AS
SELECT ROWNUM as id, cha.CHASSI, res.* FROM (
    SELECT DISTINCT tot.* FROM (

        SELECT  c.ID_CHASSI, 
                c.ID_USUARIO,
                i.ITEM,
                CASE 
                    WHEN ch.ITEM IS NOT NULL 
                    THEN 'CONTIDO' 
                    ELSE 'NÃO CONTIDO' 
                END AS STATUS
        FROM (
                SELECT DISTINCT ITEM, ID_CHASSI, ID_USUARIO FROM VIEW_SAMPLE
            ) c

        CROSS JOIN (
                    SELECT DISTINCT ITEM FROM BOLETIM
                ) i

        LEFT JOIN (
                    SELECT DISTINCT ITEM, ID_CHASSI FROM VIEW_SAMPLE
                ) ch 
        ON ch.ID_CHASSI = c.ID_CHASSI 
        AND ch.ITEM = i.ITEM
    )tot
    ORDER BY tot.ID_USUARIO, tot.ID_CHASSI, tot.STATUS, tot.ITEM ASC    
)res
INNER JOIN CHASSI cha 
ON cha.ID_CHASSI = res.ID_CHASSI; 


CREATE VIEW VIEW_ESTATISTICA_QTD_BOLETIM AS
SELECT ROWNUM as ID_QTD_BOLETIM, tot.* FROM (
SELECT sem.ID_CHASSI, cha.CHASSI, COUNT(sem.ID_CHASSI) AS QTD_BOLETINS  
FROM SAMPLE sem
INNER JOIN CHASSI cha 
ON cha.ID_CHASSI = sem.ID_CHASSI
GROUP BY sem.ID_CHASSI, cha.CHASSI
ORDER BY sem.ID_CHASSI,  cha.CHASSI
)tot;

CREATE VIEW VIEW_ESTATISTICA_QTD_CONTIDO AS
SELECT ROWNUM as ID_QTD_CONTIDO, tot.* FROM (
SELECT res.ID_CHASSI, 
        cha.CHASSI, 
        res.STATUS, 
        COUNT(res.STATUS) AS QTD_CONTIDO  
FROM (
    SELECT DISTINCT ID_CHASSI, ITEM, STATUS FROM VIEW_ESTATISTICA_DISPONIBILIDADE
    ORDER BY ID_CHASSI, STATUS, ITEM
)res
INNER JOIN CHASSI cha 
ON cha.ID_CHASSI = res.ID_CHASSI
GROUP BY res.ID_CHASSI, cha.CHASSI, res.STATUS
ORDER BY res.ID_CHASSI, cha.CHASSI, res.STATUS
)tot;

CREATE VIEW VIEW_ESTATISTICA_STATUS AS
SELECT  ROWNUM as ID_ESTATISTICA, res.* FROM (
    SELECT vv.ID_USUARIO, 
            vv.ID_CHASSI, 
            usu.NOME_USUARIO,
            cha.CHASSI,
            vv.STATUS_SAMPLE, 
            vv.ITEM 
    FROM VIEW_SAMPLE vv
    INNER JOIN USUARIO usu 
    ON usu.ID_USUARIO = vv.ID_USUARIO
    INNER JOIN CHASSI cha 
    ON cha.ID_CHASSI = vv.ID_CHASSI
)res
ORDER BY res.ID_USUARIO, res.ID_CHASSI, res.STATUS_SAMPLE, res.ITEM;                             

CREATE VIEW VIEW_STATUS_USUARIO AS
SELECT 
    U.NOME_USUARIO, 
    COUNT(CASE WHEN S.STATUS_SAMPLE = 'INCORPORATED' THEN 1 END) AS Incorporated,
    COUNT(CASE WHEN S.STATUS_SAMPLE = 'NOT INCORPORATED' THEN 1 END) AS Not_Incorporated,
    COUNT(CASE WHEN S.STATUS_SAMPLE = 'NOT APPLICABLE' THEN 1 END) AS Not_Applicable
FROM 
    USUARIO U
    INNER JOIN CADASTRO C ON U.ID_USUARIO = C.ID_USUARIO
    INNER JOIN SAMPLE S ON C.ID_CHASSI = S.ID_CHASSI
GROUP BY 
    U.NOME_USUARIO;

CREATE VIEW VIEW_STATUS_CHASSI AS
SELECT 
    c.CHASSI,
    SUM(CASE WHEN s.STATUS_SAMPLE = 'INCORPORATED' THEN 1 ELSE 0 END) as Incorporated,
    SUM(CASE WHEN s.STATUS_SAMPLE = 'NOT INCORPORATED' THEN 1 ELSE 0 END) as Not_Incorporated,
    SUM(CASE WHEN s.STATUS_SAMPLE = 'NOT APPLICABLE' THEN 1 ELSE 0 END) as Not_Applicable
FROM SAMPLE s
INNER JOIN CHASSI c ON s.ID_CHASSI = c.ID_CHASSI
GROUP BY c.CHASSI;

CREATE VIEW VIEW_ESTATISTICA_USUARIO AS
SELECT ROWNUM as id, res.* FROM(
    SELECT usu.ID_PERMISSAO, pem.PERMISSAO, COUNT(usu.ID_PERMISSAO) AS QTD FROM USUARIO usu
    INNER JOIN PERMISSAO pem 
    ON pem.ID_PERMISSAO = usu.ID_PERMISSAO
    GROUP BY usu.ID_PERMISSAO, pem.PERMISSAO
    ORDER BY usu.ID_PERMISSAO ASC
)res;

CREATE VIEW VIEW_LISTAR_USUARIOS AS
SELECT 
usu.ID_USUARIO, 
usu.NOME_USUARIO,
usu.LOGIN_USUARIO,
usu.SENHA_USUARIO,
usu.EMAIL,
usu.ID_PERMISSAO,
pem.PERMISSAO
FROM USUARIO usu
INNER JOIN PERMISSAO pem
ON pem.ID_PERMISSAO = usu.ID_PERMISSAO;

CREATE VIEW VIEW_PERMISSAO_USUARIO AS
SELECT 
    COUNT(CASE WHEN ID_PERMISSAO = 1 THEN 1 ELSE NULL END) AS Administrador,
    COUNT(CASE WHEN ID_PERMISSAO = 2 THEN 1 ELSE NULL END) AS Editor,
    COUNT(CASE WHEN ID_PERMISSAO = 3 THEN 1 ELSE NULL END) AS Consultor
FROM USUARIO;

CREATE VIEW VIEW_ITEM_CHASSI AS
SELECT b.ITEM, COUNT(*) AS QUANTIDADE
FROM SAMPLE s
JOIN BOLETIM b ON s.ID_BOLETIM = b.ID_BOLETIM
JOIN CHASSI c ON s.ID_CHASSI = c.ID_CHASSI
GROUP BY b.ITEM;

CREATE VIEW VIEW_QTD_STATUS AS
SELECT ROWNUM as id, res.* FROM (
SELECT 
  sam.ID_BOLETIM,
  bol.ITEM,
  ROUND(SUM(CASE WHEN sam.STATUS_SAMPLE = 'INCORPORATED' THEN 1 ELSE 0 END) * 100 / COUNT(*)) AS INCORPORATED_PERCENTAGE,
  ROUND(SUM(CASE WHEN sam.STATUS_SAMPLE = 'NOT INCORPORATED' THEN 1 ELSE 0 END) * 100 / COUNT(*)) AS NOT_INCORPORATED_PERCENTAGE,
  ROUND(SUM(CASE WHEN sam.STATUS_SAMPLE = 'APPLICABLE' THEN 1 ELSE 0 END) * 100 / COUNT(*)) AS APPLICABLE_PERCENTAGE
FROM SAMPLE sam
INNER JOIN BOLETIM bol
ON bol.ID_BOLETIM = sam.ID_BOLETIM
GROUP BY 
  sam.ID_BOLETIM, bol.ITEM
)res
ORDER BY res.ITEM;

CREATE VIEW VIEW_LISTAR_ITEM AS
SELECT ID_ITEM as id, ITEM_NOME FROM (
    SELECT ID_ITEM, ITEM_NOME FROM ITEM
    WHERE ID_ITEM NOT IN (SELECT ID_ITEM FROM FORMULA)
)res
ORDER BY ITEM_NOME ASC;

CREATE VIEW VIEW_LISTAR_BOLETIM AS
SELECT ID_BOLETIM as id, BOLETIM FROM (
    SELECT ID_BOLETIM, BOLETIM FROM BOLETIM 
)res
ORDER BY BOLETIM ASC;

CREATE VIEW VIEW_LISTAR_FORMULA AS
SELECT ROWNUM as ID, res.* FROM (
    SELECT ff.ID_FORMULA, ff.FORMULA, ff.ID_ITEM, ii.ITEM_NOME, ff.DT_CADASTRO 
    FROM FORMULA ff
    INNER JOIN ITEM ii
    ON ii.ID_ITEM = ff.ID_ITEM
)res;

CREATE VIEW VIEW_LISTAR_SAMPLE AS
SELECT 
ss.ID_SAMPLE,
ss.ID_CHASSI,
ss.ID_BOLETIM,
bb.BOLETIM
FROM SAMPLE ss
INNER JOIN BOLETIM bb
ON bb.ID_BOLETIM = ss.ID_BOLETIM
WHERE ss.STATUS_SAMPLE = 'INCORPORATED'
ORDER BY ss.ID_CHASSI, ss.ID_BOLETIM ASC;

CREATE VIEW VIEW_LISTAR_LOGICA AS
SELECT ROWNUM AS ID, res.* FROM (

SELECT DISTINCT tot.* FROM (

    SELECT 
    c.ID_CHASSI,
    c.ID_ITEM, 
    c.CHASSI, 
    c.ID_USUARIO, 
    c.NOME_USUARIO, 
    i.ITEM_NOME,
    CASE 
        WHEN ch.ITEM_NOME IS NOT NULL 
        THEN 'INCORPORATED' 
        ELSE 'NOT INCORPORATED' 
    END AS status_sample

    FROM (
        SELECT DISTINCT ID_CHASSI, ID_ITEM, ITEM_NOME, CHASSI, ID_USUARIO, NOME_USUARIO, STATUS_SAMPLE FROM (

            SELECT ROWNUM as ID, tot.*, 'INCORPORATED' as status_sample
            FROM (
                SELECT res.*, cha.CHASSI, cad.ID_USUARIO, usu.NOME_USUARIO FROM (
                    SELECT DISTINCT ll.ID_CHASSI, ll.ID_ITEM, ii.ITEM_NOME FROM LOGICA ll
                    INNER JOIN ITEM ii 
                    ON ii.ID_ITEM = ll.ID_ITEM 
                    ORDER BY ll.ID_CHASSI, ll.ID_ITEM ASC 
                )res
                INNER JOIN CHASSI cha 
                ON cha.ID_CHASSI = res.ID_CHASSI
                INNER JOIN CADASTRO cad 
                ON cad.ID_CHASSI = res.ID_CHASSI
                INNER JOIN USUARIO usu 
                ON usu.ID_USUARIO = cad.ID_USUARIO
                ORDER BY cad.ID_USUARIO, res.ID_CHASSI, res.ID_ITEM ASC
            )tot
            ORDER BY tot.ID_USUARIO, tot.ID_CHASSI , tot.ITEM_NOME ASC  

        ) 
    ) c
    CROSS JOIN (
        SELECT ITEM_NOME FROM ITEM
    ) i
    LEFT JOIN (
        SELECT DISTINCT ITEM_NOME, ID_CHASSI FROM (

            SELECT ROWNUM as ID, tot.*, 'INCORPORATED' as status_sample
            FROM (
                SELECT res.*, cha.CHASSI, cad.ID_USUARIO, usu.NOME_USUARIO FROM (
                    SELECT DISTINCT ll.ID_CHASSI, ll.ID_ITEM, ii.ITEM_NOME FROM LOGICA ll
                    INNER JOIN ITEM ii 
                    ON ii.ID_ITEM = ll.ID_ITEM 
                    ORDER BY ll.ID_CHASSI, ll.ID_ITEM ASC 
                )res
                INNER JOIN CHASSI cha 
                ON cha.ID_CHASSI = res.ID_CHASSI
                INNER JOIN CADASTRO cad 
                ON cad.ID_CHASSI = res.ID_CHASSI
                INNER JOIN USUARIO usu 
                ON usu.ID_USUARIO = cad.ID_USUARIO
                ORDER BY cad.ID_USUARIO, res.ID_CHASSI, res.ID_ITEM ASC
            )tot
            ORDER BY tot.ID_USUARIO, tot.ID_CHASSI , tot.ITEM_NOME ASC  

        )
    ) ch 
    ON ch.ID_CHASSI = c.ID_CHASSI 
    AND ch.ITEM_NOME = i.ITEM_NOME

)tot
    ORDER BY tot.ID_USUARIO, tot.ID_CHASSI, tot.status_sample, tot.ITEM_NOME ASC    
)res;




-- Tabela PERMISSAO
INSERT INTO PERMISSAO (PERMISSAO) VALUES ('Administrator');
INSERT INTO PERMISSAO (PERMISSAO) VALUES ('Editor');
INSERT INTO PERMISSAO (PERMISSAO) VALUES ('Consultant');

-- Tabela USUARIO
INSERT INTO USUARIO (NOME_USUARIO, LOGIN_USUARIO, SENHA_USUARIO, EMAIL, ID_PERMISSAO) VALUES ('Aldrik Alvaro', 'aldrik', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C', 'aldrik@gmail.com', 1);
INSERT INTO USUARIO (NOME_USUARIO, LOGIN_USUARIO, SENHA_USUARIO, EMAIL, ID_PERMISSAO) VALUES ('Ana Leal', 'ana', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C', 'ana@gmail.com', 2);
INSERT INTO USUARIO (NOME_USUARIO, LOGIN_USUARIO, SENHA_USUARIO, EMAIL, ID_PERMISSAO) VALUES ('Michael Felipe', 'michael', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C', 'michael@gmail.com', 3);
INSERT INTO USUARIO (NOME_USUARIO, LOGIN_USUARIO, SENHA_USUARIO, EMAIL, ID_PERMISSAO) VALUES ('Emanuelle', 'emanuelle', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C', 'emanuelle@gmail.com', 1);
INSERT INTO USUARIO (NOME_USUARIO, LOGIN_USUARIO, SENHA_USUARIO, EMAIL, ID_PERMISSAO) VALUES ('Augusto', 'augusto', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C', 'augusto@gmail.com', 2);
INSERT INTO USUARIO (NOME_USUARIO, LOGIN_USUARIO, SENHA_USUARIO, EMAIL, ID_PERMISSAO) VALUES ('Victor', 'victor', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C', 'victor@gmail.com', 2);
INSERT INTO USUARIO (NOME_USUARIO, LOGIN_USUARIO, SENHA_USUARIO, EMAIL, ID_PERMISSAO) VALUES ('luiz', 'luiz', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C', 'luiz@gmail.com', 3);
INSERT INTO USUARIO (NOME_USUARIO, LOGIN_USUARIO, SENHA_USUARIO, EMAIL, ID_PERMISSAO) VALUES ('admin', 'admin', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C', 'admin@gmail.com', 1);
INSERT INTO USUARIO (NOME_USUARIO, LOGIN_USUARIO, SENHA_USUARIO, EMAIL, ID_PERMISSAO) VALUES ('editor', 'editor', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C', 'editor@gmail.com', 2);
INSERT INTO USUARIO (NOME_USUARIO, LOGIN_USUARIO, SENHA_USUARIO, EMAIL, ID_PERMISSAO) VALUES ('consulta', 'consulta', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C', 'consulta@gmail.com', 3);

-- Tabela CHASSI
INSERT INTO CHASSI (CHASSI) VALUES ('10000076');
INSERT INTO CHASSI (CHASSI) VALUES ('10000153');
INSERT INTO CHASSI (CHASSI) VALUES ('10000218');
INSERT INTO CHASSI (CHASSI) VALUES ('10000270');
INSERT INTO CHASSI (CHASSI) VALUES ('10000348');
INSERT INTO CHASSI (CHASSI) VALUES ('10000432');
INSERT INTO CHASSI (CHASSI) VALUES ('10000437');
INSERT INTO CHASSI (CHASSI) VALUES ('10000470');

-- Tabela BOLETIM
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ( 'SB FAT-21-CG12', 'Vidros e travas elétricas');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ( 'SB FAT-23-CG01', 'Sistema modernizado de som');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ( 'SB FAT-23-CG04', 'Cruise Control');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ('SB FAT-23-CG06', 'Sensor de proximidade');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ('SB FAT-23-CG08', 'Calha de chuva');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ('SB FAT-23-CG02', 'Controle de tração');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ('SB FAT-23-CG03', 'Pintura anti-corrosão');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ('SB FAT-25-CG23', 'Função Economic/Sport');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ('SB FAT-25-CG30', 'Bluetooth');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ( 'SB FAT-25-CG04', 'Câmera traseira');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ( 'SB FAT-25-CG31', 'Freio ABS');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ( 'SB FAT-27-CG11', 'Vidros com fechamento em um toque');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ( 'SB FAT-27-CG18', 'Partida remota');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ( 'SB FAT-30-CG04', 'Aquecimento nos bancos');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ( 'SB FAT-31-CG03', 'Controle de estabilidade');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ( 'SB FAT-31-CG19', 'Roda de liga leve');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ( 'SB FAT-31-CG02', 'Câmbio automático');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ( 'SB FAT-34-CG23', 'Farol de neblina');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ( 'SB FAT-23-CG05', 'Cruise Control');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES (  'SB FAT-31-CG05', 'Cruise Control');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES (  'SB FAT-31-CG08', 'Sensor de proximidade');
INSERT INTO BOLETIM (BOLETIM, ITEM) VALUES ( 'SB FAT-25-CG11', 'Câmera traseira');

INSERT INTO ITEM (ITEM_NOME) VALUES ('Vidros e travas elétricas');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Ar-Condicionado');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Direção hidráulica');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Airbags frontais');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Airbags laterais');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Sistema modernizado de som');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Cruise Control');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Sensor de proximidade');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Calha de chuva');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Controle de tração');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Pintura anti-corrosão');
INSERT INTO ITEM (ITEM_NOME) VALUES ('GPS');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Função Economic/Sport');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Sistema multimídia');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Bluetooth');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Câmera traseira');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Estepe');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Freio ABS');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Vidros com fechamento em um toque');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Partida remota');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Aquecimento nos bancos');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Controle de estabilidade');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Roda de liga leve');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Câmbio automático');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Banco de couro');
INSERT INTO ITEM (ITEM_NOME) VALUES ('Farol de neblina');

INSERT INTO FORMULA (FORMULA, ID_ITEM, DT_CADASTRO) VALUES ('SB FAT-21-CG12 and SB FAT-23-CG01', 1, CURRENT_TIMESTAMP);
INSERT INTO FORMULA (FORMULA, ID_ITEM, DT_CADASTRO) VALUES ('SB FAT-23-CG08 or SB FAT-00-CG19', 2, CURRENT_TIMESTAMP);
INSERT INTO FORMULA (FORMULA, ID_ITEM, DT_CADASTRO) VALUES ('SB FAT-00-CG16 or SB FAT-00-CG11 and SB FAT-00-CG27', 3, CURRENT_TIMESTAMP);
INSERT INTO FORMULA (FORMULA, ID_ITEM, DT_CADASTRO) VALUES ('SB FAT-25-CG04', 4, CURRENT_TIMESTAMP);
INSERT INTO FORMULA (FORMULA, ID_ITEM, DT_CADASTRO) VALUES ('SB FAT-34-CG23 or SB FAT-25-CG11', 6, CURRENT_TIMESTAMP);
INSERT INTO FORMULA (FORMULA, ID_ITEM, DT_CADASTRO) VALUES ('SB FAT-25-CG04 and SB FAT-27-CG11', 5, CURRENT_TIMESTAMP);
INSERT INTO FORMULA (FORMULA, ID_ITEM, DT_CADASTRO) VALUES ('SB FAT-25-CG04 or SB FAT-23-CG01', 8, CURRENT_TIMESTAMP);

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

INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 1, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 2, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 3, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 4, 'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 10, 'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 6, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 7, 'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (2, 8, 'INCORPORATED', 2, CURRENT_TIMESTAMP);

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

INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (7, 11,  'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (7, 12,  'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (7, 13,  'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (7, 14,  'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (7, 15, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (7, 16, 'INCORPORATED', 2, CURRENT_TIMESTAMP);

INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (8, 11,  'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (8, 12,  'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (8, 13,  'NOT INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (8, 14,  'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (8, 15, 'INCORPORATED', 2, CURRENT_TIMESTAMP);
INSERT INTO SAMPLE (ID_CHASSI, ID_BOLETIM, STATUS_SAMPLE, ULT_USU_ALT, DT_ULT_EDICAO) VALUES (8, 16, 'INCORPORATED', 2, CURRENT_TIMESTAMP);

--INSERT INTO LOGICA (ID_CHASSI, ID_ITEM) VALUES (1,1);
--INSERT INTO LOGICA (ID_CHASSI, ID_ITEM) VALUES (2,2);

INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI) VALUES (1, 1);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI) VALUES (1, 4);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI) VALUES (2, 2);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI) VALUES (2, 3);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI) VALUES (2, 1);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI)VALUES (3, 3);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI)VALUES (4, 3);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI)VALUES (5, 2);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI)VALUES (6, 1);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI)VALUES (8, 7);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI)VALUES (8, 8);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI)VALUES (9, 2);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI)VALUES (9, 1);
INSERT INTO CADASTRO (ID_USUARIO, ID_CHASSI)VALUES (10, 3);

CREATE TRIGGER TR_ACOMPANHAMENTO_SAMPLE
AFTER UPDATE ON SAMPLE
FOR EACH ROW
BEGIN
    INSERT INTO UPDATE_SAMPLE (ID_CHASSI, ID_BOLETIM, MENSAGEM, ID_USUARIO_ALTERANTE, DT_EDICAO)
    VALUES (:OLD.ID_CHASSI, :OLD.ID_BOLETIM, CONCAT(CONCAT((SELECT bol.ITEM FROM BOLETIM bol WHERE bol.ID_BOLETIM = :OLD.ID_BOLETIM) ,' available to chassi '), (SELECT cha.CHASSI FROM CHASSI cha WHERE cha.ID_CHASSI = :OLD.ID_CHASSI)), 1, CURRENT_TIMESTAMP);
END;