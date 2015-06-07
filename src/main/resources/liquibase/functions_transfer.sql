--liquibase formatted sql

--changeset samoSieZrobilo:3
create sequence seq_przelew_wych_oczekujacy
    start with 1
    increment by 1;

create sequence seq_przelew_wych_zrealizowany
    start with 1
    increment by 1;
    
create or replace
function fn_rachunek_istnieje(i_id_rachunku in varchar2)
return int is
    l_id_rachunek int := -1;
begin
    select id_rachunek
      into l_id_rachunek
      from rachunki
      where numer = i_id_rachunku;
     
    return l_id_rachunek;
   
    exception
    when others then
        return -1;  
end;

