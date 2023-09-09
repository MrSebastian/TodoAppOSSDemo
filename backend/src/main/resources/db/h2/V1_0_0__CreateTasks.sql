CREATE TABLE tasks
(
    id          VARCHAR2(36) not null,
    title       VARCHAR2(100) not null,
    description VARCHAR2(1000) not null,
    dueDate     TIMESTAMP,

    PRIMARY KEY (id)
);