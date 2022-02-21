DROP TABLE IF EXISTS CARD;
DROP TABLE IF EXISTS CLIENT;

CREATE TABLE IF NOT EXISTS CLIENT
(
    ID       NUMBER NOT NULL PRIMARY KEY,
    LOGIN    VARCHAR2(500),
    PASSWORD NUMBER NOT NULL,
    TOKEN VARCHAR2(1000)
);

CREATE TABLE IF NOT EXISTS CARD
(
    ID        NUMBER NOT NULL,
    CLIENT_ID NUMBER NOT NULL,
    BALANCE   NUMBER,
    NUMBER    NUMBER NOT NULL,
    TITLE     VARCHAR2(300),
    FOREIGN KEY (CLIENT_ID) REFERENCES CLIENT (ID)
);

INSERT INTO CLIENT(ID, LOGIN, PASSWORD)
VALUES (1, 'User1', 123),
       (2, 'User2', 1234),
       (3, 'User3', 12345);

INSERT INTO CARD (ID, CLIENT_ID, BALANCE, NUMBER, TITLE)
VALUES (10, 1, 123, 111, 'First first client card'),
       (11, 1, 1234, 112, 'Second first client card'),
       (12, 1, 12345, 113, 'Third first client card'),
       (13, 1, 123456, 114, 'Fourth first client card'),
       (20, 2, 123, 115, 'First second client card'),
       (21, 2, 1234, 116, 'Second second client card'),
       (22, 2, 1234, 117, 'Third second client card'),
       (23, 2, 12345, 118, 'Fourth second client card'),
       (30, 3, 123, 119, 'First third client card'),
       (31, 3, 1234, 120, 'Second third client card');