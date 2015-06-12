create or replace
procedure pr_zmien_polecenie (res OUT INT, 
                              i_id_upowaznienie IN INT,
                              i_id_klient IN INT,
                              i_nazwa IN VARCHAR2,
                              i_rachunek IN VARCHAR2,
                              i_rachunek_upow IN VARCHAR2,
                              i_od_kiedy IN VARCHAR2,
                              i_do_kiedy IN VARCHAR2,
                              i_maksymalna_kwota IN DECIMAL)
as
    l_id_rachunek_upow INT := -1;
    l_id_rachunek INT := -1;
    l_od_kiedy DATE;
    l_do_kiedy DATE;
begin
    l_od_kiedy := TO_DATE (i_od_kiedy, 'dd/mm/yyyy');
  l_do_kiedy := TO_DATE (i_do_kiedy, 'dd/mm/yyyy');
  
    l_id_rachunek_upow := fn_rachunek_istnieje(i_rachunek_upow);
    IF l_id_rachunek_upow = -1 THEN
        res := 1;
        RETURN;
    END IF;
  
    l_id_rachunek := fn_rachunek_istnieje(i_rachunek);
    IF l_id_rachunek = -1 THEN
        res := 2;
        RETURN;
    END IF;
  
    IF NOT trunc(l_do_kiedy) >= trunc(l_od_kiedy) THEN
        res := 4;
        RETURN;
    END IF;
  
    IF i_maksymalna_kwota < 0 THEN
        res := 5;
        RETURN;
    END IF;
  
  UPDATE upowaznienie_polecenia_zapl
     SET nazwa = i_nazwa,
         nr_rachunku_upowaznionego = i_rachunek_upow,
         do_kiedy = l_do_kiedy,
         maksymalna_kwota = i_maksymalna_kwota
   WHERE id_upowaznienie = i_id_upowaznienie;

    res := 10; -- TODO?

end pr_zmien_polecenie;
/