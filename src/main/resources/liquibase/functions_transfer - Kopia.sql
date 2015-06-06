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


create or replace
function fn_wystarczajace_srodki(i_id_rachunku in varchar2,
                                 i_kwota_przelewu in decimal)
return boolean is
    l_saldo decimal;
begin
    select saldo
      into l_saldo
      from rachunki
     where numer = i_id_rachunku;
     
    if l_saldo >= i_kwota_przelewu then
        return true;
    else
        return false;
    end if;
    
    exception
    when others then
        return false;    
end;

create or replace
function fn_dodaj_przelew(id_klient_rachunek_wysylajacy in int,
                          i_rachunek_nadawcy in varchar2,
                          i_rachunek_odbiorcy in varchar2,
                          i_nazwa_odbiorcy in varchar2,
                          i_adres_odbiorcy in varchar2,
                          i_tytul_przelewu in varchar2,
                          i_kwota_przelewu in number,
                          i_zlecona_data_transakcji in date,
                          i_typ_przelewu in int)
return int is
    L_RACHUNEK_DROGA number := 0;
    l_id_rachunek_nadawcy INT := -1;
    l_id_rachunek_odbiorcy INT := -1;
begin
   -- sprawdzenie poprawnosci wprowadzonych danych do przelewu
   --SELECT fn_rachunek_istnieje(i_rachunek_nadawcy) INTO l_id_rachunek_nadawcy FROM dual;
   l_id_rachunek_nadawcy := fn_rachunek_istnieje(i_rachunek_nadawcy);
   if l_id_rachunek_nadawcy = -1  then
      dbms_output.put_line('l_id_rachunek_nadawcy exit');
       return 1;
    end if;
   l_id_rachunek_odbiorcy := fn_rachunek_istnieje(i_rachunek_odbiorcy);
   if l_id_rachunek_odbiorcy = -1  then
       return 2;
   end if; 
   if not fn_prawidlowa_nazwa_odbiorcy(i_rachunek_odbiorcy, i_nazwa_odbiorcy) then
       return 3;
    end if;    
   if not fn_prawidlowy_adres_odbiorcy(i_rachunek_odbiorcy, i_adres_odbiorcy) then
       return 4;
    end if;
   if not fn_wystarczajace_srodki(i_rachunek_nadawcy, i_kwota_przelewu) then
       return 5;
    end if;    
   if not trunc(i_zlecona_data_transakcji) >= trunc(sysdate) then
       return 6;
   end if;
   if not i_kwota_przelewu > 0 then
       return 7;
      end if;  
   
       -- przeniesienie srodkow potrzebnych do realizacji przelewu do r. DROGA
       update rachunki
          set saldo = saldo - i_kwota_przelewu
        where numer = i_rachunek_nadawcy;
      
       update rachunki
          set saldo = saldo + i_kwota_przelewu
        where numer = L_RACHUNEK_DROGA;
      
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
           id_klient_zrodlo,
           id_rachunku_zrodla
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
           i_typ_przelewu,
           id_klient_rachunek_wysylajacy,
           l_id_rachunek_nadawcy
           
       );
       
       return 0;
   
  
   
   exception
   when others then
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
end;

create or replace procedure fn_dodaj_przelew2(res out int,
                          id_klient_rachunek_wysylajacy in int,
                          i_rachunek_nadawcy in varchar2,
                          i_rachunek_odbiorcy in varchar2,
                          i_nazwa_odbiorcy in varchar2,
                          i_adres_odbiorcy in varchar2,
                          i_tytul_przelewu in varchar2,
                          i_kwota_przelewu in number,
                          i_zlecona_data_transakcji in varchar2,
                          i_typ_przelewu in int
                          )
as begin DECLARE
    L_RACHUNEK_DROGA number := 0;
    l_id_rachunek_nadawcy INT := -1;
    l_id_rachunek_odbiorcy INT := -1;
    l_i_zlecona_data_transakcji Date;
begin
  l_i_zlecona_data_transakcji := TO_DATE (i_zlecona_data_transakcji, 'ddmmyyyy');
   -- sprawdzenie poprawnosci wprowadzonych danych do przelewu
   --SELECT fn_rachunek_istnieje(i_rachunek_nadawcy) INTO l_id_rachunek_nadawcy FROM dual;
   l_id_rachunek_nadawcy := fn_rachunek_istnieje(i_rachunek_nadawcy);
   dbms_output.put_line('l_id_rachunek_nadawcy ' || l_id_rachunek_nadawcy);
   if l_id_rachunek_nadawcy = -1  then
      dbms_output.put_line('l_id_rachunek_nadawcy exit');
       res := 1;
       return;
    end if;
   l_id_rachunek_odbiorcy := fn_rachunek_istnieje(i_rachunek_odbiorcy);
   dbms_output.put_line('l_id_rachunek_odbiorcy ' || l_id_rachunek_odbiorcy);
   if l_id_rachunek_odbiorcy = -1  then
       res := 2;
       return;
   end if; 
   if not fn_prawidlowa_nazwa_odbiorcy(i_rachunek_odbiorcy, i_nazwa_odbiorcy) then
       res := 3;
       return;
    end if;    
   if not fn_prawidlowy_adres_odbiorcy(i_rachunek_odbiorcy, i_adres_odbiorcy) then
       res := 4;
       return;
    end if;
   if not fn_wystarczajace_srodki(i_rachunek_nadawcy, i_kwota_przelewu) then
       res := 5;
       return;
    end if;    
   if not trunc(l_i_zlecona_data_transakcji) >= trunc(sysdate) then
       res := 6;
       return;
   end if;
   if not i_kwota_przelewu > 0 then
       res := 7;
       return;
      end if;  
   
       -- przeniesienie srodkow potrzebnych do realizacji przelewu do r. DROGA
       update rachunki
          set saldo = saldo - i_kwota_przelewu
        where numer = i_rachunek_nadawcy;
      
       update rachunki
          set saldo = saldo + i_kwota_przelewu
        where numer = L_RACHUNEK_DROGA;
      
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
           id_klient_zrodlo,
           id_rachunku_zrodla
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
           l_i_zlecona_data_transakcji,
           i_typ_przelewu,
           id_klient_rachunek_wysylajacy,
           l_id_rachunek_nadawcy
           
       );
       res := 10;
   
  
end;


create or replace
procedure pr_przetwarzanie_przel_oczek(i_przetwarzany_dzien date) is
  CURSOR l_oczekujacy_kursor is
    SELECT * FROM przelew_wych_oczekujacy
    where trunc(DATA_REALIZACJI) = trunc(i_przetwarzany_dzien);
    
  przelew_oczekujacy   przelew_wych_oczekujacy%ROWTYPE;
begin
 OPEN l_oczekujacy_kursor;
   LOOP
      FETCH l_oczekujacy_kursor into przelew_oczekujacy;
      EXIT WHEN l_oczekujacy_kursor%notfound;
      
      UPDATE rachunki SET SALDO = SALDO - przelew_oczekujacy.KWOTA WHERE rachunki.id_rachunek = przelew_oczekujacy.ID_RACHUNKU_ZRODLA;
      UPDATE rachunki SET SALDO = SALDO + przelew_oczekujacy.KWOTA WHERE rachunki.NUMER = przelew_oczekujacy.NR_RACHUNKU_DOCELOWEGO;
      INSERT INTO przelew_wych_zrealizowany VALUES(
        seq_przelew_wych_zrealizowany.nextval,
        przelew_oczekujacy.NR_RACHUNKU_DOCELOWEGO,
        przelew_oczekujacy.NAZWA_ODBIORCY,
        przelew_oczekujacy.TYTUL,
        przelew_oczekujacy.ADRES,
        przelew_oczekujacy.KWOTA,
        przelew_oczekujacy.DATA_PRZYJECIA_DO_REALIZACJI,
        przelew_oczekujacy.DATA_REALIZACJI,
        przelew_oczekujacy.ID_TYP_PRZELEWU,
        przelew_oczekujacy.ID_KLIENT_ZRODLO, 
        przelew_oczekujacy.ID_RACHUNKU_ZRODLA
        );
        
   END LOOP;
   CLOSE l_oczekujacy_kursor;
   DELETE FROM przelew_wych_oczekujacy WHERE trunc(DATA_REALIZACJI) = trunc(i_przetwarzany_dzien);
   commit;

    exception
    when others then
      rollback;
end;
