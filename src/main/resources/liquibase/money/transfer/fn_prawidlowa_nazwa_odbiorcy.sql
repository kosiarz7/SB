--liquibase formatted sql

--changeset samoSieZrobilo:6 endDelimiter:"/"
create or replace
function fn_prawidlowa_nazwa_odbiorcy(i_number_rachunku in varchar2,
                                      i_nazwa_odbiorcy in varchar2)
return boolean is
    l_liczba_nazwisk number;
begin
    select count(*)
      into l_liczba_nazwisk
      from rachunki r,
           klienci_rachunki kr,
           klienci k,
		   rachunki r2
     where kr.id_klient = k.id_klient
       and kr.id_rachunek = r.id_rachunek
       and r.id_rachunek = r2.id_rachunek
	     and r2.numer = i_number_rachunku
       and UPPER(i_nazwa_odbiorcy) like '%' || trim(UPPER(k.nazwisko)) || '%';
    
    if l_liczba_nazwisk > 0 then
        return true;
    else
        return false;
    end if;
    
    exception
    when others then
        return false;    
end;
/