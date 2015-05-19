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
