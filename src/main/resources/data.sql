-- OBYWATELSTWA
INSERT INTO obywatelstwa(id_obywatelstwa, obywatelstwo) VALUES (1, 'polskie');

COMMIT;

-- UPRAWNIENIA
INSERT INTO uprawnienia(id, name) VALUES (1, 'USER');

COMMIT;

-- SEKWENCJE
CREATE SEQUENCE USER_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE ACCOUNT_SEQ START WITH 1 INCREMENT BY 1;
COMMIT;

--UBEZPIECZENIA
INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (1, 'Ubezpieczenie komunikacyjne', 'komunikacyjne', 
'Przenosisz swoje zni�ki za bezszkodow� jazd�. Przenosisz zni�ki z OC na AC. 5% zni�ki przy zakupie ubezpieczenia przez internet. P�acisz niskie, miesi�czne raty, o kt�rych pami�ta za Ciebie bank.', 
'Mo�esz kupi� polis� nawet z 3-miesi�cznym wyprzedzeniem. Ubezpieczenie kupujesz przez internet lub telefon, bez wychodzenia z domu.');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (2, 'Ubezpieczenie turystyczne', 'zdrowotne', 
'Wyb�r jednego z trzech wariant�w w formie indywidualnej, rodzinnej lub grupowej. Ochrona ju� po dw�ch godzinach od z�o�enia wniosku. Numer polisy wysy�any SMS-em.',
'Ubezpieczenie obejmuje m.in.: koszty leczenia, koszty transportu medycznego, NNW, OC. Terytorium: Europa lub ca�y �wiat.');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (3, 'Waleczni', 'zdrowotne', 
'Finansowe zabezpieczenie na wypadek nowotworu, w przypadku braku dochod�w i dodatkowych koszt�w m.in. lek�w, dotarcia do g��wnych o�rodk�w diagnostycznych, specjalistycznych klinik, rehabilitacji.',
'Nie wymagamy bada� lekarskich. Przyst�pienie do umowy nie wymaga Twojego podpisu. Miesi�czna kwota za ubezpieczenia b�dzie pobierana automatycznie z konta.');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (4, 'Ubezpieczenie nieruchomo�ci', 'nieruchomo�ci',
'Wysoka suma ubezpieczenia na wyposa�enie. Niskie miesi�czne op�aty, o kt�rych pami�tamy za Ciebie.',
'Mo�liwo�� zakupu polisy nawet z 90-o dniowym wyprzedzeniem. Wys�anie polisy na adres e-mail w ci�gu doby od zakupu.');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (5, 'Mieszkanie 24h', 'nieruchomo�ci',
'Mo�liwo�� wyboru jednego z 3 wariant�w ubezpieczenia. Niska cena - miesi�czna op�ata ju� od 19,90 PLN.',
'Uzyskasz odszkodowanie, gdy Twoje mieszkanie zostanie zalane lub strawi je po�ar. Odzyskasz �rodki, je�li padniesz ofiar� kradzie�y, rabunku lub dewastacji. Nie poniesiesz koszt�w, je�eli zalejesz s�siada lub spowodujesz inn� szkod�.');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (6, 'NNW Ochrona 24h', 'zdrowotne',
'Podstawowa ochrona ju� od 3,99 PLN miesi�cznie. Wysokie sumy ubezpieczenia, nawet 200 000 PLN.',
'Wyp�ata �wiadczenia w przypadku pobytu w szpitalu lub �mierci w wyniku nieszcz�liwego wypadku (w tym w �rodku lokomocji).');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (7, 'Assistance Dom 24h', 'nieruchomo�ci',
'Pomoc w przypadku nag�ej awarii, w�amania, kradzie�y, po�aru lub innych, nieprzewidzianych okoliczno�ci w miejscu zamieszkania.',
'Szybka pomoc w przypadku uszkodzenia mienia w miejscu zamieszkania wskutek zdarzenia losowego lub kradzie�y z w�amaniem.');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (8, 'Bezpieczna karta', 'karty',
'Ochrona w zakresie nieuprawnionego u�ycia karty i utraty �rodk�w pieni�nych.',
'Transakcje dokonane przed zastrze�eniem karty oraz nieuprawnione transakcje przy u�yciu numeru PIN. Obejmuje utrat� ubezpieczonej got�wki do 2h od jej podj�cia.');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (9, 'Ubezpieczenie splaty kredytu', 'kredytu',
'Zawarcie ubezpieczenia umo�liwa negocjowanie oprocentowania kredytu got�wkowego i prowizji za udzielenie. Poczucie finansowego bezpiecze�stwa - realna i bezzwrotna pomoc finansowa',
'Obejmuje calkowit� trwala niezdolno�� do pracy w wyniku choroby lub nieszcze�liwego wypadku, pobyt w szpitalu, powa�ne zachorowania i operacje chirurgiczne.');
COMMIT;

CREATE SEQUENCE INSURANCE_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE INS_ACC_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE TRANS_INS_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE UBEZPIECZENIESPLATA_SEQ START WITH 1 INCREMENT BY 1;
COMMIT;

CREATE or REPLACE TRIGGER ubezpieczenieSplata_trigger 
AFTER INSERT ON ubezpieczenie 
FOR EACH ROW 
DECLARE
dataS DATE;
kwotaRaty NUMBER(38,5);
BEGIN 

IF (:new.platnoscratalna = 1) THEN
  kwotaRaty := TRUNC(:new.wartosc/4);
  dataS := :new.datapoczatku;
  FOR rataCounter IN 1..4
  LOOP
    dataS := ADD_MONTHS(dataS,3);
 
    IF rataCounter = 4 THEN
      kwotaRaty :=kwotaRaty + (:new.wartosc MOD 4);
    END IF;
    
    INSERT INTO ubezpieczenieSplata ( ID_UBEZPIECZENIESPLATA, ID_UBEZPIECZENIE, DATASPLATY, RATA, STATUS ) 
            VALUES (UBEZPIECZENIESPLATA_SEQ.NEXTVAL, :new.id_ubezpieczenie, dataS, kwotaRaty, 'Oczekujaca');
             
  END LOOP;
ELSE
  dataS := ADD_MONTHS(:new.datapoczatku,1);
  kwotaRaty := :new.wartosc;
  
     INSERT INTO ubezpieczenieSplata ( ID_UBEZPIECZENIESPLATA, ID_UBEZPIECZENIE, DATASPLATY, RATA, STATUS ) 
            VALUES (UBEZPIECZENIESPLATA_SEQ.NEXTVAL, :new.id_ubezpieczenie, dataS, kwotaRaty, 'Oczekujaca');
END IF;  

END; 
COMMIT;

CREATE OR REPLACE PROCEDURE pobierzSkladkeUbez
AS
  CURSOR splaty_cur IS
  SELECT u.id_ubezpieczenie,
  rau.id_rachunekubezpieczenia as id_ubez,  
  rau.saldo as saldo_ubez,
  rach.id_rachunek as id_rach,
  rach.saldo as saldo_rach,
  us.id_ubezpieczeniesplata,
  us.datasplaty as data_splaty,
  us.rata as rata
  FROM ubezpieczenie u,
  rachunekubezpieczenia rau,
  rachunki rach,
  (SELECT id_ubezpieczeniesplata, id_ubezpieczenie, datasplaty, rata, status FROM ubezpieczeniesplata WHERE status = 'Oczekujaca' OR status = 'Zalegla') us             
  WHERE (u.id_ubezpieczenie = us.id_ubezpieczenie) AND
      (u.id_rachunekubezpieczenia = rau.id_rachunekubezpieczenia) AND
      (u.id_rachuneksplaty = rach.id_rachunek);
  
  l_splata_var splaty_cur%ROWTYPE;
BEGIN
  OPEN splaty_cur;
     LOOP
        FETCH splaty_cur INTO l_splata_var;
        EXIT WHEN splaty_cur%NOTFOUND;
        
        IF l_splata_var.data_splaty <= SYSDATE THEN
        --jesli jest z czego splacic rate
          IF  l_splata_var.rata <= l_splata_var.saldo_rach THEN
              --insert do operacji przelewu 
              UPDATE rachunki SET saldo = saldo - l_splata_var.rata WHERE id_rachunek = l_splata_var.id_rach;
              UPDATE rachunekubezpieczenia SET saldo = saldo + l_splata_var.rata WHERE id_rachunekubezpieczenia = l_splata_var.id_ubez;
              UPDATE ubezpieczeniesplata SET status = 'Zaplacona' WHERE id_ubezpieczeniesplata = l_splata_var.id_ubezpieczeniesplata;
          ELSE
              UPDATE ubezpieczeniesplata SET status = 'Zalegla' WHERE id_ubezpieczeniesplata = l_splata_var.id_ubezpieczeniesplata;
          END IF;
        END IF;
     END LOOP;
  CLOSE splaty_cur;   
END;