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
/