--liquibase formatted sql

--changeset qbisiak:8q
INSERT INTO OPERACJE_KARTA (ID, CARD_DISCRIMINATOR, CARD_OPERATION_TYPE, INTEREST, PRICE) VALUES (OPERACJE_KARTA_SEQ.NEXTVAL, 'D', 'CASH', 0,0);
INSERT INTO OPERACJE_KARTA (ID, CARD_DISCRIMINATOR, CARD_OPERATION_TYPE, INTEREST, PRICE) VALUES (OPERACJE_KARTA_SEQ.NEXTVAL, 'D', 'NON_CASH', 0,0);
INSERT INTO OPERACJE_KARTA (ID, CARD_DISCRIMINATOR, CARD_OPERATION_TYPE, INTEREST, PRICE) VALUES (OPERACJE_KARTA_SEQ.NEXTVAL, 'D', 'PROXIMITY', 0,0);
INSERT INTO OPERACJE_KARTA (ID, CARD_DISCRIMINATOR, CARD_OPERATION_TYPE, INTEREST, PRICE) VALUES (OPERACJE_KARTA_SEQ.NEXTVAL, 'H', 'CASH', 0,0);
INSERT INTO OPERACJE_KARTA (ID, CARD_DISCRIMINATOR, CARD_OPERATION_TYPE, INTEREST, PRICE) VALUES (OPERACJE_KARTA_SEQ.NEXTVAL, 'H', 'NON_CASH', 0,0);
INSERT INTO OPERACJE_KARTA (ID, CARD_DISCRIMINATOR, CARD_OPERATION_TYPE, INTEREST, PRICE) VALUES (OPERACJE_KARTA_SEQ.NEXTVAL, 'H', 'PROXIMITY', 0,0);
INSERT INTO OPERACJE_KARTA (ID, CARD_DISCRIMINATOR, CARD_OPERATION_TYPE, INTEREST, PRICE) VALUES (OPERACJE_KARTA_SEQ.NEXTVAL, 'C', 'CASH', 0,0);
INSERT INTO OPERACJE_KARTA (ID, CARD_DISCRIMINATOR, CARD_OPERATION_TYPE, INTEREST, PRICE) VALUES (OPERACJE_KARTA_SEQ.NEXTVAL, 'C', 'NON_CASH', 0,0);
INSERT INTO OPERACJE_KARTA (ID, CARD_DISCRIMINATOR, CARD_OPERATION_TYPE, INTEREST, PRICE) VALUES (OPERACJE_KARTA_SEQ.NEXTVAL, 'C', 'PROXIMITY', 0,0);