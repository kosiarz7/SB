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
'Przenosisz swoje zni¿ki za bezszkodow¹ jazdê. Przenosisz zni¿ki z OC na AC. 5% zni¿ki przy zakupie ubezpieczenia przez internet. P³acisz niskie, miesiêczne raty, o których pamiêta za Ciebie bank.', 
'Mo¿esz kupiæ polisê nawet z 3-miesiêcznym wyprzedzeniem. Ubezpieczenie kupujesz przez internet lub telefon, bez wychodzenia z domu.');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (2, 'Ubezpieczenie turystyczne', 'zdrowotne', 
'Wybór jednego z trzech wariantów w formie indywidualnej, rodzinnej lub grupowej. Ochrona ju¿ po dwóch godzinach od z³o¿enia wniosku. Numer polisy wysy³any SMS-em.',
'Ubezpieczenie obejmuje m.in.: koszty leczenia, koszty transportu medycznego, NNW, OC. Terytorium: Europa lub ca³y œwiat.');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (3, 'Waleczni', 'zdrowotne', 
'Finansowe zabezpieczenie na wypadek nowotworu, w przypadku braku dochodów i dodatkowych kosztów m.in. leków, dotarcia do g³ównych oœrodków diagnostycznych, specjalistycznych klinik, rehabilitacji.',
'Nie wymagamy badañ lekarskich. Przyst¹pienie do umowy nie wymaga Twojego podpisu. Miesiêczna kwota za ubezpieczenia bêdzie pobierana automatycznie z konta.');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (4, 'Ubezpieczenie nieruchomoœci', 'nieruchomoœci',
'Wysoka suma ubezpieczenia na wyposa¿enie. Niskie miesiêczne op³aty, o których pamiêtamy za Ciebie.',
'Mo¿liwoœæ zakupu polisy nawet z 90-o dniowym wyprzedzeniem. Wys³anie polisy na adres e-mail w ci¹gu doby od zakupu.');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (5, 'Mieszkanie 24h', 'nieruchomoœci',
'Mo¿liwoœæ wyboru jednego z 3 wariantów ubezpieczenia. Niska cena - miesiêczna op³ata ju¿ od 19,90 PLN.',
'Uzyskasz odszkodowanie, gdy Twoje mieszkanie zostanie zalane lub strawi je po¿ar. Odzyskasz œrodki, jeœli padniesz ofiar¹ kradzie¿y, rabunku lub dewastacji. Nie poniesiesz kosztów, je¿eli zalejesz s¹siada lub spowodujesz inn¹ szkodê.');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (6, 'NNW Ochrona 24h', 'zdrowotne',
'Podstawowa ochrona ju¿ od 3,99 PLN miesiêcznie. Wysokie sumy ubezpieczenia, nawet 200 000 PLN.',
'Wyp³ata œwiadczenia w przypadku pobytu w szpitalu lub œmierci w wyniku nieszczêœliwego wypadku (w tym w œrodku lokomocji).');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (7, 'Assistance Dom 24h', 'nieruchomoœci',
'Pomoc w przypadku nag³ej awarii, w³amania, kradzie¿y, po¿aru lub innych, nieprzewidzianych okolicznoœci w miejscu zamieszkania.',
'Szybka pomoc w przypadku uszkodzenia mienia w miejscu zamieszkania wskutek zdarzenia losowego lub kradzie¿y z w³amaniem.');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (8, 'Bezpieczna karta', 'karty',
'Ochrona w zakresie nieuprawnionego u¿ycia karty i utraty œrodków pieniê¿nych.',
'Transakcje dokonane przed zastrze¿eniem karty oraz nieuprawnione transakcje przy u¿yciu numeru PIN. Obejmuje utratê ubezpieczonej gotówki do 2h od jej podjêcia.');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (9, 'Ubezpieczenie splaty kredytu', 'kredytu',
'Zawarcie ubezpieczenia umo¿liwa negocjowanie oprocentowania kredytu gotówkowego i prowizji za udzielenie. Poczucie finansowego bezpieczeñstwa - realna i bezzwrotna pomoc finansowa',
'Obejmuje calkowit¹ trwala niezdolnoœæ do pracy w wyniku choroby lub nieszczeœliwego wypadku, pobyt w szpitalu, powa¿ne zachorowania i operacje chirurgiczne.');
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