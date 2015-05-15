-- OBYWATELSTWA
INSERT INTO obywatelstwa(id_obywatelstwa, obywatelstwo) VALUES (1, 'polskie');
-- UPRAWNIENIA
INSERT INTO uprawnienia(id, name) VALUES (1, 'USER');

-- UŻYTKOWNICY
-- hasło jest takie same jak login
INSERT INTO klienci(id_klient, imie, nazwisko, login, haslo, adres, kod_pocztowy, miasto, pesel, nr_dowodu, id_obywatelstwa, email) VALUES
(1, 'Jan', 'Kowalski', 'user', 
UPPER('b14361404c078ffd549c03db443c3fede2f3e534d73f78f77301ed97d4a436a9fd9db05ee8b325c0ad36438b43fec8510c204fc1c1edb21d0941c00e9e2c1ce2'),
'Ozorkowska 21', '87-789', 'Wałbrzych', '12345678901', 'ADS-212425', 1, 'maiil@mail.pl');

-- UPRAWNIENIA UŻYTKOWNIKÓW
INSERT INTO uprawnienia_klientow(id, klient_id, uprawnienie_id) VALUES (1, 1, 1);
COMMIT;