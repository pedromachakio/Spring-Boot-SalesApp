CREATE TABLE CLIENT (
ID INTEGER PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(100)
);

CREATE TABLE PRODUCT (
ID INTEGER PRIMARY KEY AUTO_INCREMENT,
DESCRIPTION VARCHAR(100),
UNIT_PRICE NUMERIC(20,2)
);

CREATE TABLE ORDER_DETAILS (
ID INTEGER PRIMARY KEY AUTO_INCREMENT,
CLIENT_ID INTEGER REFERENCES CLIENT (ID),
ORDER_DATE TIMESTAMP,
TOTAL_PRICE NUMERIC(20,2)
);

CREATE TABLE PRODUCT_ORDERED (
ID INTEGER PRIMARY KEY AUTO_INCREMENT,
PRODUCT_ID INTEGER REFERENCES PRODUCT (ID),
ORDER_DETAILS_ID INTEGER REFERENCES ORDER_DETAILS (ID),
QUANTITY INTEGER
);