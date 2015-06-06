--liquibase formatted sql

--changeset samoSieZrobilo:7 endDelimiter:"/"
create or replace
function fn_prawidlowy_adres_odbiorcy(i_numer_rachunku in varchar2,
                                      i_adres_odbiorcy in varchar2)
return boolean is
    l_liczba_adresow number;
begin
    select count(*)
      into l_liczba_adresow
      from rachunki r,
           klienci_rachunki kr,
           klienci k,
		   rachunki r2
     where kr.id_klient = k.id_klient
       and kr.id_rachunek = r.id_rachunek
       and r.id_rachunek = r2.id_rachunek
	     and r2.numer = i_numer_rachunku
       and (trim(UPPER(k.adres)) like '%' || trim(UPPER(i_adres_odbiorcy)) || '%'
            or trim(UPPER(k.miasto)) like '%' || trim(UPPER(i_adres_odbiorcy)) || '%');
     
    if l_liczba_adresow > 0 then
        return true;
    else
        return false;
    end if;
    
    exception
    when others then
        return false;    
end;
/