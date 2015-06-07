--liquibase formatted sql

--changeset ubezpieczenia:8
CREATE TABLE rachunekUbezpieczenia (
	id_rachunekUbezpieczenia INT PRIMARY KEY,
	nazwa VARCHAR2(50)  NOT NULL,
	saldo DECIMAL(*, 5) NOT NULL,
	numer VARCHAR2(26)  NOT NULL
);

CREATE TABLE ubezpieczenie (
	id_ubezpieczenie INT PRIMARY KEY,
	id_rachunekUbezpieczenia INT NOT NULL,
	id_typUbezpieczenie INT NOT NULL,
	id_klient INT  NOT NULL,
	id_rachunekSplaty INT NOT NULL,
	dataPoczatku DATE  NOT NULL,
	dataKonca DATE  NOT NULL,
	wartosc DECIMAL(*, 5) NOT NULL,
	platnoscRatalna INT NOT NULL,
	CONSTRAINT fk_id_typUbezpieczenie2 foreign key (id_typUbezpieczenie) references typUbezpieczenie(id_typUbezpieczenie),
	CONSTRAINT fk_id_klient2 foreign key (id_klient) references klienci(id_klient),
	CONSTRAINT fk_id_rachunekUbezpieczenia2 FOREIGN KEY (id_rachunekUbezpieczenia) references rachunekUbezpieczenia(id_rachunekUbezpieczenia),
	CONSTRAINT fk_id_rachunekSplaty2 FOREIGN KEY (id_rachunekSplaty) references rachunki(id_rachunek)
);

CREATE TABLE ubezpieczenieKomunikacyjne (
	id_uKomunikacyjne INT PRIMARY KEY,
	id_ubezpieczenie INT  NOT NULL,
	czyAC INT NOT NULL,
	wartosc_oc DECIMAL(*, 5) NOT NULL,
	wartosc_ac DECIMAL(*, 5),
	samochod VARCHAR2(500),
	CONSTRAINT fk_id_ubezpieczenie2 FOREIGN KEY (id_ubezpieczenie) references ubezpieczenie(id_ubezpieczenie)
);

CREATE TABLE ubezpieczenieSplata (
	id_ubezpieczenieSplata INT PRIMARY KEY,
	id_ubezpieczenie INT NOT NULL,
	dataSplaty DATE NOT NULL,
	rata DECIMAL(*, 5) NOT NULL,
	status varchar(50) NOT NULL,
	CONSTRAINT fk_id_ubezpieczenie6 FOREIGN KEY (id_ubezpieczenie) references ubezpieczenie(id_ubezpieczenie)
);

	CREATE SEQUENCE INSURANCE_SEQ START WITH 1 INCREMENT BY 1;
	CREATE SEQUENCE INS_ACC_SEQ START WITH 1 INCREMENT BY 1;
	CREATE SEQUENCE TRANS_INS_SEQ START WITH 1 INCREMENT BY 1;
	CREATE SEQUENCE UBEZPIECZENIESPLATA_SEQ START WITH 1 INCREMENT BY 1;
