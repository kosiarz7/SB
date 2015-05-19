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
  	email VARCHAR2(128),
  	CONSTRAINT klienci_dl_imie CHECK (LENGTH(imie) > 2),
  	CONSTRAINT klienci_dl_nazwisko CHECK (LENGTH(nazwisko) > 2), 
  	CONSTRAINT klienci_dl_adres CHECK (LENGTH(adres) > 5), 
  	CONSTRAINT klienci_dl_kod_pocztowy CHECK (REGEXP_LIKE(kod_pocztowy, '^[0-9]{2}[-]{1}[0-9]{3}$')), 
  	CONSTRAINT klienci_dl_miasto CHECK (LENGTH(miasto) > 2), 
  	CONSTRAINT klienci_pesel CHECK (REGEXP_LIKE(pesel, '^[0-9]{11}$')), 
  	CONSTRAINT klienci_nr_dowodu CHECK (REGEXP_LIKE(nr_dowodu, '^[A-Z]{3}[-]{1}[0-9]{6}$')),
  	CONSTRAINT klienci_fk_obywatelstwo FOREIGN KEY (id_obywatelstwa) REFERENCES obywatelstwa(id_obywatelstwa)
);

CREATE TABLE uprawnienia (
    id INTEGER NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE uprawnienia_klientow (
    id INTEGER NOT NULL,
    klient_id INTEGER NOT NULL,
    uprawnienie_id INTEGER NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT uprawnienia_kli_00_klient_fk FOREIGN KEY (klient_id) REFERENCES klienci(id_klient),
    CONSTRAINT uprawnienia_kli_00_upraw_fk FOREIGN KEY (uprawnienie_id) REFERENCES uprawnienia(id)
);

CREATE TABLE rachunki (
  	id_rachunek VARCHAR2(26) PRIMARY KEY,
  	nazwa VARCHAR(255) NOT NULL,
  	numer INTEGER NOT NULL,
  	saldo DECIMAL(*, 5) NOT NULL ,
  	data_zalozenia DATE NOT NULL
);

CREATE TABLE klienci_rachunki (
  	id_klient_rachunek INT PRIMARY KEY,
  	id_klient INT NOT NULL,
  	id_rachunek VARCHAR2(26)  NOT NULL,
  	CONSTRAINT klienci_rachunki_fk_klient FOREIGN KEY (id_klient) REFERENCES klienci(id_klient),
  	CONSTRAINT klienci_rachunki_fk_rachunek FOREIGN KEY (id_rachunek) REFERENCES rachunki(id_rachunek)
); 

INSERT INTO obywatelstwa(id_obywatelstwa, obywatelstwo) VALUES (1, 'polskie');
COMMIT;

INSERT INTO uprawnienia(id, name) VALUES (1, 'USER');
COMMIT;

CREATE SEQUENCE USER_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE ACCOUNT_SEQ START WITH 1 INCREMENT BY 1;
COMMIT;