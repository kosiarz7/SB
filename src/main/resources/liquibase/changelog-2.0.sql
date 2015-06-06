--liquibase formatted sql

--changeset samoSieZrobilo:2
CREATE TABLE typUbezpieczenie(
	id_typUbezpieczenie integer  NOT NULL,
  	nazwa varchar2(50)  NOT NULL,
  	typ varchar2(50)  NOT NULL,
	opis_korzysci varchar2(255)  NOT NULL,
  	opis_inne varchar2(255) NOT NULL,
	CONSTRAINT typUbezpieczenie_pk PRIMARY KEY (id_typUbezpieczenie)
);

INSERT INTO typ_przelewu VALUES(1, 'Przelew jednorazowy');
INSERT INTO typ_przelewu VALUES(2, 'Polecenie zapłaty');

INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (1, 'Ubezpieczenie komunikacyjne', 'komunikacyjne',
'Przenosisz swoje zniżki za bezszkodową jazdę. Przenosisz zniżki z OC na AC. 5% zniżki przy zakupie ubezpieczenia przez internet. Płacisz niskie, miesięczne raty, o których pamięta za Ciebie bank.',
'Możesz kupić polisę nawet z 3-miesięcznym wyprzedzeniem. Ubezpieczenie kupujesz przez internet lub telefon, bez wychodzenia z domu.');
INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (2, 'Ubezpieczenie turystyczne', 'zdrowotne',
'Wybór jednego z trzech wariantów w formie indywidualnej, rodzinnej lub grupowej. Ochrona już po dwóch godzinach od złożenia wniosku. Numer polisy wysyłany SMS-em.',
'Ubezpieczenie obejmuje m.in.: koszty leczenia, koszty transportu medycznego, NNW, OC. Terytorium: Europa lub cały świat.');
INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (3, 'Waleczni', 'zdrowotne',
'Finansowe zabezpieczenie na wypadek nowotworu, w przypadku braku dochodów i dodatkowych kosztów m.in. leków, dotarcia do głównych ośrodków diagnostycznych, specjalistycznych klinik, rehabilitacji.',
'Nie wymagamy badań lekarskich. Przystąpienie do umowy nie wymaga Twojego podpisu. Miesięczna kwota za ubezpieczenia będzie pobierana automatycznie z konta.');
INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (4, 'Ubezpieczenie nieruchomości', 'nieruchomości',
'Wysoka suma ubezpieczenia na wyposażenie. Niskie miesięczne opłaty, o których pamiętamy za Ciebie.',
'Możliwość zakupu polisy nawet z 90-o dniowym wyprzedzeniem. Wysłanie polisy na adres e-mail w ciągu doby od zakupu.');
INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (5, 'Mieszkanie 24h', 'nieruchomości',
'Możliwość wyboru jednego z 3 wariantów ubezpieczenia. Niska cena - miesięczna opłata już od 19,90 PLN.',
'Uzyskasz odszkodowanie, gdy Twoje mieszkanie zostanie zalane lub strawi je pożar. Odzyskasz środki, jeśli padniesz ofiarą kradzieży, rabunku lub dewastacji. Nie poniesiesz kosztów, jeżeli zalejesz sąsiada lub spowodujesz inną szkodę.');
INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (6, 'NNW Ochrona 24h', 'zdrowotne',
'Podstawowa ochrona już od 3,99 PLN miesięcznie. Wysokie sumy ubezpieczenia, nawet 200 000 PLN.',
'Wypłata świadczenia w przypadku pobytu w szpitalu lub śmierci w wyniku nieszczęśliwego wypadku (w tym w środku lokomocji).');
INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (7, 'Assistance Dom 24h', 'nieruchomości',
'Pomoc w przypadku nagłej awarii, włamania, kradzieży, pożaru lub innych, nieprzewidzianych okoliczności w miejscu zamieszkania.',
'Szybka pomoc w przypadku uszkodzenia mienia w miejscu zamieszkania wskutek zdarzenia losowego lub kradzieży z włamaniem.');
INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (8, 'Bezpieczna karta', 'karty',
'Ochrona w zakresie nieuprawnionego użycia karty i utraty środków pieniężnych.',
'Transakcje dokonane przed zastrzeżeniem karty oraz nieuprawnione transakcje przy użyciu numeru PIN. Obejmuje utratę ubezpieczonej gotówki do 2h od jej podjęcia.');
INSERT INTO typUbezpieczenie (id_typUbezpieczenie, nazwa, typ, opis_korzysci, opis_inne) VALUES (9, 'Ubezpieczenie splaty kredytu', 'kredytu',
'Zawarcie ubezpieczenia umożliwa negocjowanie oprocentowania kredytu gotówkowego i prowizji za udzielenie. Poczucie finansowego bezpieczeństwa - realna i bezzwrotna pomoc finansowa',
'Obejmuje calkowitą trwala niezdolność do pracy w wyniku choroby lub nieszcześliwego wypadku, pobyt w szpitalu, poważne zachorowania i operacje chirurgiczne.');