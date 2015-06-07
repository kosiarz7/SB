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

-- Ubezpieczenia

CREATE TABLE typUbezpieczenie
(
	id_typUbezpieczenie integer  NOT NULL,
  nazwa varchar2(50)  NOT NULL,
  typ varchar2(50)  NOT NULL,
	opis_korzysci varchar2(255)  NOT NULL,
  opis_inne varchar2(255) NOT NULL,
	CONSTRAINT typUbezpieczenie_pk PRIMARY KEY (id_typUbezpieczenie)
) ;

CREATE TABLE rachunekUbezpieczenia
(
	id_rachunekUbezpieczenia integer  NOT NULL,
	nazwa varchar2(50)  NOT NULL,
	saldo DECIMAL(*, 5) NOT NULL ,
	numer number(26,0)  NOT NULL,
	CONSTRAINT rachunekUbezpieczenia_pk PRIMARY KEY (id_rachunekUbezpieczenia)
);

CREATE TABLE ubezpieczenie
(
	id_ubezpieczenie integer  NOT NULL,
	id_rachunekUbezpieczenia integer  NOT NULL,
	id_typUbezpieczenie integer NOT NULL,
	id_klient integer  NOT NULL,
	id_rachunekSplaty integer NOT NULL,
	dataPoczatku date  NOT NULL,
	dataKonca date  NOT NULL,
	wartosc DECIMAL(*, 5) NOT NULL,
	platnoscRatalna integer NOT NULL,
	CONSTRAINT Ubezpieczenie_pk PRIMARY KEY (id_ubezpieczenie),
	CONSTRAINT fk_id_typUbezpieczenie2 foreign key (id_typUbezpieczenie) references typUbezpieczenie(id_typUbezpieczenie),
	CONSTRAINT fk_id_klient2 foreign key (id_klient) references klienci(id_klient),
	CONSTRAINT fk_id_rachunekUbezpieczenia2 FOREIGN KEY (id_rachunekUbezpieczenia) references rachunekUbezpieczenia(id_rachunekUbezpieczenia),
	CONSTRAINT fk_id_rachunekSplaty2 FOREIGN KEY (id_rachunekSplaty) references rachunki(id_rachunek)
) ;

CREATE TABLE ubezpieczenieKomunikacyjne
 (
	id_uKomunikacyjne integer  NOT NULL,
	id_ubezpieczenie integer  NOT NULL,
	czyAC integer NOT NULL,
	wartosc_oc DECIMAL(*, 5) NOT NULL,
	wartosc_ac DECIMAL(*, 5),
	samochod varchar(500),
	CONSTRAINT ubezpieczenieKomunikacyjne_pk PRIMARY KEY (id_uKomunikacyjne),
	CONSTRAINT fk_id_ubezpieczenie2 FOREIGN KEY (id_ubezpieczenie) references ubezpieczenie(id_ubezpieczenie)
) ;

CREATE TABLE ubezpieczenieSplata
 (
	id_ubezpieczenieSplata integer  NOT NULL,
	id_ubezpieczenie integer  NOT NULL,
	dataSplaty DATE NOT NULL,
	rata DECIMAL(*, 5) NOT NULL,
	status varchar(50) NOT NULL,
	CONSTRAINT ubezpieczenieSplata_pk PRIMARY KEY (id_ubezpieczenieSplata),
	CONSTRAINT fk_id_ubezpieczenie6 FOREIGN KEY (id_ubezpieczenie) references ubezpieczenie(id_ubezpieczenie)
) ;

