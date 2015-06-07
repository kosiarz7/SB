--liquibase formatted sql

--changeset samoSieZrobilo:1
CREATE TABLE obywatelstwa (
  id_obywatelstwa INT PRIMARY KEY,
  obywatelstwo VARCHAR2(64) NOT NULL,
  CONSTRAINT obywatelstwa_dl_obywatelstwa CHECK (LENGTH(obywatelstwo) > 4)
);

CREATE TABLE klienci (
id_klient INT PRIMARY KEY,
  imie VARCHAR2(64) NOT NULL,
  nazwisko VARCHAR2(64) NOT NULL,
  login VARCHAR(100) NOT NULL,
  haslo VARCHAR(128) NOT NULL,
  nieudane_logowania INTEGER DEFAULT 0 NOT NULL,
  adres VARCHAR2(64) NOT NULL,
  kod_pocztowy VARCHAR2(6) NOT NULL,
  miasto VARCHAR2(32) NOT NULL,
  pesel VARCHAR2(11)  NOT NULL,
  nr_dowodu VARCHAR2(10) NOT NULL,
  id_obywatelstwa INT NOT NULL,
  email VARCHAR2(128) NOT NULL,
  CONSTRAINT klienci_dl_imie CHECK (LENGTH(imie) > 2),
  CONSTRAINT klienci_dl_nazwisko CHECK (LENGTH(nazwisko) > 2), 
  CONSTRAINT klienci_dl_adres CHECK (LENGTH(adres) > 5), 
  CONSTRAINT klienci_dl_kod_pocztowy CHECK (REGEXP_LIKE(kod_pocztowy, '^[0-9]{2}[-]{1}[0-9]{3}$')), 
  CONSTRAINT klienci_dl_miasto CHECK (LENGTH(miasto) > 2), 
  CONSTRAINT klienci_pesel CHECK (REGEXP_LIKE(pesel, '^[0-9]{11}$')), 
  CONSTRAINT klienci_nr_dowodu CHECK (REGEXP_LIKE(nr_dowodu, '^[A-Z]{3}[-]{1}[0-9]{6}$')),
  CONSTRAINT klienci_fk_obywatelstwo FOREIGN KEY (id_obywatelstwa) REFERENCES obywatelstwa(id_obywatelstwa)
);

CREATE TABLE rachunki (
  id_rachunek INT PRIMARY KEY,
  numer  VARCHAR2(26) NOT NULL,
  nazwa VARCHAR(255) NOT NULL,
  saldo DECIMAL(*, 5) NOT NULL ,
  enabled VARCHAR2(1) NOT NULL,
  data_zalozenia DATE NOT NULL
);

CREATE TABLE klienci_rachunki (
  id_klient INT NOT NULL,
  id_rachunek INT  NOT NULL,
  CONSTRAINT klienci_rachunki_fk_klient FOREIGN KEY (id_klient) REFERENCES klienci(id_klient),
  CONSTRAINT klienci_rachunki_fk_rachunek FOREIGN KEY (id_rachunek) REFERENCES rachunki(id_rachunek)
);

CREATE TABLE uprawnienia (
    id INTEGER NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE uprawnienia_klientow (
    klient_id INTEGER NOT NULL,
    uprawnienie_id INTEGER NOT NULL,
    CONSTRAINT uprawnienia_kli_00_klient_fk FOREIGN KEY (klient_id) REFERENCES klienci(id_klient),
    CONSTRAINT uprawnienia_kli_00_upraw_fk FOREIGN KEY (uprawnienie_id) REFERENCES uprawnienia(id)
);

-- Przelewy
CREATE TABLE upowaznienie_polecenia_zapl
(
  id_upowaznienie INT PRIMARY KEY,
  nazwa VARCHAR2(32) NOT NULL,
  nr_rachunku_upowaznionego VARCHAR2(26) NOT NULL,
  od_kiedy DATE NOT NULL,
  do_kiedy DATE NOT NULL,
  maksymalna_kwota DECIMAL(*, 5),
  id_klient INT NOT NULL,
  id_rachunek INT NOT NULL,
  CONSTRAINT upowaznienie_fk_id_klient FOREIGN KEY (id_klient) REFERENCES klienci(id_klient),
  CONSTRAINT upowaznienie_fk_id_rachunek FOREIGN KEY (id_rachunek) REFERENCES rachunki(id_rachunek),
  CONSTRAINT upowaznienie_maksymalna_kwota CHECK (maksymalna_kwota > 0)
);

-- nie wiem jak sprawdzac date: przyszla data, do_kiedy > od_kiedy 

CREATE TABLE typ_przelewu
(
  id_typ_przelewu INT PRIMARY KEY,
  nazwa_typu VARCHAR2(64) NOT NULL
);

CREATE TABLE przelew_wych_oczekujacy
(
  id_przelew_oczekujacy INT PRIMARY KEY,
  nr_rachunku_docelowego VARCHAR2(26) NOT NULL,
  nazwa_odbiorcy VARCHAR(128) NOT NULL,
  tytul VARCHAR2(64) NOT NULL,
  adres VARCHAR2(64) NOT NULL,
  kwota DECIMAL(*, 5) NOT NULL,
  data_przyjecia_do_realizacji DATE DEFAULT SYSDATE,
  data_realizacji DATE DEFAULT SYSDATE,
  id_typ_przelewu INT NOT NULL,
  id_klient_zrodlo INT NOT NULL,
  id_rachunku_zrodla INT NOT NULL,
  CONSTRAINT przelew_wych_o_fk_id_klient FOREIGN KEY (id_klient_zrodlo) REFERENCES klienci(id_klient),
  CONSTRAINT przelew_wych_o_id_rachunek FOREIGN KEY (id_rachunku_zrodla) REFERENCES rachunki(id_rachunek),
  CONSTRAINT przelew_wych_o_fk_typ FOREIGN KEY (id_typ_przelewu) REFERENCES typ_przelewu(id_typ_przelewu)
);

CREATE TABLE przelew_niezrealizowany
(
  id_niezrealizowany INT PRIMARY KEY,
  nr_rachunku_docelowego VARCHAR2(26) NOT NULL,
  nazwa_odbiorcy VARCHAR(128) NOT NULL,
  tytul VARCHAR2(64) NOT NULL,
  adres VARCHAR2(64) NOT NULL,
  kwota DECIMAL(*, 5) NOT NULL,
  data_przyjecia_do_realizacji DATE DEFAULT SYSDATE,
  data_realizacji DATE DEFAULT SYSDATE,
  id_typ_przelewu INT NOT NULL,
  id_klient_zrodlo INT NOT NULL,
  id_rachunku_zrodla INT NOT NULL,
  blad INT NOT NULL,
  CONSTRAINT niezrealizowany_fk_id_klient FOREIGN KEY (id_klient_zrodlo) REFERENCES klienci(id_klient),
  CONSTRAINT niezrealizowany_id_rachunek FOREIGN KEY (id_rachunku_zrodla) REFERENCES rachunki(id_rachunek),
  CONSTRAINT niezrealizowany_fk_typ FOREIGN KEY (id_typ_przelewu) REFERENCES typ_przelewu(id_typ_przelewu)
);

CREATE TABLE przelew_wych_zrealizowany
(
  id_przelew_zrealizowany INT PRIMARY KEY,
  nr_rachunku_docelowego VARCHAR2(26) NOT NULL,
  nazwa_odbiorcy VARCHAR(128) NOT NULL,
  tytul VARCHAR2(64) NOT NULL,
  adres VARCHAR2(64) NOT NULL,
  kwota DECIMAL(*, 5) NOT NULL,
  data_przyjecia_do_realizacji DATE,
  data_realizacji DATE,
  id_typ_przelewu INT NOT NULL,
  id_klient_zrodlo INT NOT NULL,
  id_rachunku_zrodla INT NOT NULL,
  CONSTRAINT przelew_w_z_fk_id_klient FOREIGN KEY (id_klient_zrodlo) REFERENCES klienci(id_klient),
  CONSTRAINT przelew_w_z_id_rachunek FOREIGN KEY (id_rachunku_zrodla) REFERENCES rachunki(id_rachunek),
  CONSTRAINT przelew_w_z_fk_typ FOREIGN KEY (id_typ_przelewu) REFERENCES typ_przelewu(id_typ_przelewu)
);

CREATE TABLE przelew_przychodzacy
(
  id_przelew_przychodzacy INT PRIMARY KEY,
  nr_rachunku_wysylajacego VARCHAR2(26) NOT NULL,
  nazwa_odbiorcy VARCHAR(128) NOT NULL,
  tutul  VARCHAR2(64) NOT NULL,
  adress VARCHAR2(64) NOT NULL,
  kwota DECIMAL(*,5) NOT NULL,
  id_rachunek_docelowy INT NOT NULL,
  id_typ_przelewu INT NOT NULL,
  data_realizacji DATE NOT NULL,
  CONSTRAINT przelew_p_fk_rach_doc FOREIGN KEY (id_rachunek_docelowy) REFERENCES rachunki(id_rachunek),
  CONSTRAINT przelew_p_fk_typ FOREIGN KEY (id_typ_przelewu) REFERENCES typ_przelewu(id_typ_przelewu)
);


INSERT INTO obywatelstwa(id_obywatelstwa, obywatelstwo) VALUES (1, 'polskie');
COMMIT;

INSERT INTO uprawnienia(id, name) VALUES (1, 'USER');
COMMIT;

CREATE SEQUENCE USER_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE ACCOUNT_SEQ START WITH 1 INCREMENT BY 1;
COMMIT;