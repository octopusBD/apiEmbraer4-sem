--drop das views
DROP VIEW VIEW_CADASTRO;
DROP VIEW VIEW_SAMPLE;

--criação das views
CREATE VIEW VIEW_CADASTRO AS
SELECT usu.NOME_USUARIO, cha.CHASSI, per.PERMISSAO, cad.ID_CADASTRO, usu.ID_USUARIO, cha.ID_CHASSI, per.ID_PERMISSAO
FROM CADASTRO cad
INNER JOIN USUARIO usu 
ON usu.ID_USUARIO = cad.ID_USUARIO
INNER JOIN CHASSI cha 
ON cha.ID_CHASSI = cad.ID_CHASSI
INNER JOIN PERMISSAO per 
ON per.ID_PERMISSAO = usu.ID_PERMISSAO

CREATE VIEW VIEW_SAMPLE AS
SELECT sam.ID_CHASSI, sam.ID_BOLETIM, usu.NOME_USUARIO, bol.BOLETIM, bol.ITEM, sam.STATUS_SAMPLE, cha.CHASSI, sam.ULT_USU_ALT, sam.DT_ULT_EDICAO
FROM SAMPLE sam
INNER JOIN CHASSI cha 
ON cha.ID_CHASSI = sam.ID_CHASSI
INNER JOIN BOLETIM bol 
ON bol.ID_BOLETIM = sam.ID_BOLETIM
INNER JOIN USUARIO usu 
ON usu.ID_USUARIO = sam.ULT_USU_ALT