--liquibase formatted sql

--changeset samoSieZrobilo:5 
create sequence seq_przelew_przychodzace
    start with 1
    increment by 1;
