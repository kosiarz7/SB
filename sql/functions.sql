create or replace
function fn_rachunek_istnieje(i_id_rachunku in varchar2)
return int is
	l_id_rachunku number := -1;
begin
	select id_rachunek
		into l_id_rachunku
		from rachunki
		where numer = i_id_rachunku;

	return l_id_rachunku;

	exception
    when others then
        return -1;    
end;

create or replace function fn_prawidlowa_nazwa_odbiorcy(i_id_rachunku in varchar2,
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
     where kr.id_klient_rachunek = k.id_klient
       and kr.id_klient_rachunek = r.id_rachunek
       and r.id_rachunek = r2.id_rachunek
	   and r2.numer = i_id_rachunku
       and i_nazwa_odbiorcy like '%' || trim(k.nazwisko) || '%';
     
    if l_liczba_nazwisk > 0 then
        return true;
    else
        return false;
    end if;
    
    exception
    when others then
        return false;    
end;

create or replace function fn_prawidlowy_adres_odbiorcy(i_id_rachunku in varchar2,
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
	   and r2.numer = i_id_rachunku
       and (i_adres_odbiorcy like '%' || trim(k.adres) || '%'
            or i_adres_odbiorcy like '%' || trim(k.miasto) || '%');
     
    if l_liczba_adresow > 0 then
        return true;
    else
        return false;
    end if;
    
    exception
    when others then
        return false;    
end;

create or replace
FUNCTION fn_dodaj_przelew
    (
      i_rachunek_nadawcy        IN VARCHAR2,
      i_rachunek_odbiorcy       IN VARCHAR2,
      i_nazwa_odbiorcy          IN VARCHAR2,
      i_adres_odbiorcy          IN VARCHAR2,
      i_tytul_przelewu          IN VARCHAR2,
      i_kwota_przelewu          IN NUMBER,
      i_zlecona_data_transakcji IN DATE,
      i_typ_przelewu            IN INT)
    RETURN INT
  IS
  BEGIN
    -- sprawdzenie poprawnosci wprowadzonych danych do przelewu
    IF fn_rachunek_istnieje(i_rachunek_nadawcy) = -1 THEN
      RETURN 1;
    ELSIF NOT fn_rachunek_istnieje(i_rachunek_odbiorcy)= -1 THEN
      RETURN 2;
      elsif not fn_prawidlowa_nazwa_odbiorcy(i_rachunek_odbiorcy, i_nazwa_odbiorcy) then
       return 3;
       
   elsif not fn_prawidlowy_adres_odbiorcy(i_rachunek_odbiorcy, i_adres_odbiorcy) then
       return 4;

   elsif not fn_wystarczajace_srodki(i_rachunek_nadawcy, i_kwota_przelewu) then
       return 5;
       
   elsif not trunc(i_zlecona_data_transakcji) >= trunc(sysdate) then
       return 6;
  
   elsif not i_kwota_przelewu > 0 then
       return 7;
       
   else
       -- przeniesienie srodkow potrzebnych do realizacji przelewu do r. DROGA
       update rachunki
          set saldo = saldo - i_kwota_przelewu
        where numer = i_rachunek_nadawcy;

      
       -- dodanie przelewu do p. oczekujacych
       insert into przelew_wych_oczekujacy
       (
           id_przelew_oczekujacy,
           nr_rachunku_docelowego,
           nazwa_odbiorcy,
           tytul,
           adres,
           kwota,
           data_przyjecia_do_realizacji,
           data_realizacji,
           id_typ_przelewu,
           id_klient_rachunek_wysylajacy
       )
       values
       (
           seq_przelew_wych_oczekujacy.nextval,
           i_rachunek_odbiorcy,
           i_nazwa_odbiorcy,
           i_tytul_przelewu,
           i_adres_odbiorcy,
           i_kwota_przelewu,
           sysdate,
           i_zlecona_data_transakcji,
           i_typ_przelewu, -- TODO okreslic typy przelewow
           i_rachunek_nadawcy
       );
       
       return 0;
       
   end if;
    RETURN 0;
  EXCEPTION
  WHEN OTHERS THEN
    RETURN -1;
  END;
