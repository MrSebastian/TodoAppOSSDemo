CREATE TABLE Person
(
    id          VARCHAR(36) not null,
    firstname   VARCHAR(100),
    lastname    VARCHAR(100),
    email       VARCHAR(100) not null,

    PRIMARY KEY (id)
);