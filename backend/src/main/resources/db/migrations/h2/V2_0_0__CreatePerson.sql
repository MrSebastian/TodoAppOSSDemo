CREATE TABLE person
(
    id          VARCHAR2(36) not null,
    firstname   VARCHAR2(100),
    lastname    VARCHAR2(100),
    email       VARCHAR2(100) not null,

    PRIMARY KEY (id)
);