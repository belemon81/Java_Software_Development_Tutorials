DROP TABLE IF EXISTS COFFEE;
DROP TABLE IF EXISTS SUPPLIER;

CREATE TABLE COFFEE
(
    COF_NAME TEXT    NOT NULL,
    SUP_ID   INTEGER NOT NULL,
    PRICE    REAL    NOT NULL,
    SALES    INTEGER NOT NULL,
    TOTAL    INTEGER NOT NULL,
    FOREIGN KEY (SUP_ID) REFERENCES SUPPLIER (SUP_ID),
    PRIMARY KEY (COF_NAME)
);

CREATE TABLE SUPPLIER
(
    SUP_ID   INTEGER NOT NULL,
    SUP_NAME TEXT    NOT NULL,
    STREET   TEXT    NOT NULL,
    CITY     TEXT    NOT NULL,
    STATE    TEXT    NOT NULL,
    ZIP      TEXT,
    PRIMARY KEY (SUP_ID)
);