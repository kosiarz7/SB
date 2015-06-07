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
/