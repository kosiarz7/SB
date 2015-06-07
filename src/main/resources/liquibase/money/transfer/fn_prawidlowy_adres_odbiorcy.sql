--liquibase formatted sql

--changeset samoSieZrobilo:7 endDelimiter:"/"
CREATE OR REPLACE FUNCTION fn_prawidlowy_adres_odbiorcy(i_numer_rachunku IN VARCHAR2,
                                                        i_adres_odbiorcy IN VARCHAR2) RETURN boolean IS

   l_liczba_adresow INTEGER;
   pocz INTEGER := 0;
   space_poz INTEGER := 300;
   it INTEGER := 1;
   adres_token VARCHAR2(20);
   name_array dbms_sql.varchar2_table;
BEGIN
  WHILE space_poz != 0 LOOP
    SELECT INSTR(i_adres_odbiorcy,' ', 1, it) INTO space_poz FROM dual;
    IF (space_poz != 0) AND space_poz - pocz > 1 THEN
      SELECT SUBSTR(i_adres_odbiorcy, pocz + 1, space_poz - pocz - 1) INTO adres_token FROM dual; 
      DBMS_OUTPUT.put_line('pocz=' || pocz || ' space_poz=' || space_poz || ' adres_token=' || adres_token);
      pocz := space_poz;
      it := it + 1;
      
      SELECT COUNT(*) INTO l_liczba_adresow FROM 
				rachunki r,
				klienci_rachunki kr,
				klienci k,
				rachunki r2
				WHERE kr.id_klient = k.id_klient AND 
					kr.id_rachunek = r.id_rachunek AND 
					r.id_rachunek = r2.id_rachunek AND 
					r2.numer = i_numer_rachunku  AND 
					(trim(UPPER(k.adres)) LIKE '%' || trim(UPPER(adres_token)) || '%'
						OR trim(UPPER(k.miasto)) LIKE '%' || trim(UPPER(adres_token)) || '%' 
            OR trim(UPPER(adres_token)) LIKE '%' || trim(UPPER(k.adres)) || '%'
            OR trim(UPPER(adres_token)) LIKE '%' || trim(UPPER(k.miasto)) || '%' );
     
			IF l_liczba_adresow > 0 THEN
				RETURN TRUE;
			END IF;
    END IF;
  END LOOP;
	IF it = 1 THEN -- bylo tylko jedno slowo; wiem ze mozna to wciagnac w powyzszy while, ale nie chce mi sie
    SELECT COUNT(*) INTO l_liczba_adresow FROM 
				rachunki r,
				klienci_rachunki kr,
				klienci k,
				rachunki r2
				WHERE kr.id_klient = k.id_klient AND 
					kr.id_rachunek = r.id_rachunek AND 
					r.id_rachunek = r2.id_rachunek AND 
					r2.numer = i_numer_rachunku   AND 
					(trim(UPPER(k.adres)) LIKE '%' || trim(UPPER(adres_token)) || '%'
						OR trim(UPPER(k.miasto)) LIKE '%' || trim(UPPER(adres_token)) || '%' 
            OR trim(UPPER(adres_token)) LIKE '%' || trim(UPPER(k.adres)) || '%'
            OR trim(UPPER(adres_token)) LIKE '%' || trim(UPPER(k.miasto)) || '%' );
     
			IF l_liczba_adresow > 0 THEN
				RETURN TRUE;
			END IF;
  END IF;
	RETURN FALSE;
		
	
	EXCEPTION	WHEN OTHERS THEN
    RETURN FALSE;   
END;
/