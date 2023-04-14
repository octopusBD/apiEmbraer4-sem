--drop das tabelas
DROP TABLE CADASTRO;
DROP TABLE SAMPLE;
DROP TABLE USUARIO;
DROP TABLE PERMISSAO;
DROP TABLE PDF;
DROP TABLE CHASSI;
DROP TABLE BOLETIM;
DROP TABLE UPDATE_SAMPLE;

--criação das tabelas
CREATE TABLE BOLETIM
(
  ID_BOLETIM            INTEGER NOT NULL,
  BOLETIM               VARCHAR(100),
  ITEM                  VARCHAR(100),
  CONSTRAINT PK_BOLETIM PRIMARY KEY (ID_BOLETIM)
);

CREATE TABLE CHASSI
(
  ID_CHASSI            INTEGER NOT NULL,
  PARTE                VARCHAR(100),
  CHASSI               VARCHAR(100),
  CONSTRAINT PK_CHASSI PRIMARY KEY (ID_CHASSI)
);

CREATE TABLE PERMISSAO
(
  ID_PERMISSAO            INTEGER NOT NULL,
  PERMISSAO               VARCHAR(100),
  CONSTRAINT PK_PERMISSAO PRIMARY KEY (ID_PERMISSAO)
);

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

CREATE TABLE CADASTRO
(
  ID_CADASTRO           INTEGER,
  ID_USUARIO            INTEGER,
  ID_CHASSI             INTEGER,
  CONSTRAINT PK_CADASTRO PRIMARY KEY (ID_CADASTRO),
  CONSTRAINT FK_CADASTRO_ID_CHASSI FOREIGN KEY (ID_CHASSI) REFERENCES CHASSI (ID_CHASSI),
  CONSTRAINT FK_CADASTRO_ID_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO (ID_USUARIO)
);

CREATE TABLE UPDATE_SAMPLE
(
    ID_UPDATE_SAMPLE            INTEGER NOT NULL,
    ID_CHASSI                   INTEGER,
    ID_BOLETIM                  INTEGER,
    MENSAGEM                    VARCHAR(100),
    ID_USUARIO_ALTERANTE        INTEGER,
    DT_EDICAO                   TIMESTAMP,
    CONSTRAINT PK_UPDATE_SAMPLE PRIMARY KEY (ID_UPDATE_SAMPLE)
);

------------------------------------------------------------------------------------------------------

--drop das sequences
DROP SEQUENCE SEQ_BOLETIM;
DROP SEQUENCE SEQ_CHASSI;
DROP SEQUENCE SEQ_PERMISSAO;
DROP SEQUENCE SEQ_USUARIO;
DROP SEQUENCE SEQ_CADASTRO;
DROP SEQUENCE SEQ_SAMPLE;
DROP SEQUENCE SEQ_UPDATE_SAMPLE;


--criação das sequence
CREATE SEQUENCE SEQ_BOLETIM START WITH 1 INCREMENT BY 1;
ALTER TABLE BOLETIM MODIFY ID_BOLETIM DEFAULT SEQ_BOLETIM.NEXTVAL;

CREATE SEQUENCE SEQ_CHASSI START WITH 1 INCREMENT BY 1;
ALTER TABLE CHASSI MODIFY ID_CHASSI DEFAULT SEQ_CHASSI.NEXTVAL;

CREATE SEQUENCE SEQ_PERMISSAO START WITH 1 INCREMENT BY 1;
ALTER TABLE PERMISSAO MODIFY ID_PERMISSAO DEFAULT SEQ_PERMISSAO.NEXTVAL;

CREATE SEQUENCE SEQ_USUARIO START WITH 1 INCREMENT BY 1;
ALTER TABLE USUARIO MODIFY ID_USUARIO DEFAULT SEQ_USUARIO.NEXTVAL;

CREATE SEQUENCE SEQ_CADASTRO START WITH 1 INCREMENT BY 1;
ALTER TABLE CADASTRO MODIFY ID_CADASTRO DEFAULT SEQ_CADASTRO.NEXTVAL;

CREATE SEQUENCE SEQ_SAMPLE START WITH 1 INCREMENT BY 1;
ALTER TABLE SAMPLE MODIFY ID_SAMPLE DEFAULT SEQ_SAMPLE.NEXTVAL;

CREATE SEQUENCE SEQ_UPDATE_SAMPLE START WITH 1 INCREMENT BY 1;
ALTER TABLE UPDATE_SAMPLE MODIFY ID_UPDATE_SAMPLE DEFAULT SEQ_UPDATE_SAMPLE.NEXTVAL;
