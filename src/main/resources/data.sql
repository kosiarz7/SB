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
