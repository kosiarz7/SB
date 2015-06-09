--liquibase formatted sql

--changeset qbisiak:7q
ALTER TABLE KARTY
MODIFY (
	DIALY_PROXIMITY_MAX_OPS INT
	);
	
ALTER TABLE KARTY
ADD (
	LABEL VARCHAR2(30)
	);
	
INSERT INTO KARTY (CARD_TYPE, ID, DIALY_CASH_MAX_AMMOUNT, ENABLED, LIMIT, LOCKED, DIALY_NONCASH_MAX_AMMOUNT, DIALY_PROXIMITY_MAX_AMMOUNT, DIALY_PROXIMITY_MAX_OPS, DIALY_LIMIT, LABEL) VALUES ('D', KARTY_SEQ.NEXTVAL, '600', 'Y', '0', 'N', '600', '50', '2', '1000', 'Child');
INSERT INTO KARTY (CARD_TYPE, ID, DIALY_CASH_MAX_AMMOUNT, ENABLED, LIMIT, LOCKED, DIALY_NONCASH_MAX_AMMOUNT, DIALY_PROXIMITY_MAX_AMMOUNT, DIALY_PROXIMITY_MAX_OPS, DIALY_LIMIT, LABEL) VALUES ('D', KARTY_SEQ.NEXTVAL, '5000', 'Y', '0', 'N', '10000', '150', '3', '15000', 'Student');
INSERT INTO KARTY (CARD_TYPE, ID, DIALY_CASH_MAX_AMMOUNT, ENABLED, LIMIT, LOCKED, DIALY_NONCASH_MAX_AMMOUNT, DIALY_PROXIMITY_MAX_AMMOUNT, DIALY_PROXIMITY_MAX_OPS, DIALY_LIMIT, LABEL) VALUES ('D', KARTY_SEQ.NEXTVAL, '10000', 'Y', '0', 'N', '16000', '150', '3', '25000', 'Gold');
INSERT INTO KARTY (CARD_TYPE, ID, DIALY_CASH_MAX_AMMOUNT, ENABLED, LIMIT, LOCKED, DIALY_NONCASH_MAX_AMMOUNT, DIALY_PROXIMITY_MAX_AMMOUNT, DIALY_PROXIMITY_MAX_OPS, DIALY_LIMIT, LABEL) VALUES ('D', KARTY_SEQ.NEXTVAL, '15000', 'Y', '0', 'N', '24000', '150', '3', '35000', 'Platinum');