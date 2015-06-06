--liquibase formatted sql

--changeset jakasnazwa:7
CREATE TABLE rodzaj_kredytu(
id_rodzaju INT PRIMARY KEY NOT NULL,
nazwa VARCHAR(64) NOT NULL
);
 
CREATE TABLE kredyt
(
id_kredytu INT PRIMARY KEY,
nazwa VARCHAR2(64) NOT NULL,
czas_trwania INT NOT NULL,
spłata_na_raz INT NOT NULL, --zamiast BOOL dalem INT bedzie sie dawalo 1 lub 0 i rozroznialo
kapital_początkowy INT NOT NULL,
kapital_spłacony INT NOT NULL,
odsetki INT NOT NULL,
odsetki_splacone INT NOT NULL,
odsetki_karne INT NOT NULL,
data_rozp_oferty DATE,
data_zako_oferty DATE,
koszt_ubezpieczenia INT,
id_rodzaju INT NOT NULL,
id_klient INT NOT NULL,
CONSTRAINT klient_fk FOREIGN KEY (id_klient) REFERENCES klienci(id_klient),
CONSTRAINT rodzaj_kredytu_fk FOREIGN KEY (id_rodzaju) REFERENCES rodzaj_kredytu(id_rodzaju)
);
 
CREATE TABLE rachunek_kredytowy
(
id_rachunek INT PRIMARY KEY,
id_klient INT NOT NULL,
id_kredytu INT NOT NULL,
CONSTRAINT klient_rachunek_fk FOREIGN KEY (id_klient) REFERENCES klienci(id_klient),
CONSTRAINT kredyt_rachunek_fk FOREIGN KEY (id_kredytu) REFERENCES kredyt(id_kredytu)
);
 
CREATE TABLE raty_kredytu(
id_raty INT PRIMARY KEY NOT NULL,
numer_raty INT NOT NULL,
kapital INT NOT NULL,
odsetki INT NOT NULL,
data_planowa_wplaty DATE NOT NULL,
data_wplaty_raty DATE NOT NULL,
odsetki_karne INT NOT NULL,
id_kredytu INT NOT NULL,
CONSTRAINT kredyt_raty_fk FOREIGN KEY (id_kredytu) REFERENCES kredyt(id_kredytu)
);
 
CREATE TABLE oprocentowanie(
id_oprocentowanie INT PRIMARY KEY,
data_od DATE NOT NULL,
data_do DATE NOT NULL,
id_kredytu INT NOT NULL,
CONSTRAINT kredyt_oprocentowanie_fk FOREIGN KEY (id_kredytu) REFERENCES kredyt(id_kredytu)
);

   CREATE SEQUENCE RACHUNEK_KREDYTOWY_SEQ START WITH 1 INCREMENT BY 1;
   CREATE SEQUENCE KREDYT_SEQ START WITH 1 INCREMENT BY 1;
   CREATE SEQUENCE RODZAJ_KREDYTU_SEQ START WITH 1 INCREMENT BY 1;
   CREATE SEQUENCE RATY_KREDYTU_SEQ START WITH 1 INCREMENT BY 1;
   CREATE SEQUENCE OPROCENTOWANIE_SEQ START WITH 1 INCREMENT BY 1;