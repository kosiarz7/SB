--liquibase formatted sql

--changeset samoSieZrobilo:5 
create sequence seq_polecenie_zaplaty
    start with 1
    increment by 1;
