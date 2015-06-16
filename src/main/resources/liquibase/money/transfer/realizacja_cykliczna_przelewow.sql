--liquibase formatted sql
DECLARE
   l_job_exists number;
BEGIN
    SELECT count(*)
      INTO l_job_exists 
      FROM user_scheduler_jobs
     WHERE job_name = 'PRZET_PRZEL_OCZEK';

   IF l_job_exists = 1 THEN
      SYS.DBMS_SCHEDULER.DROP_JOB(job_name => 'PRZET_PRZEL_OCZEK');
   END IF;
   
    SYS.DBMS_SCHEDULER.CREATE_JOB (
            job_name => 'PRZET_PRZEL_OCZEK',
            job_type => 'STORED_PROCEDURE',
            job_action => 'PR_PRZETWARZANIE_PRZEL_OCZEK',
            number_of_arguments => 0,
            start_date => NULL,
            repeat_interval => 'FREQ=MINUTELY;BYSECOND=0',
            end_date => NULL,
            job_class => '"SYS"."DEFAULT_JOB_CLASS"',
            enabled => FALSE,
            auto_drop => FALSE,
            comments => '',
            credential_name => NULL,
            destination_name => NULL
    );

    SYS.DBMS_SCHEDULER.SET_ATTRIBUTE(
             name => 'PRZET_PRZEL_OCZEK',
             attribute => 'logging_level', value => DBMS_SCHEDULER.LOGGING_RUNS
    );
     
    SYS.DBMS_SCHEDULER.enable(name => 'PRZET_PRZEL_OCZEK');
END;
/
