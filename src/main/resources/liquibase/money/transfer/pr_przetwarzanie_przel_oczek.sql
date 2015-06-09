create or replace
PROCEDURE pr_przetwarzanie_przel_oczek(i_przetwarzany_dzien DATE) IS
	CURSOR l_oczekujacy_kursor IS
		SELECT * FROM przelew_wych_oczekujacy
		WHERE trunc(DATA_REALIZACJI) = trunc(i_przetwarzany_dzien);
    
	przelew_oczekujacy przelew_wych_oczekujacy%ROWTYPE;
	blad INT := 0;
  l_id_rachunek_odbiorcy INT;
BEGIN
	OPEN l_oczekujacy_kursor;
	LOOP
		FETCH l_oczekujacy_kursor into przelew_oczekujacy;
		EXIT WHEN l_oczekujacy_kursor%notfound;
      
		l_id_rachunek_odbiorcy := fn_rachunek_istnieje(przelew_oczekujacy.nr_rachunku_docelowego);
		IF l_id_rachunek_odbiorcy = -1 THEN
			-- nie istnieje rachunek docelowy
			blad := 1;
		END IF; 
		IF NOT fn_prawidlowa_nazwa_odbiorcy(przelew_oczekujacy.nr_rachunku_docelowego, przelew_oczekujacy.nazwa_odbiorcy) THEN
			blad := 3;
		END IF;    
		IF NOT fn_prawidlowy_adres_odbiorcy(przelew_oczekujacy.nr_rachunku_docelowego, przelew_oczekujacy.adres) THEN
			blad := 4;
		END IF;
		IF NOT fn_wystarczajace_srodki_id(przelew_oczekujacy.id_rachunku_zrodla, przelew_oczekujacy.kwota) THEN
			blad := 5;
		END IF; 
		IF NOT przelew_oczekujacy.kwota > 0 THEN
			blad := 7;
		END IF;  
      
		IF blad = 0 THEN
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
		ELSE
			INSERT INTO przelew_niezrealizowany VALUES(
				seq_przelew_wych_nzrealizowany.nextval,
				przelew_oczekujacy.NR_RACHUNKU_DOCELOWEGO,
				przelew_oczekujacy.NAZWA_ODBIORCY,
				przelew_oczekujacy.TYTUL,
				przelew_oczekujacy.ADRES,
				przelew_oczekujacy.KWOTA,
				przelew_oczekujacy.DATA_PRZYJECIA_DO_REALIZACJI,
				przelew_oczekujacy.DATA_REALIZACJI,
				przelew_oczekujacy.ID_TYP_PRZELEWU,
				przelew_oczekujacy.ID_KLIENT_ZRODLO, 
				przelew_oczekujacy.ID_RACHUNKU_ZRODLA,
				blad
				);
		END IF;
        
	END LOOP;
	CLOSE l_oczekujacy_kursor;
	DELETE FROM przelew_wych_oczekujacy WHERE trunc(DATA_REALIZACJI) = trunc(i_przetwarzany_dzien);
	COMMIT;

    EXCEPTION
    WHEN OTHERS THEN
		ROLLBACK;
END;
