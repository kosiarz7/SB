--liquibase formatted sql

--changeset ubezpieczenia:9
CREATE TABLE roszczenia (
	id_roszczenie INT PRIMARY KEY,
	id_ubezpieczenie INT NOT NULL,
	id_klient INT NOT NULL,
	dataZgloszenia DATE NOT NULL,
	opisZgloszenia VARCHAR2(500)  NOT NULL,
	dataZakonczenia DATE NOT NULL,
	STATUS VARCHAR(50) NOT NULL,
	CONSTRAINT fk_id_ubezpieczenieR FOREIGN KEY (id_ubezpieczenie) references ubezpieczenie(id_ubezpieczenie),
	CONSTRAINT fk_id_klientR foreign key (id_klient) references klienci(id_klient)
);

CREATE SEQUENCE INSURANCECLAIM_SEQ START WITH 1 INCREMENT BY 1;
