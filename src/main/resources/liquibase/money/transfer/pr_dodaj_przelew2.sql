--liquibase formatted sql

--changeset samoSieZrobilo:10 endDelimiter:"/"
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
  l_i_zlecona_data_transakcji := TO_DATE (i_zlecona_data_transakcji, 'dd/mm/yyyy');
   -- sprawdzenie poprawnosci wprowadzonych danych do przelewu
   --SELECT fn_rachunek_istnieje(i_rachunek_nadawcy) INTO l_id_rachunek_nadawcy FROM dual;
   l_id_rachunek_nadawcy := fn_rachunek_istnieje(i_rachunek_nadawcy);

   if l_id_rachunek_nadawcy = -1  then
       res := 1;
       return;
    end if;
   l_id_rachunek_odbiorcy := fn_rachunek_istnieje(i_rachunek_odbiorcy);
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
END;
/