--liquibase formatted sql

--changeset samoSieZrobilo:8 endDelimiter:/
create or replace
function fn_wystarczajace_srodki(i_nr_rachunku in varchar2,
                                 i_kwota_przelewu in decimal)
return boolean is
    l_saldo decimal;
begin
    select saldo
      into l_saldo
      from rachunki
     where numer = i_nr_rachunku;
     
    if l_saldo >= i_kwota_przelewu then
        return true;
    else
        return false;
    end if;
    
    exception
    when others then
        return false;    
end;
/