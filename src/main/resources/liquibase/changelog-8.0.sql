--liquibase formatted sql
DECLARE
   l_job_exists number;
BEGIN
    SELECT count(*)
      INTO l_job_exists 
      FROM user_scheduler_jobs
     WHERE job_name = 'przet_przel_oczek';

   IF l_job_exists = 1 THEN
      SYS.DBMS_SCHEDULER.DROP_JOB(job_name => 'przet_przel_oczek');
   END IF;
   
    SYS.DBMS_SCHEDULER.CREATE_JOB (
            job_name => 'przet_przel_oczek',
            job_type => 'STORED_PROCEDURE',
            job_action => 'PR_PRZETWARZANIE_PRZEL_OCZEK',
            number_of_arguments => 1,
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
             name => 'przet_przel_oczek',
             attribute => 'logging_level', value => DBMS_SCHEDULER.LOGGING_RUNS
    );

    SYS.DBMS_SCHEDULER.SET_JOB_ARGUMENT_VALUE(
             job_name => 'przet_przel_oczek',
             argument_position => 1,
             argument_value => ''
    );
            
    SYS.DBMS_SCHEDULER.enable(name => 'przet_przel_oczek');
END;
/
