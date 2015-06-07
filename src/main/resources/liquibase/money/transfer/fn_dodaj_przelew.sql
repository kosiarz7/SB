--liquibase formatted sql

--changeset samoSieZrobilo:9 endDelimiter:"/"
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
/