create or replace
PROCEDURE pr_dodaj_polecenie(	res out int,
												i_id_klient IN INT,
												i_nazwa IN VARCHAR2,
												i_rachunek IN VARCHAR2,
												i_rachunek_upow IN VARCHAR2,
												i_od_kiedy IN VARCHAR2,
												i_do_kiedy IN VARCHAR2,
												i_maksymalna_kwota IN DECIMAL
												)
AS BEGIN DECLARE
    l_id_rachunek_upow INT := -1;
    l_id_rachunek INT := -1;
    l_od_kiedy Date;
    l_do_kiedy Date;
BEGIN
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
	l_od_kiedy := TO_DATE (i_od_kiedy, 'dd/mm/yyyy');
	IF NOT trunc(l_od_kiedy) >= trunc(sysdate) THEN
		res := 3;
		RETURN;
	END IF;
	l_do_kiedy := TO_DATE (i_do_kiedy, 'dd/mm/yyyy');
	IF NOT trunc(l_do_kiedy) >= trunc(l_od_kiedy) THEN
		res := 4;
		RETURN;
	END IF;
	IF i_maksymalna_kwota < 0 THEN
		res := 5;
		RETURN;
	END IF;
	
	INSERT INTO upowaznienie_polecenia_zapl VALUES(
		seq_polecenie_zaplaty.nextval,
		i_nazwa,
		i_rachunek_upow,
		l_od_kiedy,
		l_do_kiedy,
		i_maksymalna_kwota,
		i_id_klient,
		l_id_rachunek
		);
	res := 10;
END;
END;
/