--liquibase formatted sql

--changeset samoSieZrobilo:4

create sequence seq_przelew_wych_oczekujacy
    start with 1
    increment by 1;

