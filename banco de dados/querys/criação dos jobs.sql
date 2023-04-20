-- criação da rotina para limpar semanalmente tabela LOG_BOLETIM_ITEM
CREATE OR REPLACE PROCEDURE LIMPEZA_LOGS AS
BEGIN
    EXECUTE IMMEDIATE 'TRUNCATE TABLE LOG_BOLETIM_ITEM';
END LIMPEZA_LOGS;
/

BEGIN
    DBMS_SCHEDULER.CREATE_JOB (
        job_name        => 'LIMPA_LOGS',
        job_type        => 'STORED_PROCEDURE',
        job_action      => 'LIMPEZA_LOGS',
        start_date      => SYSTIMESTAMP,
        repeat_interval => 'FREQ=WEEKLY;BYDAY=SUN;BYHOUR=2;BYMINUTE=0;BYSECOND=0;',
        enabled         => TRUE
    );
END;
/