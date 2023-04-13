-- recriação da tabela log_boletim_item (caso a tebale já não estaja com todos os dados da tabela sample)
CREATE TABLE LOG_BOLETIM_ITEM AS SELECT * FROM SAMPLE;

-- criação da trigger para passar as informações da tabela SAMPLE para LOG_BOLETIM_ITEM
CREATE OR REPLACE TRIGGER SAMPLE_BACKUP
AFTER UPDATE ON SAMPLE
FOR EACH ROW
    ENABLE
BEGIN
    INSERT INTO LOG_BOLETIM_ITEM (
        ID_SAMPLE,
        ID_CHASSI,
        ID_BOLETIM,
        STATUS_SAMPLE,
        ULT_USU_ALT,
        DT_ULT_EDICAO
    ) VALUES (
        :NEW.ID_SAMPLE,
        :NEW.ID_CHASSI,
        :NEW.ID_BOLETIM,
        :NEW.STATUS_SAMPLE,
        :NEW.ULT_USU_ALT,
        :NEW.DT_ULT_EDICAO
    );
END;
/
